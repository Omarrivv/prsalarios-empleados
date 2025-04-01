package pe.edu.vallegrande.prsalariosempleados.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.prsalariosempleados.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    boolean existsByNombreCargo(String nombreCargo);
} 