package pe.edu.vallegrande.prsalariosempleados.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.prsalariosempleados.model.Empleado;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(String dni);
    Optional<Empleado> findByEmail(String email);
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
} 