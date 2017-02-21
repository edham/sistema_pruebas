/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author EdHam
 */
public class clsUtilidades {
    public static double Redondear(double NDecimal, int Decimales)
    {
      return Math.round(NDecimal*Math.pow(10,Decimales))/Math.pow(10,Decimales);
    } 
}
