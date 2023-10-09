package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DatosRespuestaConsulta(
        Long id,
        Long idMedico,
        String nombreMedico,
        Especialidad especialidadMedico,
        Long idPaciente,
        String nombrePaciente,
        LocalDateTime date,
        Cancelacion cancelacion
) {
    public DatosRespuestaConsulta(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getMedico().getNombre(),
                consulta.getMedico().getEspecialidad(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNombre(),
                consulta.getDate(),
                consulta.getCancelacion()
        );
    }
}
