/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.ClienteN;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface ClienteNFacadeLocal {

    void create(ClienteN clienteN);

    void edit(ClienteN clienteN);

    void remove(ClienteN clienteN);

    ClienteN find(Object id);

    List<ClienteN> findAll();

    List<ClienteN> findRange(int[] range);

    int count();
    
}
