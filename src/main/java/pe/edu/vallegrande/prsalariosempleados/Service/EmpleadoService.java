package pe.edu.vallegrande.prsalariosempleados.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.vallegrande.prsalariosempleados.model.Empleado;
import pe.edu.vallegrande.prsalariosempleados.Repository.EmpleadoRepository;
import pe.edu.vallegrande.prsalariosempleados.Repository.CargoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private CargoRepository cargoRepository;

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Optional<Empleado> buscarPorDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    @Transactional
    public Empleado guardar(Empleado empleado) {
        // Validar que el cargo existe
        if (empleado.getCargo() == null || !cargoRepository.existsById(empleado.getCargo().getIdCargo())) {
            throw new RuntimeException("El cargo especificado no existe");
        }

        // Validar DNI único
        if (empleadoRepository.existsByDni(empleado.getDni())) {
            throw new RuntimeException("Ya existe un empleado con ese DNI");
        }

        // Validar email único si se proporciona
        if (empleado.getEmail() != null && empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new RuntimeException("Ya existe un empleado con ese email");
        }

        // Establecer valores por defecto
        if (empleado.getFechaIngreso() == null) {
            empleado.setFechaIngreso(LocalDate.now());
        }
        if (empleado.getEstado() == null) {
            empleado.setEstado("Activo");
        }

        return empleadoRepository.save(empleado);
    }

    @Transactional
    public Empleado actualizar(Long id, Empleado empleado) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }

        // Validar que el cargo existe
        if (empleado.getCargo() == null || !cargoRepository.existsById(empleado.getCargo().getIdCargo())) {
            throw new RuntimeException("El cargo especificado no existe");
        }

        // Validar DNI único excepto para el mismo empleado
        Optional<Empleado> empleadoConDni = empleadoRepository.findByDni(empleado.getDni());
        if (empleadoConDni.isPresent() && !empleadoConDni.get().getIdEmpleado().equals(id)) {
            throw new RuntimeException("Ya existe otro empleado con ese DNI");
        }

        // Validar email único excepto para el mismo empleado
        if (empleado.getEmail() != null) {
            Optional<Empleado> empleadoConEmail = empleadoRepository.findByEmail(empleado.getEmail());
            if (empleadoConEmail.isPresent() && !empleadoConEmail.get().getIdEmpleado().equals(id)) {
                throw new RuntimeException("Ya existe otro empleado con ese email");
            }
        }

        empleado.setIdEmpleado(id);
        return empleadoRepository.save(empleado);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }
        empleadoRepository.deleteById(id);
    }
} 