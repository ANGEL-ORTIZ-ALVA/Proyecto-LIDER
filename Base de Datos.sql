create database IF NOT EXISTS LIDER2024_5;
use LIDER2024_5;

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('cliente', 'trabajador', 'jefe_de_almacen', 'administrador') NOT NULL,
    estado ENUM('activo', 'suspendido') NOT NULL DEFAULT 'activo',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/*
UNIQUE (correo_electronico),
    UNIQUE (telefono)
*/
CREATE TABLE Clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
);

/*Tabla Roles de Trabajadores*/
CREATE TABLE Roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100)
);

/*Tabla personal*/
CREATE TABLE Personal (
    id_personal INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id),
    FOREIGN KEY (id_rol) REFERENCES Roles(id)
);

/*Tabla proveedores*/
CREATE TABLE Proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    rubro VARCHAR(30) NOT NULL,
    info_extra TEXT
);

/*Tabla insumos*/
CREATE TABLE Insumos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    cantidad_stock INT NOT NULL,
    unidad_medida VARCHAR(50),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    id_proveedor INT,
    fecha_ingreso DATE,
    fecha_vencimiento DATE,
    ubicacion_almacen VARCHAR(255),
    observaciones TEXT,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id)
);

/*Tabla solicitud de incidente*/
CREATE TABLE Solicitud_incidente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    fecha_solicitud DATE NOT NULL,
    incidente VARCHAR(255) NOT NULL,
    estado ENUM('pendiente', 'aprobada', 'rechazada') NOT NULL DEFAULT 'pendiente',
    fecha_aprobacion DATE,
    fecha_rechazo DATE,
    observaciones TEXT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);


/*Tabla Solicitud de Requerimientos de Insumos (SRI)*/
CREATE TABLE SRI (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_personal INT NOT NULL,
    id_soli_incidente INT NOT NULL,
    fecha_solicitud DATE NOT NULL,
    estado ENUM('pendiente', 'aprobada', 'rechazada') NOT NULL DEFAULT 'pendiente',
    fecha_aprobacion DATE,
    observaciones TEXT,
    FOREIGN KEY (id_personal) REFERENCES Personal(id_personal),
    FOREIGN KEY (id_soli_incidente) REFERENCES Solicitud_incidente(id)
);

/*Tabla de detalle de Solicitud de Requerimientos de Insumos (DetalleSRI)*/
CREATE TABLE DetalleSRI(
    id_SRI INT NOT NULL,
    id_insumo INT NOT NULL,
    cantidad INT NOT NULL,
    descripcion TEXT,
    PRIMARY KEY (id_SRI, id_insumo),
    FOREIGN KEY (id_insumo) REFERENCES Insumos(id),
    FOREIGN KEY (id_SRI) REFERENCES SRI(id)
);

/*Tabla comprobante de recepción de Solicitud de Requerimientos de Insumos (CRSRI)*/
CREATE TABLE CRSRI(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_SRI INT NOT NULL,
    fecha_recepcion DATE NOT NULL,
    hora_recepcion TIME NOT NULL,
    personal_recepcion INT NOT NULL,
    estado_recepcion ENUM('conforme', 'incompleto', 'no_recepcionado') NOT NULL DEFAULT 'no_recepcionado',
    descripcion TEXT,
    FOREIGN KEY (personal_recepcion) REFERENCES Personal(id_personal),
    FOREIGN KEY (id_SRI) REFERENCES SRI(id)
);

/*Tabla de Solicitud de Reabastecimiento de Almacén (SOREA)*/
CREATE TABLE SOREA(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT,
    recepcionista INT,
    fecha_solicitud DATE NOT NULL,
    fecha_recepcion DATE NOT NULL,
    descripcion VARCHAR(255),
    FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id),
    FOREIGN KEY (recepcionista) REFERENCES Personal(id_personal)
);

/*Tabla de detalles de Solicitud de Reabastecimiento (DetalleSOREA)*/
CREATE TABLE DetalleSOREA(
    id_SOREA INT NOT NULL,
    id_insumo INT NOT NULL,
    cantidad INT NOT NULL,
    unidad_medida VARCHAR(50),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    descripcion VARCHAR(255),
    PRIMARY KEY (id_SOREA, id_insumo),
    FOREIGN KEY (id_SOREA) REFERENCES SOREA(id),
    FOREIGN KEY (id_insumo) REFERENCES Insumos(id)
);

/*Tabla de comprobante de recepción de solicitud de reabastecimiento (CRSOREA)*/
CREATE TABLE CRSOREA(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_solicitud INT,
    fecha_recepcion DATE NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    estado ENUM('rechazado', 'pendiente', 'recepcionado') NOT NULL DEFAULT 'pendiente',
    descripcion TEXT,
    FOREIGN KEY (id_solicitud) REFERENCES SOREA(id)
);

/*Procedimientos almacenados*/
DELIMITER //

CREATE PROCEDURE usp_insertar_crsri(
    p_id_sri INT,
    p_fecha_recepcion DATE,
    p_hora_recepcion TIME,
    p_personal_recepcion INT,
    p_estado_recepcion varchar(15),
    p_descripcion TEXT
)
BEGIN
    INSERT INTO CRSRI (id_SRI, fecha_recepcion, hora_recepcion, personal_recepcion, estado_recepcion, descripcion)
    VALUES (p_id_sri, p_fecha_recepcion, p_hora_recepcion, p_personal_recepcion, p_estado_recepcion, p_descripcion);
END //

DELIMITER ;
select * from SRI;
select * from CRSRI;
CALL usp_insertar_crsri(2, '2024-05-18', '09:15:00', 2, 'conforme', 'prueba2');
CALL usp_editar_crsri(2, '2024-05-18', '09:15:00', 2, 'conforme', 'prueba455');

DELIMITER //

CREATE PROCEDURE usp_editar_crsri(
    p_id INT,
    p_fecha_recepcion DATE,
    p_hora_recepcion TIME,
    p_personal_recepcion INT,
    p_estado_recepcion ENUM('conforme', 'incompleto', 'no recepcionado'),
    p_descripcion TEXT
)
BEGIN
    UPDATE CRSRI
    SET fecha_recepcion = p_fecha_recepcion,
        hora_recepcion = p_hora_recepcion,
        personal_recepcion = p_personal_recepcion,
        estado_recepcion = p_estado_recepcion,
        descripcion = p_descripcion
    WHERE id = p_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE usp_eliminar_crsri(
    IN p_id INT
)
BEGIN
    DELETE FROM CRSRI WHERE id = p_id;
END //

DELIMITER ;

select * from Insumos;
