/*CON CURSOR VARIABLE*/
--Creo el procedimiento con dos parámetros del tipo del campo familias de la tabla agentes.

CREATE OR REPLACE PROCEDURE CambiarAgentesFamilia(
                            id_FamiliaOrigen agentes.familia%TYPE, 
                            id_FamiliaDestino agentes.familia%TYPE)
AS

    TYPE cursor_familias IS REF CURSOR RETURN agentes%ROWTYPE;
    cFamilias cursor_familias;
    
    
    v_NFamiliaOrigen number(8) :=0;
    v_NFamiliaDestino number(8) :=0;

BEGIN

    
    FOR cFamilias IN (SELECT * FROM agentes WHERE familia = id_FamiliaOrigen) LOOP
        v_NFamiliaOrigen := v_NFamiliaOrigen + 1;
    END LOOP;

    FOR cFamilias IN (SELECT * FROM familias WHERE identificador = id_FamiliaDestino) LOOP
        v_NFamiliaDestino := v_NFamiliaDestino + 1;
    END LOOP;
    
    IF v_NFamiliaDestino = 0 THEN
        RAISE_APPLICATION_ERROR(-20201, 'No hay códigos de familia que coincida con la familia indicada.');
    
    ELSIF v_NFamiliaOrigen = 0 THEN
        RAISE_APPLICATION_ERROR(-20202, 'No existen agentes con el código indicado.');
    
    ELSIF id_FamiliaOrigen = id_FamiliaDestino THEN
        RAISE_APPLICATION_ERROR(-20203, 'La categoría de la familia de origen es idéntica a la de destino. No se ha realizado ningún cambio.');
    
    ELSE
        UPDATE agentes SET familia = id_FamiliaDestino WHERE familia = id_FamiliaOrigen;
        DBMS_OUTPUT.PUT_LINE('Se han trasladado ' || v_NFamiliaOrigen || ' agentes de la familia '
                                || id_FamiliaOrigen || ' a la familia ' || id_FamiliaDestino);
    END IF; 

END;
/

/*bloque para probar el procedimiento.*/

DECLARE

    p_id_FamiliaOrigen agentes.familia%TYPE := &familiaOrigen;
    p_id_FamiliaDestino agentes.familia%TYPE := &familiaDestino;

BEGIN
    
    CambiarAgentesFamilia(p_id_FamiliaOrigen, p_id_FamiliaDestino);
    
END;
/

/*
Actividad 2.
Queremos controlar algunas restricciones a la hora de trabajar con agentes:

La longitud de la clave de un agente no puede ser inferior a 6.
La habilidad de un agente debe estar comprendida entre 0 y 9 (ambos inclusive).
La categoría de un agente sólo puede ser igual a 0, 1 o 2.
Si un agente tiene categoría 2 no puede pertenecer a ninguna familia y debe pertenecer a una oficina.  
Si un agente tiene categoría 1 no puede pertenecer a ninguna oficina y debe pertenecer  a una familia.  
Todos los agentes deben pertenecer  a una oficina o a una familia pero nunca a ambas a la vez.
Se pide crear un disparador para asegurar estas restricciones. El disparador deberá lanzar todos los errores que se puedan 
producir en su ejecución mediante errores que identifiquen con un mensaje adecuado por qué se ha producido dicho error.

Algunas de las restricciones implementadas con el disparador se pueden incorporar a la definición del esquema de la tabla 
utilizando el Lenguaje de Definición de Datos (Check,Unique,..).Identifica cuáles son y con qué tipo de restricciones las 
implementarías.
*/

CREATE OR REPLACE TRIGGER integridad_Agentes
BEFORE INSERT OR UPDATE ON agentes
FOR EACH ROW

BEGIN
    IF LENGTH(:NEW.clave) < 6 THEN
        RAISE_APPLICATION_ERROR(-20204, 'La clave del agente no puede ser menor que 6.');
        
    ELSIF (:NEW.habilidad < 0 OR :NEW.habilidad > 9) OR :NEW.habilidad IS NULL THEN
        RAISE_APPLICATION_ERROR(-20205, 'El valor de la habilidad del agente debe estar comprendido entre 0 y 9.');
        
    ELSIF :NEW.categoria < 0 OR :NEW.categoria  > 2 OR :NEW.categoria IS NULL THEN
        RAISE_APPLICATION_ERROR(-20206, 'El valor de la categoría del agente no puede ser nula y debe debe ser 0, 1 o 2.');
        
    ELSIF :NEW.categoria = 2 THEN
        IF :NEW.familia IS NOT NULL THEN
            RAISE_APPLICATION_ERROR(-20207, 'Un empleado con categoría 2 no puede pertenecer a ninguna familia');
            
        ELSIF :NEW.oficina IS NULL THEN
            RAISE_APPLICATION_ERROR(-20208, 'Un empleado con categoría 2 debe pertenecer a una oficina');
            
        END IF;
        
    ELSIF :NEW.categoria = 1 THEN
        IF :NEW.familia IS NULL THEN
            RAISE_APPLICATION_ERROR(-20209, 'Un empleado con categoría 1 debe pertenecer a  una familia');
            
        ELSIF :NEW.oficina IS NOT NULL THEN
            RAISE_APPLICATION_ERROR(-20210, 'Un empleado con categoría 1 no puede pertenecer a una oficina');
            
        END IF;
            
    ELSIF :NEW.familia IS NOT NULL AND :NEW.oficina IS NOT NULL THEN
             RAISE_APPLICATION_ERROR(-20211, 'Un empleado no puede pertenecer a una familia y a una oficina a la vez.');
             
    ELSIF :NEW.familia IS NULL AND :NEW.oficina IS NULL THEN
             RAISE_APPLICATION_ERROR(-20212, 'Un empleado debe pertenecer a una familia o a una oficina.');

    END IF;
END;
/

INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '12345', 0, 0, 1, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', -1, 0, 1, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', -9, 0, 1, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', 0, -1, 1, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', 0, 3, 1, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', 0, 2, 1, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', 0, 1, null, 1);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', 0, 1, null, null);
INSERT INTO AGENTES VALUES (1850, 'Xerach Casanova Cabrera', 'xcc', '123456', 0, 1, 1, 1);