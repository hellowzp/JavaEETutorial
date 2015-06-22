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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
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
//import javax.xml.ws.WebServiceRef;
//import net.restfulwebservices.datacontracts._2008._01.BibleVerse;
//import net.restfulwebservices.servicecontracts._2008._01.BibleKJVService;
//import net.restfulwebservices.servicecontracts._2008._01.IBibleServiceGetVerseDefaultFaultContractFaultFaultMessage;
//import net.webservicex.WeatherForecast;
//import net.webservicex.WeatherForecastSoap;
//import net.webservicex.WeatherForecasts;

/**
 *
 * @author sylun
 */
@WebServlet(name = "servlet", urlPatterns = {"/servlet"})
public class CalculatorServlet extends HttpServlet {
     
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.webservicex.net/WeatherForecast.asmx.wsdl")
//    private WeatherForecast service;    
//    private WeatherForecastSoap port; 
//    private WeatherForecasts result; 
   // StatefulBean statefulBean = lookupStatefulBeanBean();
    
     @EJB
    private TimeCounterBeanLocal timeCounterBean; 
    private static final String STATEFUL_TEST_BEAN_KEY = "STATEFUL_TEST_BEAN_KEY";
    private StatefulBean statefulBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request CalculatorServlet request
     * @param response CalculatorServlet response
     * @throws ServletException if a CalculatorServlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int timeCounter = timeCounterBean.getNrOfSeconds();
        timeCounterBean.setNrOfSeconds(0);
        PrintWriter out = response.getWriter();       
        
        HttpSession httpSession = request.getSession(true);
        httpSession.getAttribute(STATEFUL_TEST_BEAN_KEY);        
        
        if(statefulBean == null){
           statefulBean = lookupStatefulBeanBean();
           httpSession.setAttribute(STATEFUL_TEST_BEAN_KEY, statefulBean); 
           statefulBean.setMessage(request.getParameter("message"));           
        }
        String msg = statefulBean.getMessage();

        /*
        String zipcode="3000";
        String Min_Temp="";
        String Max_Temp="";
        try{
        port=service.getWeatherForecastSoap();
        result=port.getWeatherByZipCode(zipcode);
        result=port.getWeatherByPlaceName(Max_Temp);
         Min_Temp = result.getDetails().getWeatherData().get(0).getMinTemperatureC();
         Max_Temp = result.getDetails().getWeatherData().get(0).getMaxTemperatureC();
         System.out.println("Min Temp: " + result.getDetails().getWeatherData().get(0).getMinTemperatureC());
         System.out.println("Max Temp: " + result.getDetails().getWeatherData().get(0).getMaxTemperatureC());
        }catch (Exception ex){
            System.out.println(ex);
        }
        */
        
        try  {
            /* TODO output your page here. You may use following sample code. */
            
            Context ctx = new InitialContext();
            ConnectionFactory     connectionFactory = (ConnectionFactory)ctx.lookup("jms/NotificationQueue");
            Queue     queue = (Queue)ctx.lookup("jms/dest");
            javax.jms.Connection  connection = connectionFactory.createConnection();
            javax.jms.Session        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            
            message.setText(msg);
            System.out.println( "It come from Servlet:"+ message.getText());
            messageProducer.send(message);        
             
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CalculatorFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>We send msg: " + message.getText() + " to server</h2>"); 
            out.println("<h2>you did " + timeCounter + " seconds over the previous</h2>");
            out.println("<br></br>");
//            out.println("<h2>Min Temp: " + Min_Temp + "</h2>"); 
//            out.println("<h2>Max Temp: " + Max_Temp + " </h2>");
            out.println("<br></br>");
            out.println("Click <a href='index.xhtml'>here</a> to go back to main");
            out.println("</body>");
            
            out.println("</html>");
        }catch(NamingException | JMSException ex){
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request CalculatorServlet request
     * @param response CalculatorServlet response
     * @throws ServletException if a CalculatorServlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request CalculatorServlet request
     * @param response CalculatorServlet response
     * @throws ServletException if a CalculatorServlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the CalculatorServlet.
     *
     * @return a String containing CalculatorServlet description
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
