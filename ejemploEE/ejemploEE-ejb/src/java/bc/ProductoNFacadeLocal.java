/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.ProductoN;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface ProductoNFacadeLocal {

    void create(ProductoN productoN);

    void edit(ProductoN productoN);

    void remove(ProductoN productoN);

    ProductoN find(Object id);

    List<ProductoN> findAll();

    List<ProductoN> findRange(int[] range);

    int count();
    
}
