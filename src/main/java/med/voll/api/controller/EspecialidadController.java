package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.medico.Especialidad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidades")
@SecurityRequirement(name = "jwt")
public class EspecialidadController {
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(Especialidad.values());
    }
}
