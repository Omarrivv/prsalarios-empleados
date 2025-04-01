package pe.edu.vallegrande.prsalariosempleados.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Long idCargo;

    @Column(name = "nombre_cargo", nullable = false, unique = true)
    private String nombreCargo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
} 