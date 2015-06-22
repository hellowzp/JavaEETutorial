/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import Entity.*;
import Facade.BooksFacade;
import Facade.StudentFacade;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author SYLUN
 */
@Named(value="StudentController")
@ManagedBean 
@SessionScoped
public class StudentController implements Serializable {
    @EJB
    private BooksFacade booksFacade;
     private static final long serialVersionUID = 1L;
    @EJB
    private StudentFacade studentFacade; 
    
    private Student s=new Student();
    List<Books> books;
    public int id,sid,bid;
    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
       

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String SearchBooks(){        
        
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.id = Integer.parseInt(request.getParameter("myform:input"));        
        Student st = studentFacade.find(id);
        books = st.getBooks(); 
        for(int i = 0; i < books.size(); i++){                
         System.out.println("BookID " + i + ": " + books.get(i).getName()+ " and ParentID:" + books.get(i).getParentId().toString());
        // list.add("BookID " + i + ": " + books.get(i).getName()+ " and ParentID:" + books.get(i).getParentId().toString());
        }
        return "studentList";
      }      
     
   public String AddPurchase(){
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       this.sid = Integer.parseInt(request.getParameter("purchaseform:studentid")); 
       this.bid = Integer.parseInt(request.getParameter("purchaseform:bookid")); 
      
       //System.out.println("sid :"+sid +" /bid : "+bid); 
       // Find both student and books infor for the sid and bid that send from webform
       Student st1= this.studentFacade.find(sid);       
       Books bo= this.booksFacade.find(bid);        
       //then add the book that has id (bid) to the List of books in student entity class
       st1.getBooks().add(bo);
       // finally update the information of student that has id(sid), it mean that add book to him
       this.studentFacade.edit(st1);
      // System.out.println("BookID:"+ bo.getBooksId()+ " and BookName:" +bo.getName()+" ParentID: "+ bo.getParentId().getId());
      // System.out.println("StudentID:"+ st1.getStudentId()+ " and StName:" +st1.getName()+" Gender: "+ st1.getGender()+ " Age: "+ st1.getAge());
       
 //      this.getBooks();
 //      for(int i=0;i<books.size();i++){
 //           System.out.println(books.get(i));
 //      }
       // this.studentFacade.create(this.s.setBooks(books.));
        return "purchaseList";
   } 
    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
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
