package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ConsultaRegistroValidacionInterface {
    public static final byte START_HOUR = 7;
    public static final byte END_HOUR = 19;
    public static final String ERROR_MESSAGE = "El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas.";

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        boolean domingo = DayOfWeek.SUNDAY.equals(datosEntrada.date().getDayOfWeek());
        boolean antesDeApertura = datosEntrada.date().getHour() < START_HOUR;
        boolean despuesDeCierre = datosEntrada.date().getHour() > END_HOUR;
        if (domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
