CREATE DATABASE cinema;
USE cinema;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    passwd VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    date_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE cine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(100),
    ciudad VARCHAR(100),
    nombre VARCHAR(255)
);

CREATE TABLE pelicula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    productor VARCHAR(255),
    clasificacion VARCHAR(50),
    duracion INT NOT NULL, -- Duración en minutos
    genero VARCHAR(100)
);

CREATE TABLE funciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cine INT NOT NULL,
    id_pelicula INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    sala INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    capacidad INT NOT NULL,
    capacidad_actual INT DEFAULT 0,
    FOREIGN KEY (id_cine) REFERENCES cine(id) ON DELETE CASCADE,
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id) ON DELETE CASCADE
);

CREATE TABLE transaccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    id_funcion INT NOT NULL,
    id_cine INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    boletos INT NOT NULL,
    precio_boleto DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) GENERATED ALWAYS AS (boletos * precio_boleto) STORED,
    FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (id_funcion) REFERENCES funciones(id) ON DELETE CASCADE,
    FOREIGN KEY (id_cine) REFERENCES cine(id) ON DELETE CASCADE
);

Drop table transaccion;
DROP TABLE funciones;
Drop table pelicula;
Drop table cine;
Drop table user;


SET SQL_SAFE_UPDATES = 0;
