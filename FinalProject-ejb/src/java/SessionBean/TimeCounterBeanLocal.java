/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import javax.ejb.Local;
import javax.ejb.Timer;

/**
 *
 * @author SYLUN
 */
@Local
public interface TimeCounterBeanLocal {
    
    public void myTimer(Timer t);
    public void cancelTimer();
    public int getNrOfSeconds();
    public void setNrOfSeconds(int nrOfSeconds);
    public Timer getTime();
    public void setTime(Timer time);
}
