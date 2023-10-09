package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ConsultaRegistroValidacionInterface {
    public static final String ERROR_MESSAGE = "No se puede permitir agendar citas con pacientes inactivos en el sistema.";
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        boolean pacienteEstaActivo = pacienteRepository.estaActivo(datosEntrada.idPaciente());
        if (!pacienteEstaActivo) {
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
