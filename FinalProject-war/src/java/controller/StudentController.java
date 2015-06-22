/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import Entity.*;
import Facade.StudentFacade;
/**
 *
 * @author SYLUN
 */
@Named(value="StudentController")
@ManagedBean 
@SessionScoped
public class StudentController implements Serializable {
    @EJB
    private StudentFacade studentFacade;  
    private Student s=new Student();

    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
    }
    
    /**
     * Creates a new instance of StudentController
     */
    public StudentController() {
    }
    
    public List<Student> findAll(){
        return this.studentFacade.findAll();
        }
    
    public String add(){
        this.studentFacade.create(this.s);
        this.s=new Student();        
        return "studentList";
    }
    
    public void delete(Student s){
        this.studentFacade.remove(s);
    }
    public String edit(Student s){
        this.s=s;
        return "editStudent";
    }
   
    public String addBook(Books b){
        this.s.getBooks().add(b);
        return "studentList";
    }
    
    public String edit(){
        this.studentFacade.edit(this.s);
        this.s=new Student();
        return "studentList";
    }
    
     
    
}
