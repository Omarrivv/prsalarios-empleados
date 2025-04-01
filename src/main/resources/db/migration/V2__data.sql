-- Insertar en cargos
INSERT INTO cargos (nombre_cargo, descripcion) VALUES
('Gerente', 'Encargado de la administración general'),
('Contador', 'Responsable de las finanzas'),
('Desarrollador', 'Encargado de la programación de software'),
('Diseñador', 'Responsable del diseño gráfico y UX'),
('Analista', 'Analiza datos y procesos'),
('Soporte Técnico', 'Brinda asistencia tecnológica'),
('Jefe de Recursos Humanos', 'Gestiona el personal'),
('Vendedor', 'Encargado de ventas'),
('Secretaria', 'Asistente administrativa'),
('Conserje', 'Encargado de la limpieza y mantenimiento');

-- Insertar en empleados
INSERT INTO empleados (dni, nombres, apellidos, fecha_nacimiento, telefono, email, direccion, fecha_ingreso, id_cargo, estado) VALUES
('12345678', 'Juan', 'Pérez', '1990-05-10', '987654321', 'juan@example.com', 'Calle 123', '2020-03-01', 1, 'Activo'),
('87654321', 'María', 'Gómez', '1985-08-15', '987654322', 'maria@example.com', 'Av. Principal 456', '2019-07-10', 2, 'Activo'),
('11223344', 'Carlos', 'Díaz', '1992-12-20', '987654323', 'carlos@example.com', 'Jr. Secundario 789', '2021-01-05', 3, 'Activo'),
('44332211', 'Ana', 'Fernández', '1995-03-25', '987654324', 'ana@example.com', 'Psje. Comercio 101', '2022-05-12', 4, 'Activo'),
('55667788', 'Pedro', 'Ramírez', '1988-11-30', '987654325', 'pedro@example.com', 'Calle Industrial 202', '2018-09-15', 5, 'Inactivo'),
('66778899', 'Laura', 'Torres', '1991-07-08', '987654326', 'laura@example.com', 'Av. La Paz 303', '2020-11-22', 6, 'Activo'),
('77889900', 'José', 'Martínez', '1983-05-21', '987654327', 'jose@example.com', 'Jr. Central 404', '2017-04-30', 7, 'Inactivo'),
('88990011', 'Elena', 'Castro', '1996-09-14', '987654328', 'elena@example.com', 'Calle Norte 505', '2023-02-14', 8, 'Activo'),
('99001122', 'Diego', 'Hernández', '1986-06-17', '987654329', 'diego@example.com', 'Av. Sur 606', '2016-06-05', 9, 'Activo'),
('10111213', 'Carmen', 'Rojas', '1993-01-10', '987654330', 'carmen@example.com', 'Jr. Este 707', '2021-10-09', 10, 'Activo');

-- Insertar en salarios
INSERT INTO salarios (id_empleado, sueldo_base, horas_extra, bonificaciones) VALUES
(1, 3000, 100, 200),
(2, 2500, 50, 100),
(3, 4000, 200, 300),
(4, 2800, 120, 150),
(5, 1500, 0, 50),
(6, 3200, 180, 250),
(7, 5000, 300, 400),
(8, 2200, 50, 50),
(9, 2700, 80, 100),
(10, 3500, 200, 300);

-- Insertar en descuentos
INSERT INTO descuentos (id_empleado, afp, onp, essalud, impuesto_renta) VALUES
(1, 300, 0, 150, 250),
(2, 250, 0, 120, 200),
(3, 400, 0, 200, 300),
(4, 280, 0, 140, 250),
(5, 150, 0, 80, 100),
(6, 320, 0, 160, 270),
(7, 500, 0, 250, 400),
(8, 220, 0, 110, 180),
(9, 270, 0, 130, 220),
(10, 350, 0, 180, 280);

-- Insertar en asignaciones familiares
INSERT INTO asignaciones_familiares (id_empleado, cantidad_hijos) VALUES
(1, 2),
(2, 1),
(3, 3),
(4, 2),
(5, 0),
(6, 1),
(7, 4),
(8, 0),
(9, 2),
(10, 3);

-- Insertar en boletas de pago
INSERT INTO boletas_pago (id_empleado, fecha_emision, id_salario, id_descuento, id_asignacion) VALUES
(1, '2025-03-01', 1, 1, 1),
(2, '2025-03-01', 2, 2, 2),
(3, '2025-03-01', 3, 3, 3),
(4, '2025-03-01', 4, 4, 4),
(5, '2025-03-01', 5, 5, 5),
(6, '2025-03-01', 6, 6, 6),
(7, '2025-03-01', 7, 7, 7),
(8, '2025-03-01', 8, 8, 8),
(9, '2025-03-01', 9, 9, 9),
(10, '2025-03-01', 10, 10, 10); 