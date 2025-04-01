package pe.edu.vallegrande.prsalariosempleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.prsalariosempleados.model.Descuento;
import pe.edu.vallegrande.prsalariosempleados.Service.DescuentoService;
import java.util.List;

@RestController
@RequestMapping("/api/descuentos")
public class DescuentoController {
    
    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public List<Descuento> listarTodos() {
        return descuentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Descuento> buscarPorId(@PathVariable Long id) {
        return descuentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<Descuento> buscarPorEmpleado(@PathVariable Long idEmpleado) {
        return descuentoService.buscarPorEmpleado(idEmpleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Descuento guardar(@RequestBody Descuento descuento) {
        return descuentoService.guardar(descuento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Descuento> actualizar(@PathVariable Long id, @RequestBody Descuento descuento) {
        try {
            Descuento descuentoActualizado = descuentoService.actualizar(id, descuento);
            return ResponseEntity.ok(descuentoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            descuentoService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 