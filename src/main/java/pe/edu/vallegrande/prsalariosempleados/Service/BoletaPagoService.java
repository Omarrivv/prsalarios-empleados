package pe.edu.vallegrande.prsalariosempleados.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.prsalariosempleados.model.BoletaPago;
import pe.edu.vallegrande.prsalariosempleados.Repository.BoletaPagoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BoletaPagoService {
    
    @Autowired
    private BoletaPagoRepository boletaPagoRepository;

    public List<BoletaPago> listarTodos() {
        return boletaPagoRepository.findAll();
    }

    public Optional<BoletaPago> buscarPorId(Long id) {
        return boletaPagoRepository.findById(id);
    }

    public List<BoletaPago> buscarPorEmpleado(Long idEmpleado) {
        return boletaPagoRepository.findByEmpleadoIdEmpleado(idEmpleado);
    }

    public List<BoletaPago> buscarPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        return boletaPagoRepository.findByFechaEmisionBetween(fechaInicio, fechaFin);
    }

    public Optional<BoletaPago> buscarPorEmpleadoYFecha(Long idEmpleado, LocalDate fechaEmision) {
        return boletaPagoRepository.findByEmpleadoIdEmpleadoAndFechaEmision(idEmpleado, fechaEmision);
    }

    public BoletaPago guardar(BoletaPago boletaPago) {
        return boletaPagoRepository.save(boletaPago);
    }

    public BoletaPago actualizar(Long id, BoletaPago boletaPago) {
        if (!boletaPagoRepository.existsById(id)) {
            throw new RuntimeException("Boleta de pago no encontrada");
        }
        boletaPago.setIdBoleta(id);
        return boletaPagoRepository.save(boletaPago);
    }

    public void eliminar(Long id) {
        if (!boletaPagoRepository.existsById(id)) {
            throw new RuntimeException("Boleta de pago no encontrada");
        }
        boletaPagoRepository.deleteById(id);
    }
} 