 
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="SessionBean.ListElementRemote"%>
<%@page import="javax.ejb.EJB;"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%!
@EJB    
private static ListElementRemote values;
    
    public void jspInit(){
        try
        {
            InitialContext io=new InitialContext();
            values=(ListElementRemote)io.lookup("java.global/FinalProject/ListElement");
        }
        catch(Exception e) {System.out.println("Here is the error"+e);}
    }
    
%>

<%
    if(request.getParameter("addNum")!=null)
    {
        int e=Integer.parseInt(request.getParameter("t1"));
        values.AddElement(e);
    }
    if(request.getParameter("remNum")!=null)
    {
        int e=Integer.parseInt(request.getParameter("t1"));
        values.RemoveElement(e);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="stateful" method="post">
            <input type="text" name="t1"><br>
            <input type="submit" value="Add" name="addNum"><br>
            <input type="submit" value="Remove" name="remNum"><br>
            
            <%
                   if(values!=null){
                       List<Integer> nums=values.getElement();
                       for(int value: nums){
                           out.println("<br>"+ value);
                       }
                       out.println("<br> Size of List :"+ nums.size());
                   }                  
                   
            %>
            
            <a href='index.xhtml'>Back To Main</a>
        </form>
    </body>
</html>
