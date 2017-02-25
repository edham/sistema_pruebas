/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.VentaN;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EdHam
 */
@Stateless
public class VentaNFacade extends AbstractFacade<VentaN> implements VentaNFacadeLocal {
    @PersistenceContext(unitName = "ejemploEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaNFacade() {
        super(VentaN.class);
    }
    
}
