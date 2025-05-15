# Cinema Management System

A desktop application built in Java for managing the operations of a cinema. It allows users to register, view and filter movies, and purchase tickets. Administrators can manage users, theaters, movies, and showtimes.

## Features

- User registration and login
- Movie catalog viewing and filtering
- Screening scheduling by cinema and room
- Ticket purchases and transaction tracking
- Admin features for managing users, movies, and functions

## Technologies Used

- Java
- MySQL
- JDBC
- MVC Architecture

## Database Schema

The system uses a MySQL database with the following tables:

- `user`: stores users' information
- `cine`: stores cinemas and their locations
- `pelicula`: stores movies and their metadata
- `funciones`: stores showtime information
- `transaccion`: stores ticket purchases

To initialize the database, use the following SQL:

```sql
REATE DATABASE cinema;
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
```

## Getting Started

1. Clone the repository

```
git clone https://github.com/yourusername/cinema-management-system
```

2. Import the project into your Java IDE
3. Set your database credentials in the DB connection file
4. Run the application with the next command from the root of your project:

```
java -cp "bin;lib/*" App
```
