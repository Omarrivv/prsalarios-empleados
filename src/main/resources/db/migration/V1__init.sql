CREATE TABLE cargos (
    id_cargo SERIAL PRIMARY KEY,
    nombre_cargo VARCHAR(100) UNIQUE NOT NULL,
    descripcion TEXT
);

CREATE TABLE empleados (
    id_empleado SERIAL PRIMARY KEY,
    dni VARCHAR(8) UNIQUE NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    direccion TEXT,
    fecha_ingreso DATE NOT NULL,
    id_cargo INT NOT NULL,
    estado VARCHAR(20) CHECK (estado IN ('Activo', 'Inactivo')),
    FOREIGN KEY (id_cargo) REFERENCES cargos(id_cargo)
);

CREATE TABLE salarios (
    id_salario SERIAL PRIMARY KEY,
    id_empleado INT NOT NULL,
    sueldo_base DECIMAL(10,2) NOT NULL CHECK (sueldo_base > 0),
    horas_extra DECIMAL(10,2) DEFAULT 0,
    bonificaciones DECIMAL(10,2) DEFAULT 0,
    total_bruto DECIMAL(10,2) GENERATED ALWAYS AS (sueldo_base + horas_extra + bonificaciones) STORED,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);

CREATE TABLE descuentos (
    id_descuento SERIAL PRIMARY KEY,
    id_empleado INT NOT NULL,
    afp DECIMAL(10,2) DEFAULT 0,
    onp DECIMAL(10,2) DEFAULT 0,
    essalud DECIMAL(10,2) DEFAULT 0,
    impuesto_renta DECIMAL(10,2) DEFAULT 0,
    total_descuento DECIMAL(10,2) GENERATED ALWAYS AS (afp + onp + essalud + impuesto_renta) STORED,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);

CREATE TABLE asignaciones_familiares (
    id_asignacion SERIAL PRIMARY KEY,
    id_empleado INT NOT NULL,
    cantidad_hijos INT DEFAULT 0,
    monto_asignacion DECIMAL(10,2) GENERATED ALWAYS AS (cantidad_hijos * 102.5) STORED,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);

CREATE TABLE boletas_pago (
    id_boleta SERIAL PRIMARY KEY,
    id_empleado INT NOT NULL,
    fecha_emision DATE NOT NULL,
    id_salario INT NOT NULL,
    id_descuento INT NOT NULL,
    id_asignacion INT NOT NULL,
    total_neto DECIMAL(10,2),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado),
    FOREIGN KEY (id_salario) REFERENCES salarios(id_salario),
    FOREIGN KEY (id_descuento) REFERENCES descuentos(id_descuento),
    FOREIGN KEY (id_asignacion) REFERENCES asignaciones_familiares(id_asignacion)
);

CREATE OR REPLACE FUNCTION calcular_total_neto_boleta()
RETURNS TRIGGER AS $$
DECLARE
    salario_bruto DECIMAL(10,2);
    descuento_total DECIMAL(10,2);
    asignacion_total DECIMAL(10,2);
BEGIN
    SELECT total_bruto INTO salario_bruto FROM salarios WHERE id_salario = NEW.id_salario;
    SELECT total_descuento INTO descuento_total FROM descuentos WHERE id_descuento = NEW.id_descuento;
    SELECT monto_asignacion INTO asignacion_total FROM asignaciones_familiares WHERE id_asignacion = NEW.id_asignacion;
    
    NEW.total_neto := salario_bruto - descuento_total + asignacion_total;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_total_neto_boleta
BEFORE INSERT OR UPDATE ON boletas_pago
FOR EACH ROW
EXECUTE FUNCTION calcular_total_neto_boleta(); 