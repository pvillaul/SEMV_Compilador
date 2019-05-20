/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 *
 * @author pablo
 */
public class TablaSimbolos { //pila de ambitos y cada ambito 3 listas de parametros,variables
    private Stack<Ambito> pilaAmbitos;
        
    public TablaSimbolos(){
        this.pilaAmbitos = new Stack<>();
    }

    public Stack<Ambito> getPilaAmbitos() {
        return pilaAmbitos;
    }

    public void setPilaAmbitos(Stack<Ambito> pilaAmbitos) {
        this.pilaAmbitos = pilaAmbitos;
    }
    
    public void NuevoEntorno(String id){
        Ambito a = new Ambito(id);
        this.pilaAmbitos.push(a);
    }
    
    public void SalirEntorno(){
        this.pilaAmbitos.pop();
    }
    
    public void InsertaID(String clase, String lexema, String tipo){
        Ambito a = this.pilaAmbitos.peek();
        Elemento e = new Elemento(clase,lexema,tipo);
        a.add(e);
    }
    
    public void InsertaID(String clase, String lexema, String tipo, Ambito ap){
        Ambito a = this.pilaAmbitos.peek();
        Elemento e = new Elemento(clase,lexema,tipo,ap);
        a.add(e);
    }
    
    public Elemento busca(String lexema){
        Elemento e;
        for(Ambito a : this.pilaAmbitos){
            e = a.busca(lexema);
            if (e != null) {
                return e;
            }
        }
        return null;
    }
    
    public String getTipoVariable(String v) throws Exception {
        /*Buscar en todos los ambitos de la pila empexndo por la cima y vamos buscando todas las variables
        sino error sintactico.*/
                
        //USas eel metodo buscar y desvuelves el tipo
        
        Elemento e = this.busca(v);
        if(e != null){
            if(e.getClase().equals("V")){
                return e.getTipo();
            } else {
                throw new ErrorSintactico("Elemento " + v + " no es una variable");
            }
        } else {
            throw new ErrorSintactico("Elemento " + v + " Inexistente");
        }
    }
    
    public String getTipoFuncion(String f) throws ErrorSintactico {
        /*Buscar en todos los ambitos de la pila empexndo por la cima y vamos buscando todas las variables
        sino error sintactico.*/
        
        Elemento e = this.busca(f);
        if(e != null){
            if(e.getClase().equals("F")){
                return e.getTipo();
            } else {
                throw new ErrorSintactico("Elemento " + f + " no es una funcion");
            }
        } else {
            throw new ErrorSintactico("Elemento " + f + " Inexistente");
        }
    }
    
    public boolean ComprobarParametros(String nf, List<String> p) throws ErrorSintactico {
        /*Mirar que la funcion nf este en un ambito
        Comprobar que en dicho ambito este p campo a campo, iguales o compatibles
        Crear tabla de comatibilidad*/
        boolean check = false;
        
        Ambito aux = null;
        Elemento e;
        for(Ambito a : this.pilaAmbitos){
            e = a.busca(nf);
            if (e != null) {
                if(e.getClase().equals("F")){
                    aux = a;
                    break;
                }
            }
        } //Buscamos el ambito donde se encuntre NF
        
        int size = p.size();
        int sum = 0;
        
        for(Elemento elem : aux.getElementos()){
            if(elem.getClase().equals("P")){
                for(String n : p){
                    if(elem.getLexema().equals(n)){
                        check = true;
                        sum++;
                    }
                }
            }
        } //Comprobamos que dicho ambito sea igual a todos los parametros N:N
        
        if (sum == size){
            return check;
        } else {
            throw new ErrorSintactico("Funcion " + nf + " no corresponden parametros");
        }
    }
    
    public boolean Iguales(String exp1, String exp2, String opt) throws ErrorSintactico {
        if(exp1.equals(exp2)){
            return true;
        } else {
            if(opt != null){
                throw new ErrorSintactico("Expreciones " + exp1 + " - " + exp2 + " no compatibles"
                + " con la operacion " + opt);
            } else {
                throw new ErrorSintactico("Condicion Incorrecta para las expreciones "
                + exp1 + " - " + exp2);
            }
        }
    }
    
    public void CargarCabeceras(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File ("/home/pablo/" + ruta);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null) {
              System.out.println(linea);
              String[] parts = linea.split("\\(");
              String[] type_name = parts[0].split(" ");
              this.NuevoEntorno(type_name[1]); //Creamos un nuevo entorno con el nombre de la funcion
              
              String list = parts[1].replace("\\);", "");
              Ambito lp = new Ambito("LISTPARAM");
              if(list.contains(",")){
                  String[] argv = list.split(",");
                  for(String s : argv){
                      String[] params = s.split(" ");
                      lp.add(new Elemento("P",params[1],params[0]));
                  }
                  this.InsertaID("F",type_name[1],type_name[0],lp);
              } else {
                  String[] params = list.split(" ");
                  lp.add(new Elemento("P",params[1],params[0]));
                  this.InsertaID("F",type_name[1],type_name[0],lp);
              }
              System.out.println(this.toString());
              this.SalirEntorno();
           }
        } catch(IOException e) {
            System.out.println ("El error es: " + e.getMessage());
        } finally {
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (IOException e2){
               System.out.println ("El error es: " + e2.getMessage());
           }
        }
    }

    @Override
    public String toString() {
        return "TablaSimbolos{" + "pilaAmbitos=" + pilaAmbitos + '}';
    }
}