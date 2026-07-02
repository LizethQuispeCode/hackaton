==========================================================
CÓMO ADAPTAR ESTA BASE DE DATOS A CUALQUIER CONTEXTO
==========================================================

1. IDEA PRINCIPAL DEL MODELO

Esta base de datos está diseñada como un modelo genérico de gestión.
El núcleo del sistema es:

- ProductS (entidad principal)
- CATEGORIES (clasificación)
- SUPPLIERS (proveedores o entidades relacionadas)
- PURCHASES (transacciones)
- UsersS (control de acceso)

2. PRINCIPIO DE ADAPTABILIDAD

NO se modifica la estructura.
SOLO se cambian nombres de entidades y algunos campos descriptivos.

3. TABLA EQUIVALENTE POR CONTEXTO

----------------------------------------------------------
ProductS
----------------------------------------------------------
Se adapta según el sistema:

- Productos → Inventario
- Libros → Biblioteca
- Pacientes → Clínica
- Habitaciones → Hotel
- Mascotas → Veterinaria
- Cursos → Educación

----------------------------------------------------------
CATEGORIES
----------------------------------------------------------
Se convierte en:

- Tipo de Producto
- Género (libros)
- Especialidad (salud)
- Área académica
- Tipo de habitación

----------------------------------------------------------
SUPPLIERS
----------------------------------------------------------
Se convierte en:

- Proveedores
- Autores
- Médicos
- Clientes
- Dueños de mascotas
- Docentes

----------------------------------------------------------
PURCHASES
----------------------------------------------------------
Se convierte en:

- Ventas
- Atenciones médicas
- Reservas
- Matrículas
- Pedidos

----------------------------------------------------------
PURCHASE_DETAILS
----------------------------------------------------------
Se convierte en:

- Detalle de venta
- Detalle de receta
- Detalle de reserva
- Detalle de matrícula

4. QUÉ NO SE DEBE CAMBIAR

- Primary Keys
- Foreign Keys
- Auditoría (created_at, updated_at)
- status (eliminación lógica)
- Estructura de relaciones

5. SOLO SE PUEDE CAMBIAR

- Nombres de tablas
- Nombres de campos descriptivos
- Labels del frontend
- DTOs en backend

6. EJEMPLO REAL

SISTEMA: CLÍNICA

ProductS → PATIENTS
CATEGORIES → SPECIALTIES
SUPPLIERS → DOCTORS
PURCHASES → APPOINTMENTS
PURCHASE_DETAILS → MEDICAL_RECORD

7. BENEFICIO DEL MODELO

Este diseño permite:

- Reutilizar backend completo
- Reutilizar frontend completo
- Cambiar solo el contexto del negocio
- Adaptarse a cualquier hackathon en minutos
