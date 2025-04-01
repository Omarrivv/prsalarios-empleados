package pe.edu.vallegrande.prsalariosempleados.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.prsalariosempleados.model.BoletaPago;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoletaPagoRepository extends JpaRepository<BoletaPago, Long> {
    List<BoletaPago> findByEmpleadoIdEmpleado(Long idEmpleado);
    List<BoletaPago> findByFechaEmisionBetween(LocalDate fechaInicio, LocalDate fechaFin);
    Optional<BoletaPago> findByEmpleadoIdEmpleadoAndFechaEmision(Long idEmpleado, LocalDate fechaEmision);
} 