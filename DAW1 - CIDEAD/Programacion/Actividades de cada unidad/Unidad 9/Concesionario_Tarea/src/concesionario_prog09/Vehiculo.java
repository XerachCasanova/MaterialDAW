/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario_prog09;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;


/**
 * Se encarga de crear objetos de tipo Vehiculo, a los cuales se les puede
 * añadir información tales como: marca, matrícula, nombre del propietario, dni
 * del propietario descripción del vehículo, kilómetros del vehículo, precio
 * y fecha de matriculación.
 * 
 * @author Xerach E. Casanova Cabrera - DAW A.
 * 
 */

public class Vehiculo implements Comparable<Vehiculo>, Serializable {

    
    private String marca, matricula, propietario, descripcion, dni;
    private int kilometros;
    private float precio;
    private LocalDate fechaMatr;
    
    public Vehiculo(){
        
    }
    
    /**
     * Constructor del objeto vehículo. Se encarga de construir objetos de típo
     * vehículo.
     * 
     * @param marca marca del vehículo
     * @param matricula matrícula del vehículo
     * @param propietario nombre del propietario del vehículo
     * @param descripcion descripción del vehículo
     * @param kilometros kilómetros actuales del vehículo
     * @param dni dni del propietario del vehículo
     * @param precio precio del vehículo
     * @param fechaMatr fecha de matriculación del vehículo
     */
    public Vehiculo(String marca, String matricula, String propietario, String descripcion, int kilometros, String dni, float precio, LocalDate fechaMatr){
       this.marca = marca;
       this.matricula = matricula;
       this.propietario = propietario;
       this.descripcion = descripcion;
       this.kilometros = kilometros;
       this.dni = dni;
       this.precio = precio;
       this.fechaMatr = fechaMatr;

        
    }
    
    /**
     * Devuelve el valor de la matrícula del vehículo.
     * 
     * @return la matrícula a devolver.
     */
    
    public String getMatricula() { return matricula; }
  
    
    /**
     * Inserta la matrícula del vehículo con el valor que el usuario
     * le pase por parámetros.
     * 
     * @param matricula la matrícula a insertar
     */
    
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    /**
     * Devuelve los kilómetros actuales del objeto de tipo vehículo
     * 
     * @return los kilómetros a devolver.
     */
    
    public int getKm(){ return kilometros; }
    
  
    /**
     * Inserta los kilómetros del vehículo con el valor que el usuario
     * le pase por parámetros.
     * 
     * @param km los kilómetros a insertar.
     */
    public void setKm(int km) { kilometros = km; }
    
 
    /**
     * Devuelve los años que tiene el vehículo a partir de la fecha de
     * matriculación
     * 
     * @return los años a devolver. 
     */
    public int get_anios(){ 
        
        return Period.between(getFechaMatr(), LocalDate.now()).getYears(); 
        
    }

    
    /**
     * Devuelve la descripción del vehículo.
     * 
     * @return Descripción a devolver.
     */
    
    public String getDescripcion(){ return descripcion; }
    
    //public void setDescripcion(String desc){ descripcion = desc; }
    
    
    /**
     * Inserta una descripción en el vehículo.
     * 
     * @param descripcion fescripción a insertar.
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    /**
     * Devuelve el nombre del propietario del vehículo.
     * 
     * @return nombre del propietario a devolver.
     */
    
    public String getPropietario(){ return propietario; }
    
    
    /**
     * Asigna el nombre del propietario al vehículo.
     * 
     * @param propietario nombre del propietario a asignar.
     */
    public void setPropietario(String propietario) { this.propietario = propietario; }

    
    /**
     * Devuelve el precio del vehículo.
     * @return precio a devolver.
     */
    
    public float getPrecio(){ return precio; }
    
    
    /**
     * Asigna un precio en el vehículo.
     * 
     * @param precio precio a asignar.
     */
    public void setPrecio(float precio) {  this.precio = precio; }

    /**
     * Devuelve la marca del vehículo.
     * 
     * @return marca del vehículo a devolver.
     */
    public String getMarca() {  return marca; }

    /**
     * Asigna una marca al vehículo.
     * 
     * @param marca marca a asignar.
     */
    public void setMarca(String marca) { this.marca = marca; }

  

    /**
     * Devuelve el DNI del propietario del vehículo
     * 
     * @return DNI a devolver.
     */
    public String getDni() { return dni; }

    /**
     * Asigna el dni del propietario al vehículo.
     * 
     * @param dni DNI a asignar.
     */
    public void setDni(String dni) { this.dni = dni; }


    /**
     * 
     * Devuelve la fecha de matriculación del vehículo
     * 
     * @return fecha de matriculación a devolver.
     */
    public LocalDate getFechaMatr() {  return fechaMatr;    }

    /**
     * 
     * Asigna una fecha de matriculación al vehículo.
     * 
     * @param fechaMatr fecha de matriculación a asignar.
     */
    public void setFechaMatr(LocalDate fechaMatr) { this.fechaMatr = fechaMatr; }

    @Override
    public int compareTo(Vehiculo o) {
       
        
        if (matricula.substring(4,7).equals(o.matricula.substring(4,7))) return matricula.compareTo(o.matricula);
        else return matricula.substring(4,7).compareTo(o.matricula.substring(4,7));
        
   
        
    }
       

} //fin clase Vehiculo


