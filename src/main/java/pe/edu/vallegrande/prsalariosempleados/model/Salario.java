package pe.edu.vallegrande.prsalariosempleados.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "salarios")
public class Salario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salario")
    private Long idSalario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "sueldo_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal sueldoBase;

    @Column(name = "horas_extra", precision = 10, scale = 2)
    private BigDecimal horasExtra = BigDecimal.ZERO;

    @Column(name = "bonificaciones", precision = 10, scale = 2)
    private BigDecimal bonificaciones = BigDecimal.ZERO;

    @Column(name = "total_bruto", insertable = false, updatable = false)
    private BigDecimal totalBruto;

    @PrePersist
    @PreUpdate
    public void calcularTotalBruto() {
        this.totalBruto = this.sueldoBase
            .add(this.horasExtra != null ? this.horasExtra : BigDecimal.ZERO)
            .add(this.bonificaciones != null ? this.bonificaciones : BigDecimal.ZERO);
    }

    // Getters y Setters
    public Long getIdSalario() {
        return idSalario;
    }

    public void setIdSalario(Long idSalario) {
        this.idSalario = idSalario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public BigDecimal getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(BigDecimal sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public BigDecimal getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(BigDecimal horasExtra) {
        this.horasExtra = horasExtra != null ? horasExtra : BigDecimal.ZERO;
    }

    public BigDecimal getBonificaciones() {
        return bonificaciones;
    }

    public void setBonificaciones(BigDecimal bonificaciones) {
        this.bonificaciones = bonificaciones != null ? bonificaciones : BigDecimal.ZERO;
    }

    public BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(BigDecimal totalBruto) {
        // Este setter no hace nada ya que el total es calculado por la base de datos
        this.totalBruto = totalBruto;
    }
} 