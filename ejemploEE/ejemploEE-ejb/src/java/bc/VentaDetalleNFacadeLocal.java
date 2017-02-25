/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.VentaDetalleN;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface VentaDetalleNFacadeLocal {

    void create(VentaDetalleN ventaDetalleN);

    void edit(VentaDetalleN ventaDetalleN);

    void remove(VentaDetalleN ventaDetalleN);

    VentaDetalleN find(Object id);

    List<VentaDetalleN> findAll();

    List<VentaDetalleN> findRange(int[] range);

    int count();
    
}
