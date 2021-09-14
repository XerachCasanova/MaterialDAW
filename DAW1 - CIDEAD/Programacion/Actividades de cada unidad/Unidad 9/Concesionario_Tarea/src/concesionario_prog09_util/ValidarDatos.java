/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario_prog09_util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase se encarga de validar los datos que el usuario introduce por
 * pantalla, se recomienda manejar excepciones con el uso de los métodos de
 * esta clase.
 * 
 * @author Xerach E. Casanova Cabrera - DAW A
 */
public class ValidarDatos {
    
   
    private static final String LETRAS_DNI= "TRWAGMYFPDXBNJZSQVHLCKE";
    
    //---------------------------------------------------------------
    //MÉTODOS DE VALIDACIÓN DEL DNI
    
    /**
     * Calcula la letra del DNI sobre el número que se le pasa por parámetro.
     * 
     * 
     * @param dni número sobre el cual se calculará la letra del DNI que le corresponde.
     * @return Devuelve la letra que le corresponde al DNI de ese número concreto.
     */
    
    private static char calcularLetraDNI (int dni) {

        char letra;

        letra = LETRAS_DNI.charAt(dni % 23);       

        return letra;

    }   // fin calcularLetraDNI()
    
    /**
     * Convierte una cadena de DNI sin la letra final en tipo int, si se trata
     * de un NIE, convierte la letra del inicio en su equivalente numérico.
     * 
     * @param letraNie letra inicial del NIE
     * @param numeroDni cadena que contiene el número del DNI.
     * @return devuelve número de DNI en tipo de dato entero.
     */
    
    private static int getDniNumerico(String letraNie, String numeroDni){

        switch (letraNie) {
                case "X":
                    numeroDni = "0" + numeroDni;
                    break;
                case "Y":
                    numeroDni = "1" + numeroDni;
                    break;
                case "Z":
                    numeroDni = "2" + numeroDni;
                    break;
                default:
                    numeroDni = "0" + numeroDni;
                    break;

            } //fin sitch case
        

        return Integer.valueOf(numeroDni);
        
    } //fin getDniNUmerico()
    
    /**
     * Analiza un DNI o un NIE para analizar si se trata de un DNI válido o no,
     * si no es válido se lanza una excepción.
     * 
     * @param dni dni/nie completo, con letra, para analizar si válido.
     * @throws Exception devuelve una cadena con el error "DNI no válido".
     */
    public static void validarDNI(String dni) throws Exception{
        
        boolean dniValido = true;
        char letraReal;
        String letraNie, letraNif;
        String numeroDNI;
        
        if (dni.length()==9) {
            
            Pattern expresion = Pattern.compile("([XYZxyz]?)([0-9]{7,8})([A-Za-z])");
            Matcher cadena = expresion.matcher(dni);
            
            if (cadena.matches()) {
                
                letraNie = cadena.group(1);
                numeroDNI = cadena.group(2);
                letraNif = cadena.group(3);
                
                if (letraNif == "") dniValido = false;
                else {
               
                    letraReal = ValidarDatos.calcularLetraDNI(getDniNumerico(letraNie, numeroDNI));
            
                    if (letraReal != letraNif.charAt(0)) dniValido = false; 
                
                } //fin if - else (letraNif == "")
                
            } else dniValido = false; //fin if - else (cadena.matches())
            
            
        } else dniValido = false; //fin if - else (dni.length()==9)
        
        if (!dniValido) throw new Exception ("DNI no válido, introduzca un DNI correcto.");

    } //fin validarDNI()
    
    //--------------------------------------------------------------
    //MÉTODO DE VALIDACIÓN DE NOMBRE
    
    /**
     * Método encargado de validar un nombre y dos apellidos
     * @param nombre a validar
     * @throws Exception devuelve una excepción si el nombre a validar no contiene
     * un nombre y dos apellidos.
     */
    public static void validarNombre(String nombre) throws Exception {
        

        /*nombre=nombre.replaceAll(" +", " "); Con replaceAll podemos eliminar 
        los espacios consecutivos que existan en la cadena, utilizando como primer
        parámetro una expresión regular, sin embargo, el ejercicio pide no usar
        expresiones regulares, así que he utilizado un algoritmo que elimina los
        espacios consecutivos*/
        
        nombre = nombre.trim();
        int espaciosConsec = 0;
        
        for (int i=0; i< nombre.length(); i++){
            
           if (nombre.charAt(i) == ' ' && nombre.charAt(i+1) == ' ') {
               
               nombre = nombre.substring(0, i) + nombre.substring(i+1, nombre.length());
               
               espaciosConsec = 0;
               i = 0;
               
           }
            
        }
        
        /*Una vez eliminados los espacios innecesarios, realizo un bucle que cuenta
        el número de espacios totales que hay. Si hay 2 espacios significa que se han
        introducido un nombre y dos apellidos.*/
        
        int contadorEspacios=0, posicionEspacio =0;
        
        while (posicionEspacio != -1) {
            
            posicionEspacio = nombre.indexOf(" ", posicionEspacio+1);
            
            if (posicionEspacio != -1) contadorEspacios++;
            
        } //fin while
        
        if (contadorEspacios != 2 || nombre.length() > 40) throw new Exception ("Nombre incorrecto, introduce un nombre y dos apellidos (max. 40 caracteres).");
        
        
    } //fin validarNombre()
    
    //-------------------------------------------------------------
    //MÉTODO DE VALIDACIÓN DE KILÓMETROS
    
    
    /**
     * Método encargado de validar si los Kilómetros introducidos son mayores
     * que los kilómetros que anteriores.
     * 
     * @param kmsNuevos Hace referencia a los kilómetros con los que se va a actualizar
     * el vehículo
     * @param kmsActuales Hace referencia a los kilómetros actuales del vehículo.
     * @throws Devuelve una excepción si el número de kilómetros a actualizar es menor
     * al número de kilómetros actual del vehículo.
     * 
     */
    
    public static void validarKms(int kmsNuevos, int kmsActuales) throws Exception{
        
        
        if (kmsNuevos <= kmsActuales) throw new Exception ("El número de kilómetros "
                + "introducidos no puede ser menor que el número de kilómetros del vehículo");
       
        
    } // fin validarKms() - 2 parámetros
    
    /**
     * Método encargado de validar si el número de Kilómetros introducido es mayor
     * que cero.
     * 
     * @param kms número de kilómetros a validar.
     * @throws Devuelve una excepción si el número de kilómentros es menor o igual
     * que cero.
     */
    public static void validarKms(int kms) throws Exception{
        
        if ( kms <=0 ) throw new Exception ("El número de kilómetros introducido debe ser mayor que 0.");
        
    } //fin validarKms() - 1 parámetro
    
    //------------------------------------------------------------------
    //MÉTODO DE VALIDACIÓN DE FECHA DE MATRICULACIÓN
    
    /**
     * Método encargado de validar si la fecha introducida es anterior al día de hoy.
     * @param fecha fecha a evaluar.
     * @return Devuelve falso si la fecha a evaluar es posterior al día de hoy.
     */
    /*public static void validarFechaMatriculacion(LocalDate fecha) throws Exception{
        
        if (!fecha.isBefore(LocalDate.now())) throw new Exception ("La fecha de matriculación no puede ser posterior a la actual.");
        
    }*/
    
   
    public static LocalDate validarFechaMatriculacion(int anio, int mes, int dia) throws Exception{
        
        LocalDate fechaMatr = null;
        
        try {
            
            fechaMatr = LocalDate.of(anio, mes, dia);
            
            if (!fechaMatr.isBefore(LocalDate.now())) throw new Exception ("La fecha de matriculación no puede ser posterior a la actual.");
            
        }catch (DateTimeException e){
            System.out.println("Fecha incorrecta. Por favor, introduzca una fecha válida");
           
            
        } //fin try - catch
        
        
        return fechaMatr;
        
    } //fin validarFechaMatriculacion()
    
    
    /**
     * valida el año de matriculación controlando que el año no sea menor de 1900
     * y mayor que el año actual. Genera una excepción si el año es incorrecto.
     * 
     * @param anio año a comprobar
     * @throws Exception Genera un mensaje de error si el año introducido es incorrecto.
     */
    
    public static void validarAnioMatriculacion(int anio) throws Exception {
        
        if (anio < 1900 || anio > LocalDate.now().getYear()) throw new Exception ("Introduce "
                + "un año válido entre 1900 y el año actual.");
        
        
    } //fin validarAnioMatriculacion()
    
    /**
     * Valida el mes de matriculación, controlando que sea un número de mes correcto,
     * Genera una excepción si el mes es incorrecto.
     * 
     * @param mes mes a comprobar
     * @throws Genera un mensaje de error si el mes introducido es incorrecto.
     */
    
    public static void validarMesMatriculacion(int mes) throws Exception {
        
        if (mes < 1 || mes > 12) throw new Exception ("Introduce un mes válido");
        
        
    } // fin validarMesMatriculacion()
     
    
    /**
     * Valida el día de matriculación, controlando que sea un día del mes correcto,
     * Genera una excepción si el mes es incorrecto.
     * 
     * @param dia dia a analizar
     * @throws Exception Genera un mensaje de error si el día introducido es incorrecto.
     */
    public static void validarDiaMatriculacion(int dia) throws Exception {
        
        if (dia < 1 || dia > 31) throw new Exception ("Introduce un día válido");
        
        
    } //fin validarDiaMatriculacion()
     
    
    /**
     * Comprueba que el precio introducido sea correcto, genera una excepción si
     * el número introducido es negativo.
     * 
     * @param precio precio a analizar.
     * @throws Exception  Genera un mensaje de error si el precio introducido es incorrecto.
     * 
     */
    public static void validarPrecio(float precio) throws Exception {
        
        if (precio < 1) throw new Exception ("Introduce un precio válido");
        
        
    } //fin validarPrecio()
    
    
    /**
     * Comprueba que la matrícula introducida tenga un formato correcto, genera
     * una excepción si la matrícula tiene un formato incorrecto.
     * 
     * @param matricula matrícula a analizar
     * @throws Exception Genera un mensaje de error si la matrícula tiene un formato incorrecto.
     */
    public static void validarMatricula(String matricula) throws Exception{
        
        Pattern expresion = Pattern.compile("[0-9]{4}[A-Z]{3}");
        Matcher cadena = expresion.matcher(matricula);
        
        if (!cadena.matches()) throw new Exception ("La matrícula no es válida.");
        
        
    } //fin validarMatricula()
    

} //fin Clase ValidarDatos
