<%-- 
    Document   : ResultSOAP
    Created on : May 25, 2015, 1:17:21 PM
    Author     : SYLUN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SOAP Result Page</title>
    </head>
    <body>
        <p>Your Result of Adding (Number1 + Number2):</p>   
    
    
     <%-- start web service invocation --%><hr/>
    <%
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
    try {
	webclient.Webclient_Service service = new webclient.Webclient_Service();
	webclient.Webclient port = service.getWebclientPort();
	 // TODO initialize WS operation arguments here
	int a = Integer.parseInt(num1);
	int b = Integer.parseInt(num2);
	// TODO process result here
	int result = port.add(a, b);
	out.println("Result = "+result);
    } catch (Exception ex) {
    }
    %>
    <%-- end web service invocation --%><hr/>
    
    <p>Your Result of Author Name is:</p>
    
    
   
    <%-- start web service invocation --%><hr/>
    <%
    String id = request.getParameter("id");
    try {
	webclient.Webclient_Service service = new webclient.Webclient_Service();
	webclient.Webclient port = service.getWebclientPort();
	 // TODO initialize WS operation arguments here
	int a = Integer.parseInt(id);
	// TODO process result here
	java.lang.String result = port.search(a);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
      
    <a href='index.xhtml'>Home</a>
    </body>
</html>
