package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoNoDisponible implements ConsultaRegistroValidacionInterface {
    public static final String ERROR_MESSAGE = "El médico ya no está disponible.";
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        if (datosEntrada.idMedico() != null || datosEntrada.especialidad() == null) {
            return;
        }
        Medico medico = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(
                datosEntrada.especialidad(),
                datosEntrada.date()
        );
        if(medico == null){
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
