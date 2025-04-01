# prsalarios-empleados

# Documentación de Endpoints

## BoletaPagoController

### Listar todas las boletas de pago
- **Método:** GET
- **URL:** `/api/boletas-pago`

### Buscar boleta de pago por ID
- **Método:** GET
- **URL:** `/api/boletas-pago/{id}`

### Buscar boletas de pago por empleado
- **Método:** GET
- **URL:** `/api/boletas-pago/empleado/{idEmpleado}`

### Buscar boletas de pago por periodo
- **Método:** GET
- **URL:** `/api/boletas-pago/periodo?fechaInicio={fechaInicio}&fechaFin={fechaFin}`

### Crear nueva boleta de pago
- **Método:** POST
- **URL:** `/api/boletas-pago`
- **Cuerpo (JSON):**
  ```json
  {
    "empleado": { "idEmpleado": 1 },
    "fechaEmision": "2024-03-31",
    "salario": { "idSalario": 1 },
    "descuento": { "idDescuento": 1 },
    "asignacionFamiliar": { "idAsignacion": 1 }
  }
  ```

### Actualizar boleta de pago
- **Método:** PUT
- **URL:** `/api/boletas-pago/{id}`
- **Cuerpo (JSON):** Mismo formato que en crear

### Eliminar boleta de pago
- **Método:** DELETE
- **URL:** `/api/boletas-pago/{id}`

---

## DescuentoController

### Listar todos los descuentos
- **Método:** GET
- **URL:** `/api/descuentos`

### Buscar descuento por ID
- **Método:** GET
- **URL:** `/api/descuentos/{id}`

### Buscar descuentos por empleado
- **Método:** GET
- **URL:** `/api/descuentos/empleado/{idEmpleado}`

### Crear nuevo descuento
- **Método:** POST
- **URL:** `/api/descuentos`
- **Cuerpo (JSON):**
  ```json
  {
    "empleado": { "idEmpleado": 1 },
    "afp": 300.00,
    "onp": 0.00,
    "essalud": 150.00,
    "impuestoRenta": 250.00
  }
  ```

### Actualizar descuento
- **Método:** PUT
- **URL:** `/api/descuentos/{id}`
- **Cuerpo (JSON):** Mismo formato que en crear

### Eliminar descuento
- **Método:** DELETE
- **URL:** `/api/descuentos/{id}`

---

## SalarioController

### Listar todos los salarios
- **Método:** GET
- **URL:** `/api/salarios`

### Buscar salario por ID
- **Método:** GET
- **URL:** `/api/salarios/{id}`

### Buscar salarios por empleado
- **Método:** GET
- **URL:** `/api/salarios/empleado/{idEmpleado}`

### Crear nuevo salario
- **Método:** POST
- **URL:** `/api/salarios`
- **Cuerpo (JSON):**
  ```json
  {
    "empleado": { "idEmpleado": 1 },
    "sueldoBase": 2500.00,
    "horasExtra": 150.00,
    "bonificaciones": 300.00
  }
  ```

### Actualizar salario
- **Método:** PUT
- **URL:** `/api/salarios/{id}`
- **Cuerpo (JSON):** Mismo formato que en crear

### Eliminar salario
- **Método:** DELETE
- **URL:** `/api/salarios/{id}`

---

## AsignacionFamiliarController

### Listar todas las asignaciones familiares
- **Método:** GET
- **URL:** `/api/asignaciones-familiares`

### Buscar asignación familiar por ID
- **Método:** GET
- **URL:** `/api/asignaciones-familiares/{id}`

### Buscar asignaciones familiares por empleado
- **Método:** GET
- **URL:** `/api/asignaciones-familiares/empleado/{idEmpleado}`

### Crear nueva asignación familiar
- **Método:** POST
- **URL:** `/api/asignaciones-familiares`
- **Cuerpo (JSON):**
  ```json
  {
    "empleado": { "idEmpleado": 1 },
    "cantidadHijos": 2
  }
  ```

### Actualizar asignación familiar
- **Método:** PUT
- **URL:** `/api/asignaciones-familiares/{id}`
- **Cuerpo (JSON):** Mismo formato que en crear

### Eliminar asignación familiar
- **Método:** DELETE
- **URL:** `/api/asignaciones-familiares/{id}`

---

## EmpleadoController

### Listar todos los empleados
- **Método:** GET
- **URL:** `/api/empleados`

### Buscar empleado por ID
- **Método:** GET
- **URL:** `/api/empleados/{id}`

### Buscar empleado por DNI
- **Método:** GET
- **URL:** `/api/empleados/dni/{dni}`

### Crear nuevo empleado
- **Método:** POST
- **URL:** `/api/empleados`
- **Cuerpo (JSON):**
  ```json
  {
    "dni": "12345678",
    "nombres": "Juan",
    "apellidos": "Pérez",
    "fechaNacimiento": "1990-05-10",
    "telefono": "987654321",
    "email": "juan@example.com",
    "direccion": "Calle 123",
    "fechaIngreso": "2020-03-01",
    "cargo": { "idCargo": 1 },
    "estado": "Activo"
  }
  ```

### Actualizar empleado
- **Método:** PUT
- **URL:** `/api/empleados/{id}`
- **Cuerpo (JSON):** Mismo formato que en crear

### Eliminar empleado
- **Método:** DELETE
- **URL:** `/api/empleados/{id}`

---

## CargoController

### Listar todos los cargos
- **Método:** GET
- **URL:** `/api/cargos`

### Buscar cargo por ID
- **Método:** GET
- **URL:** `/api/cargos/{id}`

### Crear nuevo cargo
- **Método:** POST
- **URL:** `/api/cargos`
- **Cuerpo (JSON):**
  ```json
  {
    "nombreCargo": "Gerente",
    "descripcion": "Encargado de la administración general"
  }
  ```

### Actualizar cargo
- **Método:** PUT
- **URL:** `/api/cargos/{id}`
- **Cuerpo (JSON):** Mismo formato que en crear

### Eliminar cargo
- **Método:** DELETE
- **URL:** `/api/cargos/{id}`
