CREATE DATABASE HackathonDB;
GO

USE HackathonDB;
GO

-- =========================
-- ROLES
-- =========================
CREATE TABLE ROLES(
    role_id INT IDENTITY(1,1),
    role_name VARCHAR(50) NOT NULL,
    description VARCHAR(150),
    status BIT NOT NULL DEFAULT 1,
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_ROLES PRIMARY KEY(role_id),
    CONSTRAINT UQ_ROLE_NAME UNIQUE(role_name)
);
GO

-- =========================
-- Users
-- =========================
CREATE TABLE Users(
    Users_id INT IDENTITY(1,1),
    role_id INT NOT NULL,
    usemame VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(120),
    phone VARCHAR(20),
    status BIT DEFAULT 1,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_Users PRIMARY KEY(Users_id),
    CONSTRAINT UQ_usersname UNIQUE(usemame),
    CONSTRAINT UQ_EMAIL UNIQUE(email),

    CONSTRAINT FK_Users_ROLES
        FOREIGN KEY(role_id)
        REFERENCES ROLES(role_id)
);
GO

-- =========================
-- CATEGORIES
-- =========================
CREATE TABLE CATEGORIES(
    category_id INT IDENTITY(1,1),
    category_name VARCHAR(100) NOT NULL,
    description VARCHAR(250),
    status BIT DEFAULT 1,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_CATEGORIES PRIMARY KEY(category_id),
    CONSTRAINT UQ_CATEGORY_NAME UNIQUE(category_name)
);
GO

-- =========================
-- SUPPLIERS
-- =========================
CREATE TABLE SUPPLIERS(
    supplier_id INT IDENTITY(1,1),
    company_name VARCHAR(120) NOT NULL,
    contact_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(120),
    address VARCHAR(200),
    status BIT DEFAULT 1,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_SUPPLIERS PRIMARY KEY(supplier_id),
    CONSTRAINT UQ_SUPPLIER_COMPANY UNIQUE(company_name)
);
GO

-- =========================
-- ProductS
-- =========================
CREATE TABLE ProductS(
    Product_id INT IDENTITY(1,1),
    category_id INT NOT NULL,
    supplier_id INT NOT NULL,
    Product_name VARCHAR(120) NOT NULL,
    description VARCHAR(250),
    unit_price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    minimum_stock INT NOT NULL DEFAULT 5,
    barcode VARCHAR(50),
    status BIT DEFAULT 1,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_ProductS PRIMARY KEY(Product_id),
    CONSTRAINT UQ_Product_BARCODE UNIQUE(barcode),

    CONSTRAINT CK_Product_PRICE CHECK(unit_price > 0),
    CONSTRAINT CK_Product_STOCK CHECK(stock >= 0),
    CONSTRAINT CK_Product_MIN_STOCK CHECK(minimum_stock >= 0),

    CONSTRAINT FK_ProductS_CATEGORIES
        FOREIGN KEY(category_id)
        REFERENCES CATEGORIES(category_id),

    CONSTRAINT FK_ProductS_SUPPLIERS
        FOREIGN KEY(supplier_id)
        REFERENCES SUPPLIERS(supplier_id)
);
GO

-- =========================
-- PURCHASES
-- =========================
CREATE TABLE PURCHASES(
    purchase_id INT IDENTITY(1,1),
    supplier_id INT NOT NULL,
    Users_id INT NOT NULL,
    purchase_date DATETIME2 DEFAULT GETDATE(),
    total DECIMAL(12,2) DEFAULT 0,
    status VARCHAR(20) DEFAULT 'COMPLETED',
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_PURCHASES PRIMARY KEY(purchase_id),

    CONSTRAINT CK_PURCHASE_TOTAL CHECK(total >= 0),
    CONSTRAINT CK_PURCHASE_STATUS CHECK(status IN ('PENDING','COMPLETED','CANCELLED')),

    CONSTRAINT FK_PURCHASES_SUPPLIERS
        FOREIGN KEY(supplier_id)
        REFERENCES SUPPLIERS(supplier_id),

    CONSTRAINT FK_PURCHASES_Users
        FOREIGN KEY(Users_id)
        REFERENCES Users(Users_id)
);
GO

-- =========================
-- PURCHASE DETAILS
-- =========================
CREATE TABLE PURCHASE_DETAILS(
    detail_id INT IDENTITY(1,1),
    purchase_id INT NOT NULL,
    Product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2,
    created_by VARCHAR(50) DEFAULT 'SYSTEM',
    updated_by VARCHAR(50),

    CONSTRAINT PK_PURCHASE_DETAILS PRIMARY KEY(detail_id),

    CONSTRAINT CK_DETAIL_QUANTITY CHECK(quantity > 0),
    CONSTRAINT CK_DETAIL_PRICE CHECK(unit_price > 0),
    CONSTRAINT CK_DETAIL_SUBTOTAL CHECK(subtotal >= 0),

    CONSTRAINT FK_DETAILS_PURCHASES
        FOREIGN KEY(purchase_id)
        REFERENCES PURCHASES(purchase_id),

    CONSTRAINT FK_DETAILS_ProductS
        FOREIGN KEY(Product_id)
        REFERENCES ProductS(Product_id)
);
GO

--ROLES--

INSERT INTO ROLES(role_name, description)
VALUES 
('ADMIN', 'Administrador del sistema'),
('EMPLOYEE', 'Empleado operativo'),
('MANAGER', 'Gestor de inventario');
GO

--Users--

INSERT INTO Users(role_id, usemame, password, full_name, email, phone)
VALUES
(1, 'admin', '123456', 'Administrador General', 'admin@demo.com', '999999999'),
(2, 'juan', '123456', 'Juan Perez', 'juan@demo.com', '987654321'),
(2, 'maria', '123456', 'Maria Lopez', 'maria@demo.com', '987111222'),
(3, 'carlos', '123456', 'Carlos Ruiz', 'carlos@demo.com', '987333444'),
(2, 'ana', '123456', 'Ana Torres', 'ana@demo.com', '987555666');
GO

--CATEGORIES--

INSERT INTO CATEGORIES(category_name, description)
VALUES
('ELECTRONICS', 'Electronic devices'),
('CLOTHING', 'Apparel and clothing'),
('FOOD', 'Food Products'),
('OFFICE', 'Office supplies'),
('SPORTS', 'Sports items'),
('CLEANING', 'Cleaning Products'),
('TOOLS', 'Hardware tools');
GO

--SUPPLIERS--

INSERT INTO SUPPLIERS(company_name, contact_name, phone, email, address)
VALUES
('TechWorld', 'Luis Gomez', '999111222', 'tech@world.com', 'Lima'),
('FashionLine', 'Sara Diaz', '999222333', 'fashion@line.com', 'Cusco'),
('FoodMarket', 'Pedro Ruiz', '999333444', 'food@market.com', 'Arequipa'),
('OfficePro', 'Ana Silva', '999444555', 'office@pro.com', 'Lima'),
('SportMax', 'Jorge Torres', '999555666', 'sport@max.com', 'Piura'),
('CleanHome', 'Rosa Perez', '999666777', 'clean@home.com', 'Trujillo'),
('ToolMaster', 'Miguel Rojas', '999777888', 'tool@master.com', 'Chiclayo');
GO

--ProductS--
INSERT INTO ProductS(category_id, supplier_id, Product_name, description, unit_price, stock, minimum_stock, barcode)
VALUES
(1, 1, 'Laptop HP', 'Laptop de alto rendimiento', 2500, 10, 2, 'ELEC001'),
(1, 1, 'Mouse Logitech', 'Mouse inalámbrico', 80, 50, 10, 'ELEC002'),
(1, 1, 'Teclado Mecánico', 'Teclado gamer RGB', 150, 30, 5, 'ELEC003'),

(2, 2, 'Camiseta Nike', 'Ropa deportiva', 60, 40, 10, 'CLO001'),
(2, 2, 'Pantalón Jeans', 'Jeans azul', 120, 25, 5, 'CLO002'),

(3, 3, 'Arroz 5kg', 'Arroz de calidad', 20, 100, 20, 'FOOD001'),
(3, 3, 'Aceite vegetal', 'Aceite comestible', 15, 80, 15, 'FOOD002'),

(4, 4, 'Cuaderno A4', 'Cuaderno universitario', 8, 200, 30, 'OFF001'),
(4, 4, 'Lapicero Azul', 'Bolígrafo estándar', 2, 500, 100, 'OFF002'),

(5, 5, 'Balón de fútbol', 'Balón profesional', 90, 20, 5, 'SPO001'),
(5, 5, 'Raqueta tenis', 'Raqueta ligera', 200, 10, 2, 'SPO002'),

(6, 6, 'Detergente', 'Limpieza hogar', 18, 60, 10, 'CLE001'),
(6, 6, 'Cloro', 'Desinfectante', 12, 70, 10, 'CLE002'),

(7, 7, 'Martillo', 'Herramienta básica', 25, 30, 5, 'TOO001'),
(7, 7, 'Destornillador', 'Juego de destornilladores', 40, 20, 5, 'TOO002');
GO

--PURCHASES--

INSERT INTO PURCHASES(supplier_id, Users_id, total, status)
VALUES
(1, 1, 2680, 'COMPLETED'),
(2, 2, 300, 'COMPLETED'),
(3, 2, 200, 'COMPLETED'),
(4, 3, 100, 'COMPLETED'),
(5, 4, 290, 'COMPLETED');
GO

--PURCHASE DETAILS--

INSERT INTO PURCHASE_DETAILS(purchase_id, Product_id, quantity, unit_price, subtotal)
VALUES
(1, 1, 1, 2500, 2500),
(1, 2, 2, 80, 160),
(1, 3, 1, 20, 20),

(2, 4, 2, 60, 120),
(2, 5, 1, 120, 120),

(3, 6, 5, 20, 100),
(3, 7, 5, 15, 75),

(4, 8, 10, 8, 80),
(4, 9, 10, 2, 20),

(5, 10, 2, 90, 180),
(5, 11, 1, 200, 200);
GO

