/*1*/
SELECT nombre, salario 
FROM EMPLEADO 
WHERE salario > 1000 
ORDER BY nombre ASC;

/*2*/
SELECT nombre 
FROM EMPLEADO 
WHERE comision > salario*0.2;

/*3*/
SELECT E.codemple, D.coddpto, nombre, (salario+ NVL(comision, 0))*166.386 || ' ptas.' AS "salario en ptas." 
FROM EMPLEADO E JOIN DPTO D ON E.coddpto = D.coddpto
WHERE  salario + NVL(comision, 0) > 1800
ORDER BY D.coddpto ASC, nombre ASC;

/*4*/
SELECT nombre
FROM EMPLEADO
WHERE salario >= 
    (SELECT salario*1.05 
    FROM empleado 
    WHERE UPPER(nombre) = 'MARIA' AND UPPER(APE1) = 'JAZMIN') 
ORDER BY NOMBRE ASC;

/*5*/
SELECT nombre || ' ' || ape1 || ' ' || ape2 AS "NOMBRE", TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) || ' años' as "Antigüedad"
FROM EMPLEADO
ORDER BY fechaingreso DESC;

/*6*/
SELECT nombre
FROM EMPLEADO
WHERE coddpto = ANY(SELECT coddpto FROM dpto WHERE presupuesto > 50000);

/*7*/
SELECT  nombre || ' ' || ape1 || ' ' || ape2 AS NOMBRE
FROM EMPLEADO
WHERE salario + NVL(comision,0) = (SELECT MAX(salario+NVL(comision,0)) FROM empleado);

/*8*/
SELECT nombre
FROM EMPLEADO
WHERE salario < 
    (SELECT MIN(salario) 
    FROM EMPLEADO 
    WHERE coddpto = 1)
ORDER BY nombre;

/*9*/
SELECT nombre
FROM EMPLEADO E JOIN DPTO D ON E.coddpto = D.coddpto
WHERE codemplejefe = 1;

/*10*/
SELECT nombre || ' ' || ape1 || ' ' || ape2 AS "NOMBRE"
FROM EMPLEADO
WHERE SUBSTR(UPPER(ape1),0,1) IN ('P', 'Q', 'R', 'S');

/*11*/
SELECT nombre
FROM EMPLEADO
WHERE UPPER(INSTR(nombre, 'JUAN')) = 1;

/*12*/
SELECT nombre
FROM EMPLEADO
WHERE UPPER(localidad) = ANY (SELECT UPPER(localidad) FROM CENTRO);

/*13*/
SELECT nombre
FROM EMPLEADO
WHERE codemple IN (SELECT codemplejefe FROM DPTO) AND salario = (SELECT MAX(salario) FROM EMPLEADO);

/*14*/
SELECT nombre || ' ' || ape1 || ' ' || ape2 AS "NOMBRE", salario
FROM EMPLEADO
WHERE salario > (SELECT MAX(salario)*0.6 FROM EMPLEADO)
ORDER BY nombre ASC, ape1 ASC, ape2 ASC;

/*15*/
SELECT COUNT(DISTINCT localidad) AS "LOCALIDADES DE EMPLEADOS"
FROM EMPLEADO;

/*16*/
SELECT nombre || ' ' || ape1 || ' ' || ape2 AS "NOMBRE"
FROM EMPLEADO
WHERE salario = (SELECT MAX(salario) FROM EMPLEADO);

/*17*/
SELECT localidad, COUNT(codemple) as "Nº DE EMPLEADOS"
FROM EMPLEADO
GROUP BY localidad
HAVING COUNT(codemple) > 3;

/*18*/
SELECT denominacion, COUNT(codemple) AS "Nº DE EMPLEADOS", SUM(salario) AS "SUMA DE SALARIOS", SUM(ALL comision) AS "SUMA DE COMISIONES"
FROM DPTO D JOIN EMPLEADO E ON D.coddpto = E.coddpto
WHERE E.coddpto IN (SELECT coddpto FROM EMPLEADO WHERE salario > 1700)
GROUP BY denominacion;


/*19*/
SELECT denominacion
FROM DPTO D JOIN EMPLEADO E ON D.coddpto = E.coddpto
GROUP BY denominacion
HAVING COUNT(D.coddpto) >= ALL (SELECT COUNT(coddpto) FROM EMPLEADO GROUP BY coddpto);

/*20*/
SELECT C.codcentro, D.denominacion
FROM CENTRO C LEFT OUTER JOIN DPTO D ON C.codcentro = D.codcentro;

/*21*/
SELECT denominacion
FROM DPTO
WHERE coddptodepende IS NULL;

/*22*/
SELECT nombre, denominacion
FROM DPTO D LEFT OUTER JOIN EMPLEADO E ON D.coddpto = E.coddpto;

/*23*/
SELECT DDEPENDE.Denominacion AS "DEPARTAMENTO", D.Denominacion AS "DPTO SUPERIOR"
FROM DPTO DDEPENDE LEFT OUTER JOIN DPTO D ON DDEPENDE.coddptodepende = D.coddpto;

/*24*/
SELECT nombre, DECODE(comision, NULL, 'No tiene comisión', 'Tiene comisión') AS "COMISIÓN"
FROM EMPLEADO
ORDER BY NOMBRE;

/*25*/
SELECT localidad
FROM CENTRO
WHERE UPPER(localidad) NOT IN (SELECT UPPER(localidad) FROM empleado)
ORDER BY localidad;

/*26*/
SELECT localidad
FROM CENTRO
WHERE UPPER(localidad) = ANY (SELECT UPPER(localidad) FROM empleado)
ORDER BY localidad;

/*27/28 - 1*/
SELECT nombre, 
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) || ' años' AS "ANTIGÜEDAD",
(CASE
WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) BETWEEN 1 AND 5 
THEN '100 Euros'
WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) BETWEEN 6 AND 10 
THEN TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) * 50 || ' Euros '
WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) BETWEEN 11 AND 20 
THEN TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) * 70 || ' Euros '
ELSE TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) * 100 || ' Euros '
END) AS "GRATIFICACIÓN"
FROM EMPLEADO
ORDER BY nombre;

/*27/28 - 2*/
SELECT nombre, 
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) || ' años' AS "ANTIGÜEDAD",
'100 Euros' AS "GRATIFICACION"
FROM EMPLEADO
WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) BETWEEN 1 AND 5
UNION 
SELECT nombre, 
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) || ' años' AS "ANTIGÜEDAD",
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) * 50 || ' Euros' AS "GRATIFICACION"
FROM EMPLEADO
WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) BETWEEN 6 AND 10
UNION
SELECT nombre,
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) || ' años' AS "ANTIGÜEDAD",
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) * 70 || ' Euros' AS "GRATIFICACION"
FROM EMPLEADO
WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) BETWEEN 11 AND 20
UNION
SELECT nombre,
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) || ' años' AS "ANTIGÜEDAD",
TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) * 100 || ' Euros' AS "GRATIFICACION"
FROM EMPLEADO
WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, fechaingreso)/12, 0) > 20
ORDER BY nombre;

/*29*/

SELECT nombre || ' '  || ape1 || ' '  || ape2 as "NOMBRE"
FROM EMPLEADO E JOIN DPTO D ON E.coddpto = D.coddpto
WHERE E.codemple NOT IN (SELECT codemplejefe FROM DPTO);
