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

    public Author getS() {
        return a;
    }

    public void setS(Author a) {
        this.a = a;
    }
    
    /**
     * Creates a new instance of AuthorController
     */
    public AuthorController() {
    }
    
    public List<Author> findAll(){
        return this.authorFacade.findAll();
        }
    
    public String add(){
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
