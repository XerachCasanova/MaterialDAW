DROP TABLE ALUMNADO CASCADE CONSTRAINTS;
DROP TABLE ALUMNADORECIENTE CASCADE CONSTRAINTS;
DROP TABLE GRUPO CASCADE CONSTRAINTS;
DROP TABLE TURNO CASCADE CONSTRAINTS;

CREATE TABLE TURNO (
id INTEGER,
nombreTurno VARCHAR2(100),
horaInicio VARCHAR2(6));

CREATE TABLE GRUPO (
id CHAR(4),
nombreGrupo VARCHAR2(100),
turno INTEGER,
maxEstudiantes INTEGER);

CREATE TABLE ALUMNADO (
expedienteA INTEGER,
nombreA VARCHAR(100),
ap1 VARCHAR2(99),
ap2 VARCHAR2(99),
fechaNacA DATE,
repetidor CHAR(2),
email VARCHAR(99),
grupo CHAR(4));


ALTER TABLE TURNO ADD CONSTRAINT pk_turno PRIMARY KEY(id);
ALTER TABLE GRUPO ADD CONSTRAINT pk_grupo PRIMARY KEY(id);
ALTER TABLE ALUMNADO ADD CONSTRAINT pk_alumnado PRIMARY KEY(expedienteA);

ALTER TABLE GRUPO ADD CONSTRAINT fk_grupo FOREIGN KEY(turno) REFERENCES TURNO (id);
ALTER TABLE ALUMNADO ADD CONSTRAINT fk_alumnado FOREIGN KEY (grupo) REFERENCES GRUPO (id);
ALTER TABLE ALUMNADO ADD CONSTRAINT ck_repetidor CHECK (repetidor IN ('si', 'no'));

INSERT INTO TURNO VALUES
(1, 'MAÑANA', '8:00');

INSERT INTO TURNO VALUES
(2, 'TARDE', '14:00');

INSERT INTO TURNO VALUES
(3, 'NOCHE', '19:00');

INSERT INTO GRUPO VALUES
('DAWA', 'Desarrollo de Aplicaciones Web', 1, 22);

INSERT INTO GRUPO VALUES
('DAWB', 'Desarrollo de Aplicaciones Multiplataforma', 2, 15);

INSERT INTO GRUPO VALUES
('ASIR', 'Administración de Sistemas Informáticos', 3, 25);


INSERT INTO ALUMNADO VALUES
(1, 'XERACH', 'CASANOVA', 'CABRERA', '21/10/84', 'si', 'xerach@xerach.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(2, 'JUAN', 'PÉREZ', 'RODRÍGUEZ', '20/11/88', 'no', 'juan@juan.com', 'DAWA');

INSERT INTO ALUMNADO (expedienteA, nombreA, ap1, fechaNacA, repetidor, email, grupo) VALUES
(3, 'ALBA', 'GUTIERREZ', '21/10/84', 'no', 'alba@alba.com', 'DAWA');


/*desde aqui*/
INSERT INTO ALUMNADO (expedienteA, nombreA, ap1, fechaNacA, repetidor, email, grupo) VALUES
(5, 'MARCOS', 'GONZÁLEZ', '03/02/96', 'no', 'marcos@marcos.com', 'DAWA');

INSERT INTO ALUMNADO (expedienteA, nombreA, ap1, fechaNacA, repetidor, email, grupo) VALUES
(4, 'NATALIA', 'RAMIREZ', '01/02/90', 'no', 'natalia@natalia.com', 'DAWA');

INSERT INTO ALUMNADO (expedienteA, nombreA, ap1, fechaNacA, repetidor, email, grupo) VALUES
(6, 'TOMÁS', 'GONZÁLEZ', '02/08/92', 'si', 'tomas@tomas.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(7, 'LAURA', 'AFONSO', 'AFONSO', '06/10/99', 'si', 'laura@laura.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(8, 'MARIA', 'ALONSO', 'PAEZ', '02/02/98', 'no', 'maria@maria.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(9, 'LORENA', 'PEREZ', 'CABRERA', '16/11/90', 'no', 'lorena@lorena.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(10, 'SOLEDAD', 'RODRIGUEZ', 'RODRIGUEZ', '02/10/82', 'no', 'soledad@soledad.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(11, 'MARTA', 'LOPEZ', 'MORALES', '12/12/89', 'no', 'marta@marta.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(12, 'JONATHAN', 'MARTINEZ', 'LEON', '01/06/98', 'no', 'jonathan@jonathan.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(13, 'ALBERTO', 'BELTRAN', 'LLANOS', '20/06/97', 'no', 'alberto@alberto.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(14, 'SAMUEL', 'LOPEZ', 'PIMIENTA', '22/05/94', 'no', 'samuel@samuel.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(15, 'ADAN', 'NUÑEZ', 'DIAZ', '31/12/96', 'no', 'adan@adan.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(16, 'JAVIER', 'BAEZ', 'EXPOSITO', '06/02/81', 'no', 'javier@javier.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(17, 'LILIANA', 'DENIZ', 'MACIAS', '20/07/99', 'no', 'liliana@liliana.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(18, 'EVA', 'PERAZA', 'DONIS', '12/10/84', 'no', 'eva@eva.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(19, 'RODRIGO', 'HERNANDEZ', 'ABRANTE', '21/10/84', 'no', 'rodrigo@rodrigo.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(20, 'MANUEL', 'MARTINEZ', 'BAUTE', '20/10/90', 'si', 'manuel2@manuel2.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(21, 'LIDIA', 'CARLOS', 'LEMUS', '21/11/95', 'si', 'lidia@lidia.com', 'DAWA');

INSERT INTO ALUMNADO VALUES
(22, 'ANTONIO', 'HERNANDEZ', 'MIRANDA', '07/10/92', 'no', 'antonio@antonio.com', 'DAWB');

INSERT INTO ALUMNADO VALUES
(23, 'RUBÉN', 'RODRÍGUEZ', 'CABRERA', '25/12/93', 'no', 'ruben@ruben.com', 'DAWB');

INSERT INTO ALUMNADO VALUES
(24, 'CLEMENTE', 'LOPEZ', 'GARCIA', '28/02/98', 'no', 'clemente@clemente.com', 'DAWB');

INSERT INTO ALUMNADO VALUES
(25, 'LORENZO', 'SUAREZ', 'LOPEZ', '01/04/84', 'no', 'lorenzo@lorenzo.com', 'ASIR');

CREATE TABLE ALUMNADORECIENTE (
expedienteA INTEGER,
nombreA VARCHAR(100),
ap1 VARCHAR2(99),
ap2 VARCHAR2(99),
fechaNacA DATE,
repetidor CHAR(2),
email VARCHAR(99));

INSERT INTO ALUMNADORECIENTE (expedienteA, nombreA, ap1, ap2, fechaNacA, repetidor, email) VALUES
(301, 'PEPA', 'JUANITA', 'PÉREZ', '03/02/96', 'no', 'pepa@pepa.com');

INSERT INTO ALUMNADORECIENTE (expedienteA, nombreA, ap1, ap2, fechaNacA, repetidor, email) VALUES
(302, 'AARON', 'GUTIERREZ', 'LOPEZ', '03/02/96', 'no', 'aaron@aaron.com');