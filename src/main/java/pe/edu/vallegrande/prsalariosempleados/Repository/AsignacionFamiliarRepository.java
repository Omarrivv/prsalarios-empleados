package pe.edu.vallegrande.prsalariosempleados.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.prsalariosempleados.model.AsignacionFamiliar;
import java.util.Optional;

@Repository
public interface AsignacionFamiliarRepository extends JpaRepository<AsignacionFamiliar, Long> {
    Optional<AsignacionFamiliar> findByEmpleadoIdEmpleado(Long idEmpleado);
} 