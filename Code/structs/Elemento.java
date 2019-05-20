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
public class Elemento {
    private String lexema;
    private String clase;
    private String tipo;
    private Ambito parametros;
        
    public Elemento(String c, String l, String t){
        this.clase = c;
        this.lexema = l;
        this.tipo = t;
        this.parametros = null;
    }
    
    public Elemento(String c, String l, String t, Ambito a){
        this.clase = c;
        this.lexema = l;
        this.tipo = t;
        this.parametros = a;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String nombre) {
        this.lexema = nombre;
    }

    public String getClase() {
        return clase;
    }

    // V = variable, F = funcion, P = parametro
    public void setClase(String clase) {
        this.clase = clase;
    }
    
    // I = integer, F = float, V = void
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Ambito getParametros() {
        return this.parametros;
    }

    public void setTs(Ambito p) {
        this.parametros = p;
    }
    
    @Override
    public String toString() {
        return "Elemento{" + "lexema=" + lexema + ", clase=" + clase + ", tipo=" + tipo + ", parametros=" + parametros + '}';
    } 
}