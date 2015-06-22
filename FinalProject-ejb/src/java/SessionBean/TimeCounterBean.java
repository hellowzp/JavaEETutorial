/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

/**
 *
 * @author SYLUN
 */
@Singleton
public class TimeCounterBean implements TimeCounterBeanLocal {    
    private Timer time;

    @Override
    public int getNrOfSeconds() {
        return nrOfSeconds;
    }

    @Override
    public Timer getTime() {
        return time;
    }

    @Override
    public void setTime(Timer time) {
        this.time = time;
    }

    @Override
    public void setNrOfSeconds(int nrOfSeconds) {
        this.nrOfSeconds = nrOfSeconds;
    }
    private int nrOfSeconds = 0;
    
    @Schedule(minute = "*", second = "*", hour = "*", persistent = false)
    @Override
    public void myTimer(Timer t) {
        time = t;
        //System.out.println("simple timer current date: " + new Date());
        nrOfSeconds++;
    }
    
    @Override
    public void cancelTimer(){
        if(null != time){
            time.cancel();
            System.out.println("timer was cancelled after "+ nrOfSeconds +" seconds");
        }
    }
}
