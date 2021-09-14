/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario_prog09;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import concesionario_prog09_util.ValidarDatos;

/**
 *
 * @author Xerach E. Casanova Cabrera - DAW A.
 */
public class Principal {

    /**
     * Método principal del programa, en él se muestra el menú con las opciones
     * que se pueden ejecutar.
     * 
     * @param args the command line arguments
     */
    static Concesionario concesionario; //creamos un objeto de tipo Concesionario, el cual tiene un constructor vacío.
    static String marca, propietario, dni, descripcion, matricula, fichero;
    static int diaMatr, mesMatr, anioMatr, km;
    static LocalDate fechaMatr;
    static float precio;
    

    public static void main(String[] args) {
        /* Creamos una variable booleana opcionSalir con valor inicial false y llamamos
        al método mostrar menú. Entramos en un bucle del cual no saldrá hasta
        que opcionSalir no sea verdadera.*/
        

        System.out.println("Introduce la ruta del fichero que contiene el listado de vehículos:");
        fichero = new Scanner(System.in).nextLine();
        
        concesionario = new Concesionario(fichero);
        
        boolean opcionSalir = false;
        mostrarMenu();

        while (!opcionSalir) {
            String opcion = new Scanner(System.in).nextLine(); //pedimos opción al usuario
            try {
                int op = Integer.valueOf(opcion); //capturamos excepción si el valor insertado no es un entero.

                if (op > 0 && op <= 6) {

                    opcionesMenu(op); //si la opción elegida está entre los parámetros aceptados llamamos al método opcionesMenu

                } else {
                    System.out.println("Opción incorrecta, elige una "
                            + "opción entre 1 y 3. Opción 4 para salir."); //si la opción es incorrecta lanzamos un mensaje en pantalla.
                    
                } //fin if - else

            } catch (NumberFormatException e) {

                System.out.println("Solo se admiten valores numéricos."); //mensaje mostrado si se inserta cualquier valor distinto a un número entero.
                System.out.print("Elige otra opción: ");
                
            } //fin try - catch

        } //fin while

    } //fin main
    
    /**
     * Método que muestra el menú de opciones en consola.
     * 
     */
    public static void mostrarMenu() {
        
        System.out.println("\n1. Nuevo Vehículo.");
        System.out.println("2. Listar Vehículos.");
        System.out.println("3. Buscar Vehículos.");
        System.out.println("4. Modificar Kms. de Vehículo.");
        System.out.println("5. Borrar vehículo.");
        System.out.println("6. Salir.");
        System.out.print("\nElige una opción: ");
        

    } //fin mostrarMenu()
 
    /**
     * Método que se encarga de ejecutar la opción elegida por el usuario.
     * 
     * @param opcionElegida opción elegida por el usuario.
     */
    public static void opcionesMenu(int opcionElegida) {

        switch (opcionElegida) {

            case 1:

                construirFormularioNuevoVehiculo();
                break;

            case 2:

                concesionario.listaVehiculos(); //llamamos al método listaVehiculos del objeto concesionario.
                break;

            case 3:

                opcionObtenerDatos();
                
                break;

            case 4:
                
                opcionActualizarKm();
                
                break;
                
            case 5:
                
                opcionBorrarVehiculo();
                
            case 6:
                
                concesionario.guardarListado(fichero);
                System.exit(0);
                
                break;
        } //fin switch case

        mostrarMenu(); //Una vez ejecutada la opción elegida por el usuario se vuelve a mostrar el menú de opciones.

    }//fin opcionesMenu()
        
    /**
     * Método que se encarga de construir un formulario para crear nuevos vehículos,
     * una vez validados todos los datos se llama al método insertarVehiculo para
     * construir un nuevo objeto vehículo.
     * 
     */
    public static void construirFormularioNuevoVehiculo(){

        /*Pedimos los datos al usuario y si el dato que se pide necesita ser validado
        llamamos al método encargado de elegir que tipo de validación necesita el dato.
        */

        Scanner datoIntroducido = new Scanner(System.in);
                
        comprobarDato(1, "Introduce el nombre del propietario del vehículo (nombre y 2 apellidos - Max. 40 caracteres): ");
        
        comprobarDato(4, "Introduce el DNI del propietario del vehículo (NIE o DNI con letra): ");
        
        comprobarDato(2, "Introduce la marca del vehículo: ");
        
        comprobarDato(3, "Introduce la descripción del vehículo: ");
        
        comprobarDato(8, "Introduce la matrícula del vehículo (formato 1111AAA): ");
        
        comprobarFechaMatriculacion();
        
        comprobarDato(9, "Introduce los kilómetros del vehículo: ");
        
        comprobarDato(10, "Introduce el precio del vehículo: ");
        
        //Una vez comprobados todos los parámetros, llamamos al método insertarVehiculo
        
        insertarVehiculo(marca, matricula, propietario, descripcion, km, dni, precio, fechaMatr);

    } //fin metodo SolicitarDatos
    
    /**
     * Método que se encarga de recoger los distintos datos que el usuario inserta
     * por teclado para crear un nuevo vehículo, si el dato a insertar requiere ser validado,
     * este método se encarga de llamar a los distintos tipos de validación existentes
     * en la clase ValidarDatos, según se haya indicado con el parámetro 'tipoValidacion',
     * también se encarga de imprimir en consola el enunciado que el
     * usuario leerá para que pueda introducir el dato correspondiente.
     * 
     * @param tipoValidacion 1 para validar propietario, 2 para validar dni, 3 para validar
     * 3 para validar marca, 4 para validar descripción, 5 para validar día de matriculación
     * 6 para validar mes de matriculación, 7 para validar año de matriculación, 8 para validar
     * matrícula, 9 para validar kilómetros, 10 para validar precio.
     * @param enunciado tipo de enunciado que se imprimirá por consola para que el usuario introduzca
     * el dato correspondiente.
     */
    public static void comprobarDato(int tipoValidacion, String enunciado){
        /* Valor de parámetros:

        1 - Validar propietario del vehículo
        2 - Validar marca
        3 - Validar descripción
        4 - Validar DNI
        5 - Validar día
        6 - Validar mes
        7 - Validar año
        8 - Validar matrícula
        9 - Validar kilómetros 
        10 - Validar precio */
        
        String dato= null;
        
        while(dato== null) {
            
            if (enunciado != null) {
                System.out.print(enunciado);
                dato = new Scanner(System.in).nextLine();  
            }
            
            
            try {
               switch (tipoValidacion) {
                
                    case 1:
                        
                        ValidarDatos.validarNombre(dato);
                        propietario = dato;
                        break;
                        
                    case 2:
                        
                        marca = dato;
                        break;
                        
                    case 3:
                        
                        descripcion = dato;
                        break;

                    case 4:
                        
                        ValidarDatos.validarDNI(dato.toUpperCase());
                        dni = dato;
                        break;

                    case 5:

                        ValidarDatos.validarDiaMatriculacion(Integer.valueOf(dato));
                        diaMatr = Integer.valueOf(dato);
                        
                        break;
                        
                    case 6:
                        
                        
                        ValidarDatos.validarMesMatriculacion(Integer.valueOf(dato));
                        mesMatr = Integer.valueOf(dato);
                        
                        break;
                        
                    case 7:
                        
                        
                        ValidarDatos.validarAnioMatriculacion(Integer.valueOf(dato));
                        anioMatr = Integer.valueOf(dato);
                        
                        break;
                        
                        
                    case 8:
                        
                        ValidarDatos.validarMatricula(dato.toUpperCase());
                        matricula = dato.toUpperCase();
                        break;
                        
                    case 9:
                        
                        ValidarDatos.validarKms(Integer.valueOf(dato));
                        km = Integer.valueOf(dato);
                        break;
                    
                    case 10:
                        
                        ValidarDatos.validarPrecio(Float.valueOf(dato));
                        precio = Float.valueOf(dato);
                        break;
                }
                
                
            }catch (NumberFormatException e) {
                
                System.out.println("El dato introducido debe ser numérico.");
                dato = null;
                
            }catch (Exception e) {
                
                System.out.println(e.getMessage());
 
                dato = null;
                
            } //fin switch case
            
            
        } //fin try
        
    } //fin comprobarDato()
   
    /**
     * Método que se encarga de comprobar si la fecha introducida es una fecha correcta.
     * 
     */
    public static void comprobarFechaMatriculacion() {
        while (fechaMatr == null) {
            
            comprobarDato(5, "Introduce el día de la matriculación del vehículo: ");
            
            comprobarDato(6, "Introduce el mes de la matriculación del vehículo: ");
            
            comprobarDato(7, "Introduce el año de la matriculación del vehículo: ");
            try {
                fechaMatr = ValidarDatos.validarFechaMatriculacion(anioMatr, mesMatr, diaMatr);
            } catch (Exception e) {
                
                System.out.println(e.getMessage());
                
            }
            
        }
    }
    
    /**
     * Método que se encarga de llamar al método encargado de crear el objeto de la
     * clase Vehículo.
     * 
     * @param marca a insertar en el objeto
     * @param matricula a insertar en el objeto
     * @param propietario a insertar en el objeto
     * @param descripcion a insertar en el objeto
     * @param km a insertar en el objeto
     * @param dni a insertar en el objeto
     * @param precio a insertar en el objeto
     * @param fechaMatr a insertar en el objeto
     */
    public static void insertarVehiculo(String marca, String matricula, String propietario, String descripcion, int km, String dni, float precio, LocalDate fechaMatr) {
        
        /*Una vez pedidos todos los parámetros, inicializamos el objeto con los 
        valores introducidos. Este método devolverá 0 si el vehículo se crea con
        éxito, -1 si el concesionario está lleno y -2 si la matrícula ya existía.*/
        
        int insercionRealizada = concesionario.insertaVehiculo(marca, matricula, propietario, descripcion, km, dni, precio, fechaMatr);

        switch (insercionRealizada) {

            case 0:
                System.out.println("Vehículo dado de alta con éxito.");
                break;
            case -1:
                System.out.println("Concesionario lleno, debe borrar un vehículo antes.");
                break;
            case -2:
                System.out.println("La matrícula introducida ya existe en la base de datos.");
                break;
                
        } //fin switch
        
    } //fin insertarVehiculo()
    
    /**
     * Método que se encarga de ejecutar el método de la clase Concesionario,
     * el cual a su vez de encarga de modificar los kilómetros de un venículo
     * ya existente.
     */
    public static void opcionActualizarKm() {
        
        System.out.print("\nIntroduce la matrícula del vehículo de la cual deseas modificar los kilómetros: ");
        
        String matricula = new Scanner(System.in).nextLine().toUpperCase(); //guardamos la matrícula en mayúscula
        
        if (concesionario.buscaVehiculo(matricula) == null) {
            
            System.out.println("\nEl vehículo no existe."); //si no encuentra la matrícula no se hacen modificaciones.
            
        } else {
            /* Si se encuentra una matrícula coincidiente, se piden los kilómetros nuevos al usuario
            y se pasa por parámetro al método del objeto concesionario que se encarga de actualizar
            los kilómetros del vehículo. Devuelve true si se actualizan correctamente, o false si
            no se actualizan.*/
            System.out.print("\nIntroduce el número nuevo número de kilómetros del vehículo seleccionado: ");
            
            int nuevosKm = 0;
            
            nuevosKm = new Scanner(System.in).nextInt();
            boolean kmActualizados = concesionario.actualizaKms(matricula, nuevosKm);
            
            if (kmActualizados) System.out.println("\nEl vehículo ha actualizado los kilómetros correctamente.");
            
            
        } //fin if-else
        
    } //fin opcionActualizarKm()
    
    /**
     * Método encargado de ejecutar el método de la clase concesionario que se encarga
     * de devolver los datos de un vehículo a partir de la matrícula
     * que introduzca el usuario.
     */
    public static void opcionObtenerDatos() {
        /*Pedimos una matrícula al usuario y la pasamos a mayúscula. Llamamos al
        método buscaVehiculo del objeto concesionario pasándole la matrícula.
        Si la cadena de texto que devuelve es null, pondremos sun mensaje en pantalla. 
        Si no, imprimimos la cadena por pantalla.
        
        */
        System.out.print("\nIntroduce la matrícula del vehículo de la cual deseas obtener los datos: ");
        
        String datos = concesionario.buscaVehiculo(new Scanner(System.in).nextLine().toUpperCase());
        
        if (datos == null) System.out.println("\nEl vehículo no existe");
        else System.out.println(datos);
        
    } //fin opcionObtenerDatos()
    
    /**
     * Método encargado de ejecutar el método del objeto concesionario que se encarga
     * de borrar un vehículo a partir de la matrícula introducida por el usuario.
     * 
     */
    public static void opcionBorrarVehiculo(){
        
        /* pedimos una matrícula al usuario y llamamos al método buscaVehiculo
        del objeto concesionario, si devuelve null, pondremos un mensaje en pantalla,
        si por el contrario, encuentra una matrícula, preguntaremos al usuario
        si desea eliminar el vehículo, procediendo a llamar al método borraVehiculo
        si el usuario acepta. */
        System.out.print("\nIntroduce la matrícula del vehículo que deseas eliminar: ");
        String matricula = new Scanner(System.in).nextLine().toUpperCase();
        
        if (concesionario.buscaVehiculo(matricula) == null) System.out.println("\nEl vehículo no existe");
        else {
            
            System.out.println("¿Estás seguro que deseas borrar el vehículo seleccionado? (esta operación no se puede deshacer)");
            System.out.println("Pulse S para borrar. Cualquier otra tecla para cancelar.");
            
            String opcion = new Scanner(System.in).nextLine().toUpperCase();
            
            if (opcion.equals("S")) { 
                if (concesionario.borraVehiculo(matricula)) System.out.println("Vehículo eliminado con éxito.");
                
                
                else System.out.println("El vehículo no se ha eliminado.");
            }
            else System.out.println("\nEl vehículo no ha sido eliminado");
            
        } //fin if - else
        
    } //fin opcionBorrarVehiculo()
    
} //fin clase Principal
