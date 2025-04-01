package pe.edu.vallegrande.prsalariosempleados.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.vallegrande.prsalariosempleados.model.Empleado;
import pe.edu.vallegrande.prsalariosempleados.model.Salario;
import pe.edu.vallegrande.prsalariosempleados.Repository.EmpleadoRepository;
import pe.edu.vallegrande.prsalariosempleados.Repository.SalarioRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SalarioService {
    
    @Autowired
    private SalarioRepository salarioRepository;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Salario> listarTodos() {
        return salarioRepository.findAll();
    }

    public Optional<Salario> buscarPorId(Long id) {
        return salarioRepository.findById(id);
    }

    public List<Salario> buscarPorEmpleado(Long idEmpleado) {
        return salarioRepository.findByEmpleadoIdEmpleado(idEmpleado);
    }

    @Transactional
    public Salario guardar(Salario salario) {
        // Validar que se proporcione un empleado
        if (salario.getEmpleado() == null || salario.getEmpleado().getIdEmpleado() == null) {
            throw new RuntimeException("Debe especificar un empleado válido");
        }

        // Validar que el empleado existe
        Empleado empleado = empleadoRepository.findById(salario.getEmpleado().getIdEmpleado())
            .orElseThrow(() -> new RuntimeException("El empleado especificado no existe"));

        // Validar sueldo base
        if (salario.getSueldoBase() == null || salario.getSueldoBase().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El sueldo base debe ser mayor que cero");
        }

        // Establecer valores por defecto y validar
        if (salario.getHorasExtra() == null) {
            salario.setHorasExtra(BigDecimal.ZERO);
        }
        if (salario.getBonificaciones() == null) {
            salario.setBonificaciones(BigDecimal.ZERO);
        }
        if (salario.getHorasExtra().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Las horas extra no pueden ser negativas");
        }
        if (salario.getBonificaciones().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Las bonificaciones no pueden ser negativas");
        }

        // Asignar el empleado completo
        salario.setEmpleado(empleado);

        return salarioRepository.save(salario);
    }

    @Transactional
    public Salario actualizar(Long id, Salario salario) {
        // Validar que el salario existe
        if (!salarioRepository.existsById(id)) {
            throw new RuntimeException("Salario no encontrado");
        }

        // Validar que se proporcione un empleado
        if (salario.getEmpleado() == null || salario.getEmpleado().getIdEmpleado() == null) {
            throw new RuntimeException("Debe especificar un empleado válido");
        }

        // Validar que el empleado existe
        Empleado empleado = empleadoRepository.findById(salario.getEmpleado().getIdEmpleado())
            .orElseThrow(() -> new RuntimeException("El empleado especificado no existe"));

        // Validar sueldo base
        if (salario.getSueldoBase() == null || salario.getSueldoBase().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El sueldo base debe ser mayor que cero");
        }

        // Establecer valores por defecto y validar
        if (salario.getHorasExtra() == null) {
            salario.setHorasExtra(BigDecimal.ZERO);
        }
        if (salario.getBonificaciones() == null) {
            salario.setBonificaciones(BigDecimal.ZERO);
        }
        if (salario.getHorasExtra().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Las horas extra no pueden ser negativas");
        }
        if (salario.getBonificaciones().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Las bonificaciones no pueden ser negativas");
        }

        // Asignar el ID y el empleado completo
        salario.setIdSalario(id);
        salario.setEmpleado(empleado);

        return salarioRepository.save(salario);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!salarioRepository.existsById(id)) {
            throw new RuntimeException("Salario no encontrado");
        }
        salarioRepository.deleteById(id);
    }
} 