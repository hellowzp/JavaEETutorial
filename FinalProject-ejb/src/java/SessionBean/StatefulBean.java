/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean;

import java.util.concurrent.TimeUnit;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.StatefulTimeout;

 
@Stateful
@StatefulTimeout(unit = TimeUnit.SECONDS, value = 60)
@LocalBean

public class StatefulBean {

    private int id;
    private String message;
    public int getID(){
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public String getBeanInfo() {
        return this.toString();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
