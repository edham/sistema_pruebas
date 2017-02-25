/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.VentaN;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface VentaNFacadeLocal {

    void create(VentaN ventaN);

    void edit(VentaN ventaN);

    void remove(VentaN ventaN);

    VentaN find(Object id);

    List<VentaN> findAll();

    List<VentaN> findRange(int[] range);

    int count();
    
}
