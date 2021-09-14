/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario_prog09;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import concesionario_prog09_util.ValidarDatos;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Xerach E. Casanova Cabrera - DAW A
 * 
 */

public class Concesionario{
    

    private LinkedList<Vehiculo> vehiculos;  
    private Serializador serializador;
    
    /**
     * Constructor de la clase concesionario.
     * 
     */    
    public Concesionario(String fichero){
    
        serializador =  new Serializador();
        vehiculos = serializador.leerListaVehiculos(fichero);
        
        if (Objects.isNull(vehiculos)){
            vehiculos= new LinkedList<Vehiculo>();
            vehiculos.add(new Vehiculo("Seat", "1111ZCD", "Xerach Casanova Cabrera", "Ibiza", 5000, "11111111A", 65000, LocalDate.now()));
            vehiculos.add(new Vehiculo("Opel", "8888ZBT", "Juan Fernández Díaz", "D2", 4500, "22222222B", 60000, LocalDate.now()));
            vehiculos.add(new Vehiculo("Renault", "7777BBB", "María Rodríguez Pineda", "Kangoo", 8000, "33333333C", 72000, LocalDate.now()));
            vehiculos.add(new Vehiculo("Toyota", "6666BBS", "Tomás Pérez López", "Hilux", 7500, "44444444D", 35000, LocalDate.now()));
            vehiculos.add(new Vehiculo("SsanYong", "5555DDD", "Amanda García García", "Musso", 11000, "55555555E", 40000, LocalDate.now()));
            vehiculos.add(new Vehiculo("Renault", "4444DDD", "Román Vázquez Pérez", "Berlingo", 8500, "66666666F", 80000, LocalDate.now()));
            Collections.sort(vehiculos);
            
        }

        

    } //fin Concesionario()
    
   /**
    * Busca un vehículo a partir de la matrícula que se pasa por parámetro.
    * 
    * @param matricula a comprobar
    * @return 
    */ 
    
    
   public String buscaVehiculo(String matricula){
        
        /* Creamos una variable String nula y recorremos el LinkedList de tipo 
       vehículo para buscar coincidencias con la matrícula.*/
       
        String vehiculo = null;
        int i=0;
        
        for (Vehiculo vh : vehiculos){
            if (vh.getMatricula().equals(matricula)){ //si encuentra la matrícula
                
                for (int j=0; j<imprimirVehiculo(vh).length; j++){
                    
                    /*Se recorre el método de tipo array de Strings completo y se guarda
                    en una variable el contenido de cada String contenido en el array*/
                    vehiculo = vehiculo + imprimirVehiculo(vh)[j];  
                    
                } //fin for  
                
                break;
                
            } //fin if
            
        } //fin for
        
        
        
        return vehiculo; //Devuelve null si no encuentra matrícula o una cadena con los datos del vehículo si encuentra una coincidencia.
        
    } //fin buscaVehiculo()
    
    /**
    * Método encargado de crear nuevos vehículos con los atributos recibidos por
    * parámetros.
    * 
    * @param marca a insertar en el objeto tipo vehículo.
    * @param matricula a insertar en el objeto tipo vehículo.
    * @param propietario a insertar en el objeto tipo vehículo.
    * @param descripcion a insertar en el objeto tipo vehículo.
    * @param km a insertar en el objeto tipo vehículo.
    * @param dni a insertar en el objeto tipo vehículo.
    * @param precio a insertar en el objeto tipo vehículo.
    * @param fechaMatr a insertar en el objeto tipo vehículo.
    * @return 0 para indicar que el vehículo se ha creado con éxito, 
    * -1 para indicar concesionario lleno, -2 para indicar que la matrícula ya existe
    */
   
    public int insertaVehiculo(String marca, String matricula, String propietario, String descripcion, int km, String dni, float precio, LocalDate fechaMatr ){
        
        /*Creamos una variable de tipo booleana que en principio tendrá valor false,
        recorremos el LinkedList hasta el número total de vehículos que hay creados.*/
        
        boolean matriculaExiste = false;
        
        for (Vehiculo vh : vehiculos){
 
            if (vh.getMatricula().equals(matricula)){
                matriculaExiste = true; //setteamos la variable booleana en true y salimos del bucle.   
                break;
            }//fin if
            
        }//fin for
        
        
        
        if (matriculaExiste) return -2; //si la matrícula existe devolvemos -2 y salimos sin insertar vehículos.
        //else if (nTotalVehiculos==vehiculos.length) return -1; //Esta sentencia ya no la usamos porque hemos eliminado el límite de vehículos.
        else { 
            
            vehiculos.add(new Vehiculo(marca, matricula, propietario, descripcion, km, dni, precio, fechaMatr));
            
            Collections.sort(vehiculos);
            
            return 0; //devolvemos 0 para indicar que el vehículo se ha creado correctamente.
            
        } //if - else
        
    } //fin insertaVehiculo()
    
    public void listaVehiculos(){
        
        if (vehiculos.size() > 0){
            /*
            Creamos un array de string del tamaño total de las cadenas contenidas
            en el método de tipo array 'imprimirVehiculo'
            */
            String impresion[] = new String[imprimirVehiculo(vehiculos.get(0)).length];
            
            // Imprimimos el número total de vehículos
            System.out.println("Número total de vehículos: " + vehiculos.size());
            System.out.println("-----------------------------------");
            
            for (Vehiculo vh : vehiculos){
                
                /*Recorremos el método de tipo array imprimirVehiculo en busca de las distintas
                cadenas contenidas en el array de String y las imprimimos por consola.*/
                for (int j=0; j<imprimirVehiculo(vh).length; j++){

                     System.out.print(imprimirVehiculo(vh)[j]);

                } //fin for
                
            }//fin for
            
        }else {
            
            System.out.println("No hay vehículos creados.");
            
        } //fin if-else

    } //fin listaVehiculos()
    
    /**
     * Método que actualiza los kilómetros de los distintos vehículos contenidos
     * en la clase concesionario, a partir de una matrícula dada por parámetro
     * y un número de kilómetros.
     * 
     * @param matricula a buscar
     * @param kms a modificar en el vehículo.
     * 
     * @return verdadero si se han actualizado los kilómetros o falso si no se han actualizado.
     */
    public boolean actualizaKms(String matricula, int kms){
        
        /*Creamos una variable booleana que será falsa desde el inicio y será verdadera
        si se actualizan los kilómetros. */
        
        boolean kmActualizados = false;
        
        //Recorremos el LinkedList de tipo Vehiculo
        
        for (Vehiculo vh : vehiculos){
            // si encuentra una matrícula
            if (vh.getMatricula().equals(matricula)){
                /*Llamamos al método validar para comprobar que los kilómetros
                    que vamos a actualizar son correctos.*/
                
                try {
                    /*Llamamos al método validar para comprobar que los kilómetros
                    que vamos a actualizar son correctos.*/
                    
                    ValidarDatos.validarKms(kms, vh.getKm());
                    vh.setKm(kms); //actualizamos kilómetros
                    kmActualizados = true; // modificamos la variable booleana
                    
                } catch (Exception e) {
                    /*Si el método validar devuelve una excepción, la capturamos
                    y la imprimimos en consola.*/
                    System.out.println(e.getMessage()); 
                    
                } //fin try - catch
                
                break; //Cuando encuentra una matrícula dejamos de recorrer el LinkedList
                
            }//fin if
            
        }//fin for


        return kmActualizados; //devuelve true si se ha actualizado o false si no se ha actualiado.
        
    } //fin actualizaKms()
    
    
    /**
     * Método que se encarga de borrar vehículos a partir de la matrícula que
     * se indique por parámetros 
     * 
     * @param matricula del vehículo a borrar
     * @return  true si se ha borrado correctamente, false si no se ha podido borrar.
     */
    public boolean borraVehiculo(String matricula) {
        
        /* Creamos una variable booleana a la que le daremos valor verdadero si se
        logra borrar un registro, además creamos una variable contador para  borrar
        el elemento del LinkedList cuando lo encuentre*/
        
        boolean borrado = false;
        
        int i=0;
        while (i < vehiculos.size()){
            
            if (vehiculos.get(i).getMatricula().equals(matricula)){
                
                vehiculos.remove(i);
                return true;
                
            }
            
            i++;
        }
        
        return false;
                
    }
    
    /**
     * Método que se encarga de generar un array de Strings que contiene
     * los datos del vehículo de la posición del LinkedList que se le pasa por parámetros
     * 
     * @param nVehiculo posición del vehículo en el array
     * @return array de Strings con los datos del vehículo.
     */
    private String[] imprimirVehiculo(Vehiculo vehiculo){

        String[] infoVehiculo = {
        "\n**********",
        "\nMarca: " + vehiculo.getMarca(),
        "\nMatrícula: " + vehiculo.getMatricula(),
        "\nPropietario: " + vehiculo.getPropietario(),
        "\nDNI: " + vehiculo.getDni(),
        "\nDescripción: " + vehiculo.getDescripcion(),
        "\nKilómetros: " + vehiculo.getKm(),
        "\nFecha de matriculación: " + vehiculo.getFechaMatr().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        "\nPrecio: " + vehiculo.getPrecio() + " €\n"
        };
        return infoVehiculo;
   
    } //fin imprimirVehiculo()

     public void guardarListado(String nombreFichero){
        
        serializador.escribirListaVehiculos(vehiculos, nombreFichero);
        
    }
    
} //fin clase Concesionario
