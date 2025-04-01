package pe.edu.vallegrande.prsalariosempleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.prsalariosempleados.model.Empleado;
import pe.edu.vallegrande.prsalariosempleados.Service.EmpleadoService;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listarTodos() {
        return empleadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Empleado> buscarPorDni(@PathVariable String dni) {
        return empleadoService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoGuardado = empleadoService.guardar(empleado);
            return ResponseEntity.ok(empleadoGuardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        try {
            Empleado empleadoActualizado = empleadoService.actualizar(id, empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            empleadoService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 