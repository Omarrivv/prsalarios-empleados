package pe.edu.vallegrande.prsalariosempleados.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "asignaciones_familiares")
public class AsignacionFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long idAsignacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "cantidad_hijos")
    private Integer cantidadHijos;

    @Column(name = "monto_asignacion", insertable = false, updatable = false)
    private BigDecimal montoAsignacion;

    // Getters y Setters
    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(Integer cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public BigDecimal getMontoAsignacion() {
        return montoAsignacion;
    }

    public void setMontoAsignacion(BigDecimal montoAsignacion) {
        // Este setter no hace nada ya que el monto es calculado por la base de datos
        this.montoAsignacion = montoAsignacion;
    }
} 