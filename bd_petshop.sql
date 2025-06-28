CREATE DATABASE petshop;
USE petshop;

-- Tabla: usuario
CREATE TABLE usuario (
    usuario_id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    role VARCHAR(100) NOT NULL,
    PRIMARY KEY (usuario_id),
    UNIQUE KEY usuario_email_unique (email),
    UNIQUE KEY usuario_username_unique (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: categoria
CREATE TABLE categoria (
    categoria_id INT AUTO_INCREMENT NOT NULL,
    categoria_nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (categoria_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: producto
CREATE TABLE producto (
    codigo INT AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    categoria_id INT NOT NULL,
    PRIMARY KEY (codigo),
    CONSTRAINT producto_categoria_fk FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: venta
CREATE TABLE venta (
    venta_id INT AUTO_INCREMENT NOT NULL,
    usuario_id INT NOT NULL,
    fecha DATETIME NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (venta_id),
    CONSTRAINT venta_usuario_fk FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla: detalle_venta
CREATE TABLE detalle_venta (
    detalle_id INT AUTO_INCREMENT NOT NULL,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_u DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (detalle_id),
    CONSTRAINT detalle_venta_fk FOREIGN KEY (venta_id) REFERENCES venta(venta_id) ON DELETE CASCADE,
    CONSTRAINT detalle_producto_fk FOREIGN KEY (producto_id) REFERENCES producto(codigo) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Datos iniciales: categorías
INSERT INTO categoria (categoria_nombre) VALUES
    ('Alimento'),
    ('Juguetes'),
    ('Accesorios'),
    ('Higiene y Cuidado');

-- Datos iniciales: productos
INSERT INTO producto (nombre, precio, stock, categoria_id) VALUES
    ('Purina Perros', 25.50, 100, 1),
    ('Hills Diet Gatos', 12.30, 80, 1),
    ('Cuerda Perros', 15.00, 60, 2),
    ('Pelota Gatos', 8.20, 100, 2),
    ('Collar Perros', 20.00, 40, 3),
    ('Correa Retráctil', 35.00, 25, 3),
    ('Shampoo', 18.00, 40, 4),
    ('Cepillo de Higiene', 12.00, 50, 4);

-- Datos iniciales: usuarios
INSERT INTO usuario (username, password, nombre, email, telefono, role) VALUES
    ('admin', 'admin1', 'Administrador', 'admin@petshop.com', '987650000', 'ADMIN'),
    ('operator', 'ope1', 'Operador', 'ope@petshop.com', '987659999', 'OPERATOR'),
    ('juan', 'juan1', 'Juan Pérez', 'juanperez@petshop.com', '983454321', 'CLIENT'),
    ('maria', 'maria1', 'María Quispe', 'mariaquispe@petshop.com', '986554322', 'CLIENT'),
    ('carlos', 'carlos1', 'Carlos Cano', 'carloscano@petshop.com', '911754323', 'CLIENT'),
    ('lucia', 'lucia1', 'Lucía Fernández', 'luciafernandez@petshop.com', '978464324', 'CLIENT'),
    ('pedro', 'pedro1', 'Pedro Sánchez', 'pedrosanchez@petshop.com', '987684325', 'CLIENT'),
    ('ana', 'ana1', 'Ana Martínez', 'anamartinez@petshop.com', '987654391', 'CLIENT');

-- Datos iniciales: ventas
INSERT INTO venta (usuario_id, fecha, total) VALUES
    (3, '2025-06-26 10:00:00', 69.00),
    (4, '2025-06-26 12:30:00', 43.40),
    (5, '2025-06-26 14:45:00', 67.30);

-- Datos iniciales: detalle de venta
INSERT INTO detalle_venta (venta_id, producto_id, cantidad, precio_u, subtotal) VALUES
    (1, 1, 2, 25.50, 51.00),
    (1, 7, 1, 18.00, 18.00),
    (2, 4, 2, 8.20, 16.40),
    (2, 8, 1, 12.00, 12.00),
    (2, 3, 1, 15.00, 15.00),
    (3, 5, 1, 20.00, 20.00),
    (3, 2, 1, 12.30, 12.30),
    (3, 6, 1, 35.00, 35.00);
    
SELECT * FROM categoria;
ALTER TABLE venta
ADD COLUMN status ENUM('PENDIENTE', 'COMPLETADA', 'CANCELADA') NOT NULL DEFAULT 'PENDIENTE';
DESCRIBE venta;