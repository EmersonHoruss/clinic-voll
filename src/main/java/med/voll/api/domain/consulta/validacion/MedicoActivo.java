package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ConsultaRegistroValidacionInterface {
    public static final String ERROR_MESSAGE = "No se puede permitir agendar citas con medicos inactivos en el sistema";
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        if (datosEntrada.idMedico() == null) {
            return;
        }
        boolean medicoEstaActivo = medicoRepository.estaActivo(datosEntrada.idMedico());
        if (!medicoEstaActivo) {
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
