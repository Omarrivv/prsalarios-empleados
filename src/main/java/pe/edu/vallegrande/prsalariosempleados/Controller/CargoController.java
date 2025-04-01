package pe.edu.vallegrande.prsalariosempleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.prsalariosempleados.model.Cargo;
import pe.edu.vallegrande.prsalariosempleados.Service.CargoService;
import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {
    
    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Cargo> listarTodos() {
        return cargoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> buscarPorId(@PathVariable Long id) {
        return cargoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cargo guardar(@RequestBody Cargo cargo) {
        return cargoService.guardar(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> actualizar(@PathVariable Long id, @RequestBody Cargo cargo) {
        try {
            Cargo cargoActualizado = cargoService.actualizar(id, cargo);
            return ResponseEntity.ok(cargoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            cargoService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 