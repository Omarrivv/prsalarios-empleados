package pe.edu.vallegrande.prsalariosempleados.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.vallegrande.prsalariosempleados.model.AsignacionFamiliar;
import pe.edu.vallegrande.prsalariosempleados.model.Empleado;
import pe.edu.vallegrande.prsalariosempleados.Repository.AsignacionFamiliarRepository;
import pe.edu.vallegrande.prsalariosempleados.Repository.EmpleadoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignacionFamiliarService {
    
    @Autowired
    private AsignacionFamiliarRepository asignacionFamiliarRepository;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    private String obtenerEmpleadosDisponibles() {
        List<Empleado> todosEmpleados = empleadoRepository.findAll();
        List<AsignacionFamiliar> todasAsignaciones = asignacionFamiliarRepository.findAll();
        
        // Obtener IDs de empleados que ya tienen asignación
        List<Long> idsConAsignacion = todasAsignaciones.stream()
            .map(a -> a.getEmpleado().getIdEmpleado())
            .collect(Collectors.toList());
        
        // Filtrar empleados sin asignación
        List<Empleado> empleadosDisponibles = todosEmpleados.stream()
            .filter(emp -> !idsConAsignacion.contains(emp.getIdEmpleado()))
            .collect(Collectors.toList());
        
        if (empleadosDisponibles.isEmpty()) {
            return "No hay empleados disponibles sin asignación familiar.";
        }
        
        StringBuilder mensaje = new StringBuilder("Empleados disponibles sin asignación familiar:\n");
        empleadosDisponibles.forEach(emp -> 
            mensaje.append(String.format("- ID: %d, Nombre: %s %s\n", 
                emp.getIdEmpleado(), 
                emp.getNombres(), 
                emp.getApellidos())
            )
        );
        
        return mensaje.toString();
    }

    public List<AsignacionFamiliar> listarTodos() {
        return asignacionFamiliarRepository.findAll();
    }

    public Optional<AsignacionFamiliar> buscarPorId(Long id) {
        return asignacionFamiliarRepository.findById(id);
    }

    public Optional<AsignacionFamiliar> buscarPorEmpleado(Long idEmpleado) {
        return asignacionFamiliarRepository.findByEmpleadoIdEmpleado(idEmpleado);
    }

    @Transactional
    public AsignacionFamiliar guardar(AsignacionFamiliar asignacionFamiliar) {
        // Validar que se proporcione un empleado
        if (asignacionFamiliar.getEmpleado() == null || asignacionFamiliar.getEmpleado().getIdEmpleado() == null) {
            throw new RuntimeException("Debe especificar un empleado válido.\n" + obtenerEmpleadosDisponibles());
        }

        Long idEmpleado = asignacionFamiliar.getEmpleado().getIdEmpleado();

        // Validar que el empleado existe
        Empleado empleado = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new RuntimeException(
                String.format("El empleado con ID %d no existe.\n%s",
                idEmpleado, obtenerEmpleadosDisponibles())));

        // Validar si ya existe una asignación para este empleado
        Optional<AsignacionFamiliar> asignacionExistente = asignacionFamiliarRepository
            .findByEmpleadoIdEmpleado(idEmpleado);
        
        if (asignacionExistente.isPresent()) {
            AsignacionFamiliar asignacion = asignacionExistente.get();
            throw new RuntimeException(
                String.format("Ya existe una asignación familiar para el empleado %s %s (ID: %d) con %d hijos\n\n%s",
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getIdEmpleado(),
                asignacion.getCantidadHijos(),
                obtenerEmpleadosDisponibles()));
        }

        // Validar cantidad de hijos
        if (asignacionFamiliar.getCantidadHijos() == null || asignacionFamiliar.getCantidadHijos() < 0) {
            asignacionFamiliar.setCantidadHijos(0);
        }

        // Asignar el empleado completo
        asignacionFamiliar.setEmpleado(empleado);

        return asignacionFamiliarRepository.save(asignacionFamiliar);
    }

    @Transactional
    public AsignacionFamiliar actualizar(Long id, AsignacionFamiliar asignacionFamiliar) {
        // Validar que la asignación existe
        AsignacionFamiliar asignacionExistente = asignacionFamiliarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                String.format("No se encontró la asignación familiar con ID %d", id)));

        // Validar que se proporcione un empleado
        if (asignacionFamiliar.getEmpleado() == null || asignacionFamiliar.getEmpleado().getIdEmpleado() == null) {
            throw new RuntimeException("Debe especificar un empleado válido.\n" + obtenerEmpleadosDisponibles());
        }

        Long idEmpleado = asignacionFamiliar.getEmpleado().getIdEmpleado();

        // Validar que el empleado existe
        Empleado empleado = empleadoRepository.findById(idEmpleado)
            .orElseThrow(() -> new RuntimeException(
                String.format("El empleado con ID %d no existe.\n%s",
                idEmpleado, obtenerEmpleadosDisponibles())));

        // Si se está cambiando el empleado, validar que el nuevo empleado no tenga ya una asignación
        if (!asignacionExistente.getEmpleado().getIdEmpleado().equals(idEmpleado)) {
            Optional<AsignacionFamiliar> otraAsignacion = asignacionFamiliarRepository
                .findByEmpleadoIdEmpleado(idEmpleado);
            
            if (otraAsignacion.isPresent()) {
                AsignacionFamiliar asignacion = otraAsignacion.get();
                throw new RuntimeException(
                    String.format("Ya existe una asignación familiar para el empleado %s %s (ID: %d) con %d hijos\n\n%s",
                    empleado.getNombres(),
                    empleado.getApellidos(),
                    empleado.getIdEmpleado(),
                    asignacion.getCantidadHijos(),
                    obtenerEmpleadosDisponibles()));
            }
        }

        // Validar cantidad de hijos
        if (asignacionFamiliar.getCantidadHijos() == null || asignacionFamiliar.getCantidadHijos() < 0) {
            asignacionFamiliar.setCantidadHijos(0);
        }

        // Asignar el ID y el empleado completo
        asignacionFamiliar.setIdAsignacion(id);
        asignacionFamiliar.setEmpleado(empleado);

        return asignacionFamiliarRepository.save(asignacionFamiliar);
    }

    @Transactional
    public void eliminar(Long id) {
        AsignacionFamiliar asignacion = asignacionFamiliarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                String.format("No se encontró la asignación familiar con ID %d", id)));
        
        asignacionFamiliarRepository.deleteById(id);
    }
} 