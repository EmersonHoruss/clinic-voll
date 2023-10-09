package med.voll.api.domain.consulta.validacion;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadRequerida implements ConsultaRegistroValidacionInterface{
    public static final String ERROR_MESSAGE = "Debe seleccionarse una especialidad para el medico.";
    @Override
    public void validar(DatosRegistroConsulta datosEntrada) {
        if(datosEntrada.idMedico()==null && datosEntrada.especialidad()==null){
            throw new ValidationException(ERROR_MESSAGE);
        }
    }
}
