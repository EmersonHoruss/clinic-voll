package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "jwt")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> registrar(
            @RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
            UriComponentsBuilder uriBuilder) {
        Medico medico = repository.save(new Medico(datosRegistroMedico));
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaMedico(medico));
    }

    @GetMapping
    public Page<DatosListadoMedico> listar(@PageableDefault(size = 2) Pageable paginacion) {
        return repository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizar(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = repository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.desactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> listarPorId(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }
}
