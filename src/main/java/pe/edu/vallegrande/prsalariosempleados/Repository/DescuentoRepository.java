package pe.edu.vallegrande.prsalariosempleados.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.prsalariosempleados.model.Descuento;
import java.util.Optional;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    Optional<Descuento> findByEmpleadoIdEmpleado(Long idEmpleado);
} 