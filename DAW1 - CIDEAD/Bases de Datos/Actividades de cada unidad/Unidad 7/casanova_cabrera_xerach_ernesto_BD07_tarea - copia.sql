/*

EJERCICIO 1

Crea el tipo de objetos "Personal" 
con los siguientes atributos:

codigo INTEGER,
dni VARCHAR2(10),
nombre VARCHAR2(30),
apellidos VARCHAR2(30),
sexo VARCHAR2(1),
fecha_nac DATE

*/
CREATE OR REPLACE TYPE Personal AS OBJECT(
    codigo INTEGER,
    dni VARCHAR2(10),
    nombre VARCHAR2(30),
    apellidos VARCHAR2(30),
    sexo VARCHAR2(1),
    fecha_nac DATE
);


/*
Crea, como tipo heredado de "Personal", 
el tipo de objeto "Responsable" con los 
siguientes atributos:

tipo  CHAR ,
antiguedad INTEGER 

*/

ALTER TYPE Personal NOT FINAL;

CREATE TYPE Responsable UNDER Personal(

    tipo CHAR,
    antiguedad INTEGER
 
);

/*Crea el tipo de objeto "Zonas" 
con los siguientes atributos:
codigo INTEGER, 
nombre VARCHAR2(20), 
refRespon REF Responsable, 
codigoPostal CHAR(5),

*/

CREATE OR REPLACE TYPE Zonas AS OBJECT(
    codigo INTEGER,
    nombre VARCHAR2(20),
    refRespon REF Responsable,
    codigoPostal CHAR(5)
);

/*
Crea, como tipo heredado de "Personal", 
el tipo de objeto "Comercial" con los 
siguientes atributos:

zonaComercial Zonas
*/

CREATE TYPE Comercial UNDER Personal(

    ZonaComercial Zonas

);

/*EJERCICIO 2

Crea un método constructor para el tipo de objetos 
"Responsable", en el que se indiquen como parámetros 
el código, nombre, primer apellido, segundo apellido 
y tipo. Este método debe asignar al atributo apellidos 
los datos de primer apellido y segundo apellido que 
se han pasado como parámetros, uniéndolos con un 
espacio entre ellos.
*/

ALTER TYPE Responsable ADD CONSTRUCTOR FUNCTION Responsable(
                                                codigo INTEGER, 
                                                nombre VARCHAR2, 
                                                primerApellido VARCHAR2, 
                                                segundoApellido VARCHAR2, 
                                                tipo CHAR)
                                                RETURN SELF AS RESULT CASCADE;
                                                
CREATE OR REPLACE TYPE BODY Responsable AS
    CONSTRUCTOR FUNCTION Responsable(
                    codigo INTEGER, 
                    nombre VARCHAR2, 
                    primerApellido VARCHAR2, 
                    segundoApellido VARCHAR2, 
                    tipo CHAR) 
                    RETURN SELF AS RESULT 
    IS
        BEGIN
            
            SELF.codigo := codigo;
            SELF.nombre := nombre;
            SELF.apellidos := primerApellido || ' ' || segundoApellido;
            SELF.tipo := tipo;
 
            RETURN;
        END;
END;

/*
EJERCICIO 3

Crea un método getNombreCompleto para el tipo de objetos 
Responsable que permita  obtener su nombre completo con 
el formato apellidos nombre

*/

ALTER TYPE Responsable ADD MEMBER FUNCTION getNombreCompleto RETURN VARCHAR2 CASCADE;

/*CREACIÓN DEL CUERPO DEL OBJETO, CON EL CONSTRUCTOR Y EL MÉTODO ANTERIORMENTE CREADO.*/

CREATE OR REPLACE TYPE BODY Responsable AS
    CONSTRUCTOR FUNCTION Responsable(
                    codigo INTEGER, 
                    nombre VARCHAR2, 
                    primerApellido VARCHAR2, 
                    segundoApellido VARCHAR2, 
                    tipo CHAR) 
                    RETURN SELF AS RESULT 
    IS
        BEGIN
            
            SELF.codigo := codigo;
            SELF.nombre := nombre;
            SELF.apellidos := primerApellido || ' ' || segundoApellido;
            SELF.tipo := tipo;
 
            RETURN;
        END;
        
    MEMBER FUNCTION getNombreCompleto RETURN VARCHAR2
    IS
    
        BEGIN
        
            RETURN SELF.apellidos || ' ' || SELF.nombre;
            
        END;
END;
   
/* EJERCICIO 4
Crea un tabla TablaResponsables de objetos Responsable. 
Inserta en dicha tabla dos objetos  Responsable.

codigo: 5
dni: 51083099F
nombre: ELENA
apellidos: POSTA LLANOS
sexo: F
fecha_nac: 31/03/1975
tipo: N
antiguedad: 4


El segundo objeto "Responsable" debes crearlo usando el método constructor 
que has realizado anteriormente. Debes usar los siguientes datos:

codigo: 6
nombre: JAVIER
apellidos: JARAMILLO HERNANDEZ
tipo: C
*/

CREATE TABLE TablaResponsables of Responsable;
   
DECLARE
    resp1 Responsable;
    resp2 Responsable;
BEGIN
    resp1 := NEW Responsable(5, '51083099F', 'ELENA', 'POSTA LLANOS', 'F', '31/03/1975', 'N', 4);
    resp2 := NEW Responsable(6, 'JAVIER', 'JARAMILLO', 'HERNÁNDEZ', 'C');
    
    INSERT INTO TablaResponsables values(resp1);
    INSERT INTO TablaResponsables values(resp2);
END;
/

/*EJERCICIO 5

Crea una colección VARRAY llamada ListaZonas en la que se puedan almacenar
hasta 10 objetos Zonas. Guarda en una instancia listaZonas1 
de dicha lista, dos Zonas

codigo: 1
nombre: zona 1
refResponsable: Referencia al responsable  cuyo codigo es 5
codigo postal: 06834

codigo: 2
nombre: zona 2
refResponsable: Referencia al responsable cuyo DNI es 51083099F.
codigo postal: 28003
*/


DECLARE
    
    resp1_Ref REF Responsable;
    resp2_Ref REF Responsable;
    Type ListaZonas IS VARRAY(10) OF Zonas;
    listaZonas1 ListaZonas := ListaZonas();
    
    
BEGIN
    SELECT REF(r) INTO resp1_Ref FROM TablaResponsables r WHERE r.codigo = '5';
    SELECT REF(r) INTO resp2_Ref FROM TablaResponsables r WHERE r.dni = '51083099F';
    
    listaZonas1.EXTEND;
    listaZonas1(1) := NEW Zonas(1, 'Zona 1', resp1_Ref, '06834');
    
    listaZonas1.EXTEND;
    listaZonas1(2) := NEW Zonas(2, 'Zona 2', resp2_Ref, '28003');
    
END;
/


/*
EJERCICIO 6

Crea una tabla TablaComerciales de objetos Comercial. 
Inserta en dicha tabla las siguientes filas:
codigo: 100
dni: 23401092Z
nombre: MARCOS
apellidos: SUAREZ LOPEZ
sexo: M
fecha_nac: 30/3/1990
zonacomercial: objeto creado anteriormente para la zona 1

codigo: 102
dni: 6932288V
nombre: ANASTASIA
apellidos:  GOMES PEREZ
sexo: F
fecha_nac: 28/11/1984
zonacomercial: objeto que se encuentre en la segunda posición de "listaZonas1" 
	    (debe tomarse de la lista)

NOTA PERSONAL. PARA PODER SEGUIR ESTE EJERCICIO, DEBEMOS COMENZAR
CON EL CÓDIGO DEL EJERCICIO ANTERIOR Y SEGUIR IMPLEMENTANDO,
PARA PODER USAR LAS POSICIONES DEL VARRAY listaZonas1.
*/

CREATE TABLE TablaComerciales of Comercial;

DECLARE
    
    resp1_Ref REF Responsable;
    resp2_Ref REF Responsable;
    Type ListaZonas IS VARRAY(10) OF Zonas;
    listaZonas1 ListaZonas := ListaZonas();
    comer1 Comercial;
    comer2 Comercial;
    
BEGIN
    SELECT REF(r) INTO resp1_Ref FROM TablaResponsables r WHERE r.codigo = '5';
    SELECT REF(r) INTO resp2_Ref FROM TablaResponsables r WHERE r.dni = '51083099F';
 
    listaZonas1.EXTEND;
    listaZonas1(1) := NEW Zonas(1, 'Zona 1', resp1_Ref, '06834');
    listaZonas1.EXTEND;
    listaZonas1(2) := NEW Zonas(2, 'Zona 2', resp2_Ref, '28003');
    
    comer1 := NEW Comercial(100, '23401092Z','MARCOS','SUAREZ LOPEZ','M','30/3/1990',listaZonas1(1));
    comer2 := NEW Comercial(102, '6932288V','ANASTASIA','GOMES PEREZ','F','28/11/1984',listaZonas1(2));
    INSERT INTO TablaComerciales values(comer1);
    INSERT INTO TablaComerciales values(comer2);
END;
/

/*EJERCICIO 7
Obtener, de la tabla TablaComerciales, el Comercial que 
tiene el código 100, asignándoselo a una variable unComercial 
*/

DECLARE

    unComercial Comercial;
BEGIN
    
    SELECT VALUE(c) INTO unComercial FROM TablaComerciales c WHERE codigo=100;
    dbms_output.put_line('El comercial con código 100 es ' || unComercial.nombre || ' ' || unComercial.apellidos);
END;
/

/* EJERCICIO 8
Modifica el código del Comercial guardado en esa variable 
unComercial asignando el valor 101, y su zona debe ser la 
segunda que se había creado anteriormente. 
Inserta ese Comercial en la tabla TablaComerciales  
*/

/*NOTA PERSONAL: COMO YA NO CONSERVO EL ARRAY DONDE TENÍA GUARDADOS LOS OBJETOS ZONA Y ESOS OBJETOS SON DESTRUIDOS
CUANDO FINALIZA UN BLOQUE DE CÓDIGO, GUARDARÉ EN UNA VARIABLE DE TIPO OBJETO ZONA EL OBJETO ZONA CREADO PARA EL
COMERCIAL CON CÓDIGO 102, YA QUE TIENE ASIGNADA ESA ZONA EN CONCRETO.*/

DECLARE
    unComercial Comercial;
    zonaComer Zonas;
BEGIN
    
    SELECT VALUE(c) INTO unComercial FROM TablaComerciales c WHERE codigo=100;
    
    
    SELECT c.ZonaComercial.* into zonaComer FROM TablaComerciales c where c.ZonaComercial.codigo = 2;

    unComercial.codigo := 101;
    unComercial.zonaComercial := zonaComer;
    
    INSERT INTO TablaComerciales values(unComercial);

END;
/

/*EJERCICIO 9

Crea un método MAP ordenarZonas para el tipo Zonas. Este método debe retornar el nombre completo 
del Responsable al que hace referencia cada zona. Para obtener el nombre debes utilizar el método 
getNombreCompleto que se ha creado anteriormente.

*/

ALTER TYPE Zonas ADD MAP MEMBER FUNCTION ordenarZonas RETURN VARCHAR2 CASCADE;

CREATE OR REPLACE TYPE BODY Zonas AS
    MAP MEMBER FUNCTION ordenarZonas RETURN VARCHAR2
    IS
    
    resp Responsable;

        BEGIN
            SELECT DEREF(refRespon) INTO resp FROM Dual;

            RETURN resp.getNombreCompleto();
        END;
END;
/

/*
EJERCICIO 10.
Realiza una consulta de la tabla TablaComerciales 
ordenada por zonaComercial para comprobar el funcionamiento del método MAP.  
*/

SELECT * FROM tablaComerciales ORDER BY zonacomercial

/*
NOTA PERSONAL:
HE CREADO UN BLOQUE DE CÓDIGO QUE HACE LA FUNCIÓN DE LA CONSULTA ANTERIOR.
EN ESTE BLOQUE, SE MUESTRA POR PANTALLA EL NOMBRE DE CADA COMERCIAL
He creado un bloque de código donde se muestra por pantalla el código del comercial,
su nombre y apellidos, la zona asignada y el jefe de la zona asignada.

Para poder trabajar con todos esos datos he tenido que crear un objeto zona en el que
se guarda el objeto zona contenido en la columna zonaComercial de cada fila de la
tabla de comerciales, una referencia al objeto Responsable para poder guardar en él
el objeto referencia responmsable que está dentro del objeto zona y un objeto responsable
para poder mostrar el contenido de la referencia usando DEREF.
*/

DECLARE


    CURSOR cComerciales IS SELECT * FROM tablaComerciales ORDER BY zonacomercial;
    
    zona Zonas;
    
    resp_ref REF Responsable;
    resp Responsable;
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('Listado de comerciales ordenados por el nombre y apellidos del jefe de zona.');
    FOR cComer_reg IN cComerciales LOOP
        
        SELECT zonaComercial INTO zona FROM tablaComerciales WHERE codigo = cComer_reg.codigo;
        
        resp_ref := zona.refRespon;
        SELECT DEREF(resp_ref) INTO resp FROM Dual;
        
        DBMS_OUTPUT.PUT_LINE('Código de comercial: ' || cComer_reg.codigo || ', Nombre: ' || cComer_reg.nombre || ' ' || cComer_reg.apellidos || 
                            '. Zona asignada: ' || cComer_reg.zonacomercial.nombre || '. Nombre de responsable de zona: ' || resp.getNombreCompleto);
    END LOOP;
        

END;
