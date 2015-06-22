/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import java.util.List;
import javax.ejb.Remote;

 
 
@Remote
public interface ListElementRemote {    
    void AddElement(int e);
    void RemoveElement(int e);

    /**
     *
     * @return
     */
    List<Integer> getElement(); 
    
}
