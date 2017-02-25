package examen_final;
import java.awt.Component;
import java.sql.CallableStatement;
import java.sql.Connection;  
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
public class CONTROL_VENTA extends javax.swing.JFrame {
DefaultTableModel modelo=new DefaultTableModel();
      Connection cn=null; 
    ResultSet rs=null;  
    Statement st=null;
    CallableStatement spcodigoauto=null;
    CallableStatement spingreso=null;
    CallableStatement splistado=null;
    private Component root;
    private ResultSetMetaData data;
    
     private void Conexion(){
        try{
            String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url="jdbc:sqlserver://192.168.1.5:1433;databasename=prueba";
            Class.forName(driver);
            cn=DriverManager.getConnection(url,"sa","123456");
            st=cn.createStatement(ResultSet. TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery("select * from VENTA");
        }catch(Exception ex){
            System.err.println("Error en Conexion " + ex.getMessage());
        }
    }
     void Llenar_Combo1(){
         try {
             DefaultComboBoxModel cmodelo=new DefaultComboBoxModel();
             cmodelo.removeAllElements();
             rs=st.executeQuery("select NOM_CLI from CLIENTES");
             try {
                 rs.beforeFirst();
                 while(rs.next()){
                     cmodelo.addElement(rs.getString("NOM_CLI"));
                 }
                 jComboBox1.setModel(cmodelo);
             } catch (Exception e) {
             }
         } catch (SQLException ex) {
            Logger.getLogger(CONTROL_VENTA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void Llenar_Combo2(){
         try {
             DefaultComboBoxModel cmodelo=new DefaultComboBoxModel();
             cmodelo.removeAllElements();
             rs=st.executeQuery("select NOM_PROD from PRODUCTO");
             try {
                 rs.beforeFirst();
                 while(rs.next()){
                     cmodelo.addElement(rs.getString("NOM_PROD"));
                 }
                 jComboBox2.setModel(cmodelo);
             } catch (Exception e) {
             }
         } catch (SQLException ex) {
            Logger.getLogger(CONTROL_VENTA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public static String Fecha_Actual(){
       Date Fecha=new Date();
       SimpleDateFormat FormatoF=new SimpleDateFormat("dd/MM/YYYY");
       return FormatoF.format(Fecha);
   }
   void Limpiar(){
       jTextField1.setText("");
       jComboBox1.setSelectedIndex(0);
       jTextField3.setText("");
       jTextField4.setText("");
       jComboBox2.setSelectedIndex(0);
       jTextField5.setText("");
       jTextField6.setText("");
       jTextField1.requestFocus();
   }
  void codigoautogenerado(){
        try {
            spcodigoauto=cn.prepareCall("{call codigo_autogenerado_venta(?)}");
            spcodigoauto.registerOutParameter(1,java.sql.Types.CHAR);
            spcodigoauto.execute();
            jTextField1.setText(spcodigoauto.getString(1));
        } catch (Exception e1) {          
        }
    }
  void Guardar(){
      try{
          String cod_cli="";
          rs=st.executeQuery("select COD_CLI from CLIENTES WHERE NOM_CLI='"+jComboBox1.getSelectedItem().toString().trim()+"'");
             try {
                 rs.beforeFirst();
                 if(rs.next()){
                     cod_cli=rs.getString("COD_CLI");
                 }
             } catch (Exception e) {
             }
          
           String CODD_PROD="";
           int stock=0;
          rs=st.executeQuery("select CODD_PROD,STOCK from PRODUCTO WHERE NOM_PROD='"+jComboBox2.getSelectedItem().toString().trim()+"'");
             try {
                 rs.beforeFirst();
                 if(rs.next()){
                     CODD_PROD=rs.getString("CODD_PROD");
                     stock=rs.getInt("STOCK");
                 }
             } catch (Exception e) {
             }
          spingreso=cn.prepareCall("{call SP_INGRESO_VENTA(?,?,?,?,?,?)}");
          spingreso.setString(1, jTextField1.getText().toUpperCase());
          spingreso.setString(2, jTextField2.getText().toUpperCase());
          spingreso.setString(3, cod_cli);
          spingreso.setString(4, CODD_PROD);
          spingreso.setString(5,jTextField6.getText());
          spingreso.setString(6, jTextField9.getText());
          spingreso.execute();
   
          stock=stock-Integer.parseInt(jTextField6.getText().toString());
          
          spingreso=cn.prepareCall("update PRODUCTO set STOCK=? WHERE CODD_PROD='"+CODD_PROD+"'");
          spingreso.setString(1, ""+stock);         
          spingreso.execute();
        
      }
      catch(Exception ex){JOptionPane.showMessageDialog(rootPane,ex.getMessage());}
  }
  void Listado(){
    try{
        modelo=new DefaultTableModel();
        splistado=cn.prepareCall("{call SP_LISTADO_VENTA}");
        rs=splistado.executeQuery();
        data=rs.getMetaData();
        int nc=data.getColumnCount();
        Object[] columnas=new Object[nc];
        for (int c = 0;c < nc; c++) {
            columnas[c]=data.getColumnLabel(c+1);
        }
        modelo.setColumnIdentifiers(columnas);
        while(rs.next()){
            Object[] fila=new Object[nc];
            for (int f = 0; f < nc; f++) {
                fila[f]=rs.getObject(f+1);
            }
            modelo.addRow(fila);
        }
    }catch(Exception e2){
        JOptionPane.showMessageDialog(rootPane,e2.getMessage());
    }
    }
    public CONTROL_VENTA() {
        initComponents();
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        jTable1.setModel(modelo);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jLabel4.setText("NOMBRE CLIENTE");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("DIRECCION");

        jLabel6.setText("RUC");

        jTextField5.setText("jTextField5");

        jLabel7.setText("PRODUCTO");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel8.setText("PRECIO");

        jLabel2.setText("NRO DE VENTA");

        jTextField1.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("FECHA");

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel9.setText("CANTIDAD");

        jTextField6.setText("jTextField6");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jButton2.setText("NUEVO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("GUARDAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Suma Subtotal");

        jLabel11.setText("IGV");

        jLabel12.setText("Total");

        jTextField7.setText("jTextField7");

        jTextField8.setText("jTextField8");

        jTextField9.setText("jTextField9");

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
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel3)
                                .addGap(32, 32, 32)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(83, 83, 83)
                                        .addComponent(jButton3)
                                        .addGap(162, 162, 162)
                                        .addComponent(jLabel10)))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(jTextField7)
                                    .addComponent(jTextField9))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jLabel10)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Guardar();
        Listado();
        JOptionPane.showMessageDialog(root, "Venta registrado");
        Limpiar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String SQL=
       "select * from CLIENTES where NOM_CLI='" +
             jComboBox1.getSelectedItem().toString()+"'";
       try{
           rs=st.executeQuery(SQL);
           rs.next();
           jTextField3.setText(rs.getString("DIR_CLI"));
           jTextField4.setText(rs.getString("RUC_CLI"));
       }catch(Exception ex){   
       }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
       String SQL=
       "select PRE_PROD from PRODUCTO where NOM_PROD='" +
             jComboBox2.getSelectedItem().toString()+"'";
       try{
           rs=st.executeQuery(SQL);
           rs.next();
           jTextField5.setText(rs.getString("PRE_PROD"));
       }catch(Exception ex){   
       }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    double total=0;
    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
             try {    
                 int stock=0;
                 double subtot,precio=Double.parseDouble(jTextField5.getText());
                 int cantidad=Integer.parseInt(jTextField6.getText());
                 rs=st.executeQuery("select STOCK from PRODUCTO WHERE NOM_PROD='"+jComboBox2.getSelectedItem().toString().trim()+"'");
                 try {
                     rs.beforeFirst();
                     if(rs.next()){
                         stock=rs.getInt("STOCK");
                     }
                 } catch (Exception e) {
                 }

                 if(stock>=cantidad)
                 {
                    
                    subtot=precio*cantidad;
                    Vector datos=new Vector();
                    datos.addElement(this.jComboBox2.getSelectedItem());
                    datos.addElement(jTextField5.getText());
                    datos.addElement(jTextField6.getText());
                    datos.addElement(subtot+"");
                    modelo.addRow(datos);
                    jTable1.setModel(modelo);
                    total+=subtot;
                    double igv=total*0.18;
                    jTextField7.setText(""+total);
                    jTextField8.setText(""+igv);
                    jTextField9.setText(""+(igv+total));
                 }else
                 {
                     JOptionPane.showMessageDialog(rootPane,"NO TIENE EL STOCK REQUERIDO");
                 }
             } catch (SQLException ex) {
        Logger.getLogger(CONTROL_VENTA.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Conexion();
    codigoautogenerado();
    Llenar_Combo1();
    Llenar_Combo2();
    Listado();
    jTextField2.setText(Fecha_Actual());
    
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila=jTable1.getSelectedRow();
                if(e.getClickCount()==2 && fila>=0){
                    System.out.println("Se ha hecho doble click");
                    DefaultTableModel modelTable = (DefaultTableModel) jTable1.getModel();
                    modelTable.removeRow(fila);
                    total=0;
                    for(int i=0; i<jTable1.getRowCount();i++){
                        total+=Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                    }
                    double igv=total*0.18;
                    jTextField7.setText(""+total);
                    jTextField8.setText(""+igv);
                    jTextField9.setText(""+(igv+total));
                }
            }

        });
    }//GEN-LAST:event_formWindowOpened

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        
    }//GEN-LAST:event_jTable1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Limpiar();
       codigoautogenerado();
       jTable1.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
       
    }//GEN-LAST:event_jTextField6KeyReleased

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CONTROL_VENTA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
