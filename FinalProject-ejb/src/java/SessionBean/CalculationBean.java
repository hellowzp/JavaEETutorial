/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author SYLUN
 */
@Stateless
@LocalBean
public class CalculationBean {

    public int add(int number1,int number2){
         return number1 + number2;
    }
    public int subtract(int number1,int number2){
         return number1 - number2;
    }
    public int multiply(int number1,int number2){
         return number1 * number2;
    }
    public int divide(int number1,int number2){
         return number1 / number2;
    }
}
