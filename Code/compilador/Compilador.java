/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author pablo
 */

import java.io.FileReader;
import java.io.Reader;
import structs.TablaSimbolos;

public class Compilador {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        TablaSimbolos ts = new TablaSimbolos();

        String ruta;
        
        if(args.length != 0){
            ruta = args[0];
        } else {
            ruta = "test.c";
        }
        
        System.out.println("Comenzando compilacion");
        Reader reader = new FileReader(ruta);
        parser p = new parser(new AnalizadorLexico(reader));
        p.parse();
        System.out.println("Compilacion finalizada con exito");
    }
}