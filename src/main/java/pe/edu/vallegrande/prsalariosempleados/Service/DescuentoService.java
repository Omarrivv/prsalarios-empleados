package pe.edu.vallegrande.prsalariosempleados.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.prsalariosempleados.model.Descuento;
import pe.edu.vallegrande.prsalariosempleados.Repository.DescuentoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DescuentoService {
    
    @Autowired
    private DescuentoRepository descuentoRepository;

    public List<Descuento> listarTodos() {
        return descuentoRepository.findAll();
    }

    public Optional<Descuento> buscarPorId(Long id) {
        return descuentoRepository.findById(id);
    }

    public Optional<Descuento> buscarPorEmpleado(Long idEmpleado) {
        return descuentoRepository.findByEmpleadoIdEmpleado(idEmpleado);
    }

    public Descuento guardar(Descuento descuento) {
        return descuentoRepository.save(descuento);
    }

    public Descuento actualizar(Long id, Descuento descuento) {
        if (!descuentoRepository.existsById(id)) {
            throw new RuntimeException("Descuento no encontrado");
        }
        descuento.setIdDescuento(id);
        return descuentoRepository.save(descuento);
    }

    public void eliminar(Long id) {
        if (!descuentoRepository.existsById(id)) {
            throw new RuntimeException("Descuento no encontrado");
        }
        descuentoRepository.deleteById(id);
    }
} 