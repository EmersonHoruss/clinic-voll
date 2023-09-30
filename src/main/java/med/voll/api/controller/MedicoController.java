package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
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
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> ResponseEntity(
            @RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
            UriComponentsBuilder uriBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaMedico(medico));
    }

    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> listarUnMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }
}
