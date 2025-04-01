package pe.edu.vallegrande.prsalariosempleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.prsalariosempleados.model.BoletaPago;
import pe.edu.vallegrande.prsalariosempleados.Service.BoletaPagoService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/boletas-pago")
public class BoletaPagoController {
    
    @Autowired
    private BoletaPagoService boletaPagoService;

    @GetMapping
    public List<BoletaPago> listarTodos() {
        return boletaPagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletaPago> buscarPorId(@PathVariable Long id) {
        return boletaPagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{idEmpleado}")
    public List<BoletaPago> buscarPorEmpleado(@PathVariable Long idEmpleado) {
        return boletaPagoService.buscarPorEmpleado(idEmpleado);
    }

    @GetMapping("/periodo")
    public List<BoletaPago> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return boletaPagoService.buscarPorPeriodo(fechaInicio, fechaFin);
    }

    @GetMapping("/empleado/{idEmpleado}/fecha/{fechaEmision}")
    public ResponseEntity<BoletaPago> buscarPorEmpleadoYFecha(
            @PathVariable Long idEmpleado,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEmision) {
        return boletaPagoService.buscarPorEmpleadoYFecha(idEmpleado, fechaEmision)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BoletaPago guardar(@RequestBody BoletaPago boletaPago) {
        return boletaPagoService.guardar(boletaPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletaPago> actualizar(@PathVariable Long id, @RequestBody BoletaPago boletaPago) {
        try {
            BoletaPago boletaActualizada = boletaPagoService.actualizar(id, boletaPago);
            return ResponseEntity.ok(boletaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            boletaPagoService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 