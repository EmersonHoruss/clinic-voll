package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PacienteSinConsulta implements ConsultaRegistroValidacionInterface {
    public static final byte START_HOUR = 7;
    public static final byte END_HOUR = 18;
    public static final String ERROR_MESSAGE = "El paciente ya tiene una consulta para ese día.";
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        LocalDateTime primerHorario = datosEntrada.date().withHour(START_HOUR);
        LocalDateTime segundoHorario = datosEntrada.date().withHour(END_HOUR);
        boolean pacienteTieneConsuta=consultaRepository.existsByPacienteIdAndDateBetween(
                datosEntrada.idPaciente(),
                primerHorario,
                segundoHorario
        );
        if(pacienteTieneConsuta){
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
