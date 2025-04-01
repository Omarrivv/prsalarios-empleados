package pe.edu.vallegrande.prsalariosempleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.prsalariosempleados.model.Salario;
import pe.edu.vallegrande.prsalariosempleados.Service.SalarioService;

import java.util.List;

@RestController
@RequestMapping("/api/salarios")
@CrossOrigin(origins = "*")
public class SalarioController {

    @Autowired
    private SalarioService salarioService;

    @GetMapping
    public List<Salario> listarTodos() {
        return salarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salario> buscarPorId(@PathVariable Long id) {
        return salarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{idEmpleado}")
    public List<Salario> buscarPorEmpleado(@PathVariable Long idEmpleado) {
        return salarioService.buscarPorEmpleado(idEmpleado);
    }

    @PostMapping
    public ResponseEntity<Salario> guardar(@RequestBody Salario salario) {
        try {
            Salario nuevoSalario = salarioService.guardar(salario);
            return ResponseEntity.ok(nuevoSalario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salario> actualizar(@PathVariable Long id, @RequestBody Salario salario) {
        try {
            Salario salarioActualizado = salarioService.actualizar(id, salario);
            return ResponseEntity.ok(salarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            salarioService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 