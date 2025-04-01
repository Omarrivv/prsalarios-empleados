package pe.edu.vallegrande.prsalariosempleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.prsalariosempleados.model.AsignacionFamiliar;
import pe.edu.vallegrande.prsalariosempleados.Service.AsignacionFamiliarService;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones-familiares")
@CrossOrigin(origins = "*")
public class AsignacionFamiliarController {
    
    @Autowired
    private AsignacionFamiliarService asignacionFamiliarService;

    @GetMapping
    public List<AsignacionFamiliar> listarTodos() {
        return asignacionFamiliarService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionFamiliar> buscarPorId(@PathVariable Long id) {
        return asignacionFamiliarService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<AsignacionFamiliar> buscarPorEmpleado(@PathVariable Long idEmpleado) {
        return asignacionFamiliarService.buscarPorEmpleado(idEmpleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody AsignacionFamiliar asignacionFamiliar) {
        try {
            AsignacionFamiliar nuevaAsignacion = asignacionFamiliarService.guardar(asignacionFamiliar);
            return ResponseEntity.ok(nuevaAsignacion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody AsignacionFamiliar asignacionFamiliar) {
        try {
            AsignacionFamiliar asignacionActualizada = asignacionFamiliarService.actualizar(id, asignacionFamiliar);
            return ResponseEntity.ok(asignacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            asignacionFamiliarService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 