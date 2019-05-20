/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pablo
 */
public class Ambito { //Lo mismo que entorno
    private String nombre;
    private List<Elemento> elementos;
    //private Ambito ambitoPadre;
        
    public Ambito(String n){
        this.nombre = n;
        this.elementos = new ArrayList<>();
        //this.ambitoPadre = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }
    
    public void add(Elemento e){
        this.elementos.add(e);
    }
    
    public Elemento busca(String lexema){
        for(Elemento e : this.elementos){
            if(e.getLexema().equals(lexema)){
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Ambito{" + "nombre=" + nombre + ", elementos=" + elementos + '}';
    }
}