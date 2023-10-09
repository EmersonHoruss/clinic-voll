package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosListadoConsulta(
        Long id,
        Long idMedico,
        String nombreMedico,
        Especialidad especialidad,
        Long idPaciente,
        String nombrePaciente,
        LocalDateTime date,
        Cancelacion cancelacion
) {
    public DatosListadoConsulta(Consulta consulta) {
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
