/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaescritorio;

import dao.ConexionDAO;

/**
 *
 * @author EdHam
 */
public class JavaEscritorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        ConexionDAO.getConnection();
     
    }
    
}
