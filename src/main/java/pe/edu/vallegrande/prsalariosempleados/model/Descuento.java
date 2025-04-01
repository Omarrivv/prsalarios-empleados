package pe.edu.vallegrande.prsalariosempleados.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "descuentos")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento")
    private Long idDescuento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "afp", nullable = false)
    private BigDecimal afp = BigDecimal.ZERO;

    @Column(name = "onp", nullable = false)
    private BigDecimal onp = BigDecimal.ZERO;

    @Column(name = "essalud", nullable = false)
    private BigDecimal essalud = BigDecimal.ZERO;

    @Column(name = "impuesto_renta", nullable = false)
    private BigDecimal impuestoRenta = BigDecimal.ZERO;

    @Column(name = "total_descuento", insertable = false, updatable = false)
    private BigDecimal totalDescuento;

    // Getters y Setters

    public Long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public BigDecimal getAfp() {
        return afp;
    }

    public void setAfp(BigDecimal afp) {
        this.afp = afp != null ? afp : BigDecimal.ZERO;
    }

    public BigDecimal getOnp() {
        return onp;
    }

    public void setOnp(BigDecimal onp) {
        this.onp = onp != null ? onp : BigDecimal.ZERO;
    }

    public BigDecimal getEssalud() {
        return essalud;
    }

    public void setEssalud(BigDecimal essalud) {
        this.essalud = essalud != null ? essalud : BigDecimal.ZERO;
    }

    public BigDecimal getImpuestoRenta() {
        return impuestoRenta;
    }

    public void setImpuestoRenta(BigDecimal impuestoRenta) {
        this.impuestoRenta = impuestoRenta != null ? impuestoRenta : BigDecimal.ZERO;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }
} 