/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

/**
 *
 * @author pablo
 */
public class ErrorSintactico extends Exception {
    
    public ErrorSintactico (String mensaje) {
        super(mensaje);
    }
    
    public ErrorSintactico (String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}