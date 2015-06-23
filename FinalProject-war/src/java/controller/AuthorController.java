/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Facade.AuthorFacade;
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
@Named(value="AuthorController")
@ManagedBean
@SessionScoped
public class AuthorController implements Serializable{
    @EJB
    private AuthorFacade authorFacade;

    private Author a=new Author();
    //int index = authorFacade.count() + 1;
    public Author getS() {
        return a;
    }

    public void setS(Author a) {
        this.a = a;
    }
   
    public int count;
 
    
    public int getCount() {
        return count;
    }

    public void setCount(int count1) {       
        
        this.count=count1;//s1.size()+1;
        
    }
    public String findCount(){
        List<Author> a1= this.authorFacade.findAll();
        count=a1.size()+1;         
        return "/author/addAuthor";
     }
    
    public AuthorController() {
    }
    
    public List<Author> findAll(){
        return this.authorFacade.findAll();
        }
    
    public String add(){
        this.a.setId(count);
        this.authorFacade.create(this.a);
        this.a=new Author();        
        return "authorList";
    }
    
    public void delete(Author a){
        this.authorFacade.remove(a);
    }
    public String edit(Author a){
        this.a=a;
        return "editAuthor";
    }
    public String edit(){
        this.authorFacade.edit(this.a);
        this.a=new Author();
        return "authorList";
    }
    
}