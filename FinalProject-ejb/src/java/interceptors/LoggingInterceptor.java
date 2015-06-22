/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

/**
 *
 * @author sylun
 */
import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logging
public class LoggingInterceptor implements Serializable{
    public static final long serialVersionUID=-2230122751970857993L;
    public LoggingInterceptor(){
    }
     @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        System.out.println("Entering method: "
                + invocationContext.getMethod().getName() + " in class "
                + invocationContext.getMethod().getDeclaringClass().getName());

        return invocationContext.proceed();
    }
    
    /*
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
    	System.out.println("----------------------------");
        System.out.println("BEFORE: " + context.getMethod());
        
        System.out.print("Input Parameters values: ");
        // Prints the parameter values of the method
        Object[] parameters = context.getParameters();
        for(Object o: parameters) {
        	System.out.print(o + " ,");
        }
        System.out.println();
               
        
        // Call the intercepted method
        Object result = context.proceed();
        
        System.out.println("Output Parameter value: " +  result);

        
        System.out.println("AFTER: " + context.getMethod());

        return result;
    }
    */
}
