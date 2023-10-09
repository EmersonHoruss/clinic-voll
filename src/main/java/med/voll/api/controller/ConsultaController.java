package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    /*@Autowired
    private ConsultaRepository repository;*/

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaConsulta> registrar(
            @RequestBody @Valid DatosRegistroConsulta datosRegistro,
            UriComponentsBuilder uriBuilder) {
        System.out.println(datosRegistro);
        Consulta consulta = service.save(datosRegistro);
        URI uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaConsulta(consulta));
        /**/
        //return null;
    }
/*
    @GetMapping
    public Page<DatosListadoConsulta> listar(@PageableDefault(size = 2) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListadoConsulta::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaConsulta> cancelar(
            @RequestBody @Valid DatosCancelarConsulta datosCancelar) {
        Consulta consulta = service.cancel(datosCancelar);
        return ResponseEntity.ok(new DatosRespuestaConsulta(consulta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaConsulta> listarPorId(@PathVariable Long id) {
        Consulta consulta = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaConsulta(consulta));
    }*/
}
