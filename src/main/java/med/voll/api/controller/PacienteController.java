package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "jwt")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> registrar(
            @RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
            UriComponentsBuilder uriBuilder) {
        Paciente paciente = repository.save(new Paciente(datosRegistroPaciente));
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaPaciente(paciente));
    }

    @GetMapping
    public Page<DatosListadoPaciente> listar(@PageableDefault(size = 2) Pageable paginacion){
        return repository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizar(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){
        Paciente paciente = repository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.desactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> listarPorId(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }
}
