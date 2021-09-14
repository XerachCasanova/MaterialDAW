/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario_prog09;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xerach
 */
public class Serializador {
    
    private ObjectInputStream lector;
    private ObjectOutputStream escritor;
    
    
    public void escribirListaVehiculos(LinkedList <Vehiculo> listaVehiculos, String nombreFichero){
        
        try {
            escritor = new ObjectOutputStream(new FileOutputStream(nombreFichero));
            
            escritor.writeObject(listaVehiculos);
            
        }catch (FileNotFoundException ex) {
            
            ex.printStackTrace();
            
        } catch (IOException ex) {
            
            ex.printStackTrace();
            
        }
        
    }
    
    public  LinkedList<Vehiculo> leerListaVehiculos(String fichero){
        
        File nombreFich = new File(fichero);
        
        if (nombreFich.exists()){
            try {
                lector = new ObjectInputStream(new FileInputStream(nombreFich));

                return (LinkedList<Vehiculo>) lector.readObject();

            }catch (FileNotFoundException ex) {

                ex.printStackTrace();

            } catch (IOException ex) {

                ex.printStackTrace();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Serializador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
        
    }
    
}
