/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.BooksFacade;
 
import java.io.Serializable;
import javax.ejb.EJB;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import Entity.*;

/**
 *
 * @author SYLUN
 */
@Named(value="BookController")
@ManagedBean
@SessionScoped
public class BookController implements Serializable{
    @EJB
    private BooksFacade booksFacade;

      private Books b=new Books();

    public Books getB() {
        return b;
    }

    public void setB(Books b) {
        this.b = b;
    }
      
    
    public BookController() {
    }
    public List<Books> findAll(){
        return this.booksFacade.findAll();
    }
    
    public String add(){
        this.booksFacade.create(this.b);
        this.b=new Books();        
        return "bookList";
    }
    
    public void delete(Books b){
        this.booksFacade.remove(b);
    }
    public String edit(Books b){
        this.b=b;
        return "editBook";
    }
    public String edit(){
        this.booksFacade.edit(this.b);
        this.b=new Books();
        return "bookList";
    }
    
     public String addStudent(Student s){
        this.b.getStudent().add(s);
        return "studentList";
    }
    
}
