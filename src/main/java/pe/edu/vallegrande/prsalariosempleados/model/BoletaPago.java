package pe.edu.vallegrande.prsalariosempleados.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "boletas_pago")
public class BoletaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Long idBoleta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_salario", nullable = false)
    private Salario salario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_descuento", nullable = false)
    private Descuento descuento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asignacion", nullable = false)
    private AsignacionFamiliar asignacionFamiliar;

    @Column(name = "total_neto")
    private BigDecimal totalNeto;

    // Getters y Setters
    public Long getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(Long idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Salario getSalario() {
        return salario;
    }

    public void setSalario(Salario salario) {
        this.salario = salario;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    public AsignacionFamiliar getAsignacionFamiliar() {
        return asignacionFamiliar;
    }

    public void setAsignacionFamiliar(AsignacionFamiliar asignacionFamiliar) {
        this.asignacionFamiliar = asignacionFamiliar;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }
} 