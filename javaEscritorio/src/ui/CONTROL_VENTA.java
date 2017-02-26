package ui;
import com.clsReglasNegocio;
import entidades.clsClienteN;
import entidades.clsProductoN;
import entidades.clsVentaDetalleN;
import entidades.clsVentaN;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import utilidades.modelotabla.TablaVentaDetalleN;
public class CONTROL_VENTA extends javax.swing.JFrame {
    
private TablaVentaDetalleN modelo=new TablaVentaDetalleN();
   
    
     
     void Llenar_ComboCLiente(){
         try {             
                DefaultComboBoxModel cmodelo=new DefaultComboBoxModel();
                cmodelo.removeAllElements();
                cmodelo.addElement("Seleccione una Opcción");
                for(clsClienteN entidad: clsReglasNegocio.ListarCliente(""))
                {
                    cmodelo.addElement(entidad);
                }               
                jcbCliente.setModel(cmodelo);           
         } catch (Exception ex) {
            Logger.getLogger(CONTROL_VENTA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void Llenar_ComboProducto(){
         try {             
                DefaultComboBoxModel cmodelo=new DefaultComboBoxModel();
                cmodelo.removeAllElements();
                cmodelo.addElement("Seleccione una Opcción");
                for(clsProductoN entidad: clsReglasNegocio.ListarProducto("2"))
                {
                    cmodelo.addElement(entidad);
                }               
                jcbProducto.setModel(cmodelo);           
         } catch (Exception ex) {
            Logger.getLogger(CONTROL_VENTA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   void Limpiar(){
        Llenar_ComboProducto();
           jcbCliente.setSelectedIndex(0);
           txtDireccion.setText("");
           txtRuc.setText("");
           txtPrecio.setText("");
           txtStock.setText("");       
           txtCantidad.setText("");
           modelo.limpiar();
           txtIGV.setText(""+modelo.getIgv());
           txtSubTotal.setText(""+modelo.getSubTotal());
           txtTotal.setText(""+modelo.getTotal());
   }
   
  void Guardar(){
    if(jcbCliente.getSelectedIndex()>0)
    {
        if(modelo.getItems().size()>0)
        {
            int i = JOptionPane.showConfirmDialog(this, "ESTA SEGURO QUE DESEA REALIZAR LA VENTA", "CONFIRMAR VENTA", JOptionPane.YES_NO_OPTION);
            if (i == 0)        
            try {
                clsVentaN entidad = new clsVentaN();
                entidad.setIgv(modelo.getIgv());
                entidad.setSub_total(modelo.getSubTotal());
                entidad.setTotal(modelo.getTotal());
                entidad.setObjClienteN((clsClienteN)jcbCliente.getSelectedItem());
                entidad.setListaItems(modelo.getItems());
                
                int id=clsReglasNegocio.insertarVenta(entidad);
                if(id>0)
                {
                    JOptionPane.showMessageDialog(rootPane,"LA VENTA SE GRABO CORRECTAMENTE CON EL CODIGO : "+id);
                   
                    Limpiar();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"OCURRIO UN ERROR INTENTELO MAS TARDE");
                }
                
            } catch (Exception ex) {
                Logger.getLogger(CONTROL_VENTA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"INGRESE AL MENOS UN PRODUCTO");
        }
    }
    else
        JOptionPane.showMessageDialog(rootPane,"SELECCIONES UN CLIENTE");
  }
  
  
  
    public CONTROL_VENTA() {
        initComponents();
        jtDetalleVenta.setModel(modelo);
        Llenar_ComboCLiente();
        Llenar_ComboProducto();
        txtIGV.setText(""+modelo.getIgv());
        txtSubTotal.setText(""+modelo.getSubTotal());
        txtTotal.setText(""+modelo.getTotal());
        
         jtDetalleVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int index=jtDetalleVenta.getSelectedRow();
                if(e.getClickCount()==2 && index>=0){
                    int i = JOptionPane.showConfirmDialog(rootPane, "ESTA SEGURO QUE DESEA QUITAR ESTE PRODUCTO", "CONFIRMAR VENTA", JOptionPane.YES_NO_OPTION);
                    if (i == 0)        
                    {
                        modelo.remover(index);
                        txtIGV.setText(""+modelo.getIgv());
                        txtSubTotal.setText(""+modelo.getSubTotal());
                        txtTotal.setText(""+modelo.getTotal());
                    }
                }
            }

        });
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDireccion = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jcbCliente = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jcbProducto = new javax.swing.JComboBox<String>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDetalleVenta = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("NOMBRE CLIENTE");

        jcbCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbClienteActionPerformed(evt);
            }
        });

        jLabel5.setText("DIRECCION");

        jLabel6.setText("RUC");

        jLabel7.setText("PRODUCTO");

        jcbProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProductoActionPerformed(evt);
            }
        });

        jLabel8.setText("PRECIO");

        jtDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtDetalleVenta);

        jLabel9.setText("StockActual");

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel10.setText("Suma Subtotal");

        jLabel11.setText("IGV");

        jLabel12.setText("Total");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel13.setText("Ingrese Cantidad:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtRuc, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addComponent(jcbCliente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnNuevo)
                                        .addGap(83, 83, 83)
                                        .addComponent(btnGuardar)
                                        .addGap(162, 162, 162)
                                        .addComponent(jLabel10)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIGV, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(txtSubTotal)
                                    .addComponent(txtTotal))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(jLabel10)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      Guardar();
//        JOptionPane.showMessageDialog(root, "Venta registrado");
      
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jcbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbClienteActionPerformed
        txtDireccion.setText("");
        txtRuc.setText("");
        if(jcbCliente.getSelectedIndex()>0)
        {
            clsClienteN entidad = (clsClienteN)jcbCliente.getSelectedItem();
            txtDireccion.setText(entidad.getDir_cli());
            txtRuc.setText(entidad.getRuc_cli());
        }
    }//GEN-LAST:event_jcbClienteActionPerformed

    private void jcbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProductoActionPerformed
        txtStock.setText("");
        txtPrecio.setText("");
        if(jcbProducto.getSelectedIndex()>0)
        {
            clsProductoN entidad = (clsProductoN)jcbProducto.getSelectedItem();
            txtStock.setText(""+entidad.getStock());
            txtPrecio.setText(""+entidad.getPrec_prod());
        }
    }//GEN-LAST:event_jcbProductoActionPerformed

    double total=0;
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       Limpiar();
       jtDetalleVenta.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
       
        if(jcbProducto.getSelectedIndex()>0)
        {
            clsVentaDetalleN entidad = new clsVentaDetalleN();
            entidad.setObjProductoN((clsProductoN)jcbProducto.getSelectedItem());
            entidad.setCosto(entidad.getObjProductoN().getPrec_prod());
            int catidad=0;
            try{
               catidad=Integer.parseInt(txtCantidad.getText());
               if(catidad<=entidad.getObjProductoN().getStock())
               {
                   entidad.setCantidad(catidad);
                   entidad.setTotal(entidad.getCantidad()*entidad.getCosto());
                   if(modelo.insertar(entidad))
                   {
                        jcbProducto.setSelectedIndex(0);
                        txtCantidad.setText("");
                        txtIGV.setText(""+modelo.getIgv());
                        txtSubTotal.setText(""+modelo.getSubTotal());
                        txtTotal.setText(""+modelo.getTotal());
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(rootPane,"PRODUCTO YA INGRESADO");
                   }
               }else
               {
                   JOptionPane.showMessageDialog(rootPane,"INGRESE UNA CANTIDAD MENOR O IGUAL AL STOCK ACTUAL");
               }
            }catch(NumberFormatException e){
                 JOptionPane.showMessageDialog(rootPane,"INGRESE UN NUMERO VALIDO");
            }
           
        }
        else
            JOptionPane.showMessageDialog(rootPane,"SELECCIONES UN PRODUCTO");
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CONTROL_VENTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CONTROL_VENTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CONTROL_VENTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CONTROL_VENTA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CONTROL_VENTA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbCliente;
    private javax.swing.JComboBox<String> jcbProducto;
    private javax.swing.JTable jtDetalleVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables


}
