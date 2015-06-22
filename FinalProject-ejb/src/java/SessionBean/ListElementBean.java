/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
/**
 *
 * @author SYLUN
 */
@Stateful
 
public class ListElementBean implements ListElementRemote{

    List<Integer> values=new ArrayList<>();
    @Override
    public void AddElement(int e) {
         values.add(e);
    }

    @Override
    public void RemoveElement(int e) {
        values.remove(new Integer(e));
    }

    @Override
    public List<Integer> getElement() {
        return values;
    }

    
}
