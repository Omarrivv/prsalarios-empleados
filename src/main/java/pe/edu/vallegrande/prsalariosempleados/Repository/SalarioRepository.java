package pe.edu.vallegrande.prsalariosempleados.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.prsalariosempleados.model.Salario;

import java.util.List;

@Repository
public interface SalarioRepository extends JpaRepository<Salario, Long> {
    List<Salario> findByEmpleadoIdEmpleado(Long idEmpleado);
} 