package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ConsultaRegistroValidacionInterface {
    public static final String ERROR_MESSAGE = "Este medico ya tiene una consulta en ese horario.";
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        if (datosEntrada.idMedico() == null) {
            return;
        }
        boolean medicoYaTieneConsulta = consultaRepository.existsByMedicoIdAndDate(
                datosEntrada.idMedico(),
                datosEntrada.date());
        if (medicoYaTieneConsulta) {
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
