/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import SessionBean.StatefulBean;
import SessionBean.TimeCounterBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sylun
 */
@WebServlet(name = "servlet", urlPatterns = {"/servlet"})
public class servlet extends HttpServlet { 
    
     @EJB
    private TimeCounterBeanLocal timeCounterBean; 
    private static final String STATEFUL_TEST_BEAN_KEY = "STATEFUL_TEST_BEAN_KEY";
    private StatefulBean statefulBean;
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //get the timer and date from timecounterbean
        int timeCounter = timeCounterBean.getNrOfSeconds();
        timeCounterBean.setNrOfSeconds(0);
         Date today=timeCounterBean.getDate();
         
        PrintWriter out = response.getWriter(); 
        HttpSession httpSession = request.getSession(true);
        httpSession.getAttribute(STATEFUL_TEST_BEAN_KEY);        
        
        //get the message from client as stateful
      //  if(statefulBean == null){
           statefulBean = lookupStatefulBeanBean();
           httpSession.setAttribute(STATEFUL_TEST_BEAN_KEY, statefulBean); 
           statefulBean.setMessage(request.getParameter("message"));           
      //  }
        String msg = statefulBean.getMessage();
        System.out.println(msg);
    
        
        try  {
            
          //send the message that get from statefull session bean and sent back to server 
            Context ctx = new InitialContext();
            ConnectionFactory     connectionFactory = (ConnectionFactory)ctx.lookup("jms/NotificationQueue");
            Queue     queue = (Queue)ctx.lookup("jms/dest");
            javax.jms.Connection  connection = connectionFactory.createConnection();
            javax.jms.Session        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            
            message.setText(msg);
            System.out.println( "The message was sent to staeful bean is : "+ message.getText());
            messageProducer.send(message);        
             
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>We send message:( " + message.getText() + ") to server</h2>"); 
            out.println("<h2>you did " + timeCounter + " seconds over the previous </h2>");
            out.println("<h2>Today is: " + today + " </h2>");
            out.println("<br></br>");
 
            out.println("<br></br>");
            out.println("Click <a href='index.xhtml'>here</a> to go back to main");
            out.println("</body>");
            
            out.println("</html>");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private StatefulBean lookupStatefulBeanBean() {
        try {
            Context c = new InitialContext();
            return (StatefulBean) c.lookup("java:global/FinalProject/FinalProject-ejb/StatefulBean!SessionBean.StatefulBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    } 

}
