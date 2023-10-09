package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacion.ConsultaCancelacionValidacionInterface;
import med.voll.api.domain.consulta.validacion.ConsultaRegistroValidacionInterface;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private List<ConsultaRegistroValidacionInterface> validadoresDeRegistroDeConsulta;
    @Autowired
    private List<ConsultaCancelacionValidacionInterface> validadoresDeCancelacionDeConsulta;

    public Consulta save(DatosRegistroConsulta datosRegistro) {
        Paciente paciente = pacienteRepository.getReferenceById(datosRegistro.idPaciente());
        Medico medico = getMedico(datosRegistro);
        validadoresDeRegistroDeConsulta.forEach(v -> v.validar(datosRegistro));
        Consulta consulta = new Consulta(medico, paciente, datosRegistro.date());
        return consultaRepository.save(consulta);
    }

    private Medico getMedico(DatosRegistroConsulta datosRegistro) {
        if (datosRegistro.idMedico() != null) {
            return medicoRepository.getReferenceById(datosRegistro.idMedico());
        }
        return seleccionarMedicoAutomaticamente(datosRegistro);
    }

    private Medico seleccionarMedicoAutomaticamente(DatosRegistroConsulta datosRegistro) {
        return null;
    }

    public Consulta cancel(DatosCancelarConsulta datosCancelar) {
        Consulta consulta = consultaRepository.getReferenceById(datosCancelar.id());
        validadoresDeCancelacionDeConsulta.forEach(v -> v.validar(datosCancelar));
        consulta.cancelar(datosCancelar.cancelacion());
        return consultaRepository.save(consulta);
    }
}
