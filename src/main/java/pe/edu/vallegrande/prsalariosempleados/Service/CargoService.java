package pe.edu.vallegrande.prsalariosempleados.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.prsalariosempleados.model.Cargo;
import pe.edu.vallegrande.prsalariosempleados.Repository.CargoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    
    @Autowired
    private CargoRepository cargoRepository;

    public List<Cargo> listarTodos() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> buscarPorId(Long id) {
        return cargoRepository.findById(id);
    }

    public Cargo guardar(Cargo cargo) {
        if (cargoRepository.existsByNombreCargo(cargo.getNombreCargo())) {
            throw new RuntimeException("Ya existe un cargo con ese nombre");
        }
        return cargoRepository.save(cargo);
    }

    public Cargo actualizar(Long id, Cargo cargo) {
        if (!cargoRepository.existsById(id)) {
            throw new RuntimeException("Cargo no encontrado");
        }
        cargo.setIdCargo(id);
        return cargoRepository.save(cargo);
    }

    public void eliminar(Long id) {
        if (!cargoRepository.existsById(id)) {
            throw new RuntimeException("Cargo no encontrado");
        }
        cargoRepository.deleteById(id);
    }
} 