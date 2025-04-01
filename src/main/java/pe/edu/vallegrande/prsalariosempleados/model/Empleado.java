package pe.edu.vallegrande.prsalariosempleados.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "dni", length = 8, unique = true, nullable = false)
    private String dni;

    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @Column(name = "estado", length = 20)
    private String estado;
} 