package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelarConsulta;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelacionNoDisponible implements ConsultaCancelacionValidacionInterface {
    public static final byte LIMIT_HOURS_TO_CANCEL = 24;
    public static final String ERROR_MESSAGE = "Una cita solo se puede cancelar con al menos 24 horas de anticipación.";
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DatosCancelarConsulta datosEntrada) {
        Consulta consulta = consultaRepository.getReferenceById(datosEntrada.id());
        LocalDateTime fechaConsulta = consulta.getDate();
        LocalDateTime ahora = LocalDateTime.now();
        boolean esPosibleCancelar = Duration.between(ahora,fechaConsulta).toHours() >= LIMIT_HOURS_TO_CANCEL;
        if(!esPosibleCancelar){
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
