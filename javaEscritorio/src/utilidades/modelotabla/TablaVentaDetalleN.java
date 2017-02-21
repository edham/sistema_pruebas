package utilidades.modelotabla;





import entidades.clsVentaDetalleN;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import utilidades.clsUtilidades;


public class TablaVentaDetalleN extends AbstractTableModel {
    private List<clsVentaDetalleN> items = null;
    private List<String> headers = null;
    private double total=0;
    private double igv=0;
    private double subTotal=0;

    public TablaVentaDetalleN() {
       items = new ArrayList<clsVentaDetalleN>();
       headers = new ArrayList<String>();
       headers.add(new String("N°"));
       headers.add(new String("PRODUCTO"));  
       headers.add(new String("PRECIO"));
       headers.add(new String("CANTIDAD"));
       headers.add(new String("TOTAL"));
    }

    public void limpiar()
    {
        items = new ArrayList<clsVentaDetalleN>();
        this.total = 0;
        this.igv = 0;
        this.subTotal = 0;
         this.fireTableDataChanged();
    }

       public List<clsVentaDetalleN> listar()
    {
        return items;
    }

    public List<clsVentaDetalleN> getItems() {
        return items;
    }

    public double getTotal() {
        return clsUtilidades.Redondear(total, 2);
    }

    public double getIgv() {
        return clsUtilidades.Redondear(igv, 2);
    }

    public double getSubTotal() {
        return clsUtilidades.Redondear(subTotal, 2);
    }
    
    public void insertarLista(List<clsVentaDetalleN> listaBoleta)
    {
        items =listaBoleta;
         for (clsVentaDetalleN entidad:items)
        {
            this.subTotal+=entidad.getCantidad()*entidad.getObjProductoN().getPrec_prod();
        }
         igv=subTotal*0.18;
         total=subTotal+igv;
          this.fireTableDataChanged();
    }

     public boolean insertar(clsVentaDetalleN entidad)
    {
      
        boolean igual=true;

        for (clsVentaDetalleN item:items)
        {
            if(item.getObjProductoN().getCod_prod()==entidad.getObjProductoN().getCod_prod())
            {
                igual=false;
                break;
            }
        }
        if(igual)
        {                
            items.add(entidad);
            subTotal+=entidad.getCantidad()*entidad.getObjProductoN().getPrec_prod();
            igv=subTotal*0.18;
            total=subTotal+igv;
        }         
        this.fireTableDataChanged();
        return igual;
       
    }
     
   

   public void remover(int rowIndex)
    {
        subTotal-=items.get(rowIndex).getCantidad()*items.get(rowIndex).getObjProductoN().getPrec_prod();
        igv=subTotal*0.18;
        total=subTotal+igv;
        items.remove(rowIndex);        
         this.fireTableDataChanged();
    }
    

   @Override
    public int getRowCount() {
        return items.size();
    }
    
    @Override
    public int getColumnCount() {
        return headers.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return headers.get(columnIndex).toString();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            clsVentaDetalleN entidad = items.get(rowIndex);
        switch(columnIndex)
        {
             case 0:
                    return rowIndex+1;               
            case 1:
                    return entidad.getObjProductoN().getNom_prod();
           case 2:
                    return  entidad.getCosto();
           case 3:
                    return  entidad.getCantidad();
           case 4:
                    return  clsUtilidades.Redondear(entidad.getTotal(),2);
           
        }
        return null;
    }
}
