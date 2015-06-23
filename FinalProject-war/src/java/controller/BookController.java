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
import Facade.AuthorFacade;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author SYLUN
 */
@Named(value="BookController")
@ManagedBean
@SessionScoped
public class BookController implements Serializable{
    @EJB
    private AuthorFacade authorFacade;
    @EJB
    private BooksFacade booksFacade;

    private Books b=new Books();
    List<Student> students;
    private int aid;
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public Books getB() {
        return b;
    }

    public void setB(Books b) {
        this.b = b;
    }
    
    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count1) {         
       // List<Student> s1= this.studentFacade.findAll();
        this.count=count1;//s1.size()+1;
        
    }
    public String findCount(){
        List<Books> b1= this.booksFacade.findAll();
        count=b1.size()+1; 
        return "/book/addBook"; 
    }
    
       
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String SearchStudents(){        
       // System.out.println("start search");
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.id = Integer.parseInt(request.getParameter("myform:input")); 
        //System.out.println("now get id"+ id);
        Books bo = booksFacade.find(id);
        students = bo.getStudent(); 
        
        
        
        for(int i = 0; i < students.size(); i++){                
         System.out.println("StudentID " + i + ": " + students.get(i).getName()+ " and Gender:" + students.get(i).getGender());
         //list.add("BookID " + i + ": " + books.get(i).getName()+ " and ParentID:" + books.get(i).getParentId().toString());
        }
        return "bookList";
      }      
    
    
    public BookController() {
    }
    public List<Books> findAll(){
        return this.booksFacade.findAll();
    }
    
    public String add(){
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       this.aid = Integer.parseInt(request.getParameter("addbooks:parentId")); 
       Author author= this.authorFacade.find(aid); 
       b.setParentId(author);
       
       this.b.setBooksId(count);
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
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       this.aid = Integer.parseInt(request.getParameter("editbooks:parentId")); 
       Author authorq= this.authorFacade.find(aid); 
       b.setParentId(authorq);
        
        
        this.booksFacade.edit(this.b);
        this.b=new Books();
        return "bookList";
    }
    
     public String addStudent(Student s){
        this.b.getStudent().add(s);
        return "studentList";
    }
     
     
    
}
