package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRegistroConsulta(
        @NotNull
        Long idPaciente,
        Long idMedico,
        @Future
        LocalDateTime date,
        Especialidad especialidad
) implements DatosEntradaConsultaInterface {
        // probar si puedes enviar los datos con el constructor desde postman
        // el programador piensa que sí lo va aceptar, la cosa es: lo sigue validadndo?
        public DatosRegistroConsulta(
                Paciente paciente,
                Medico medico,
                LocalDateTime date,
                Especialidad especialidad
        ){
                this(
                        paciente.getId(),
                        medico.getId(),
                        date,
                        especialidad
                );
        }
}
