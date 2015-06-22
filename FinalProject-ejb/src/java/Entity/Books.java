/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SYLUN
 */
@Entity
@Table(name = "BOOKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByBooksId", query = "SELECT b FROM Books b WHERE b.booksId = :booksId"),
    @NamedQuery(name = "Books.findByName", query = "SELECT b FROM Books b WHERE b.name = :name")})
public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BOOKS_ID")
    private Integer booksId;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Author parentId;

    //@OneToMany(mappedBy = "parentId")    
    //private List<Author> AuthorList;
    
    @ManyToMany(mappedBy = "books")
    private List<Student> students;
    public Books() {
    }

    public Books(Integer booksId) {
        this.booksId = booksId;
    }

    public Integer getBooksId() {
        return booksId;
    }

    public void setBooksId(Integer booksId) {
        this.booksId = booksId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getParentId() {
        return parentId;
    }

    public void setParentId(Author parentId) {
        this.parentId = parentId;
    }
    /*
    
    public List<Author> getAuthorList() {
        return AuthorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.AuthorList = authorList;
    }
*/
   @XmlTransient 
     public List<Student> getStudent() {
		return students;
	}
 
	public void setStudent(List<Student> students) {
		this.students = students;
	}


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (booksId != null ? booksId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.booksId == null && other.booksId != null) || (this.booksId != null && !this.booksId.equals(other.booksId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Books[ booksId=" + booksId + " ]";
    }
    
}
