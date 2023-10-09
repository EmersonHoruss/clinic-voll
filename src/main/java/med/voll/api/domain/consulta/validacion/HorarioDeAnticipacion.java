package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ConsultaRegistroValidacionInterface {
    public static byte LIMIT_MINUTES = 30;
    public static String ERROR_MESSAGE = "Las consultas deben programarse con al menos 30 minutos de anticipación.";

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        LocalDateTime hora = LocalDateTime.now();
        LocalDateTime horaDeConsulta = datosEntrada.date();
        boolean diferencia = Duration.between(hora, horaDeConsulta).toMinutes() < LIMIT_MINUTES;
        if(diferencia){
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
