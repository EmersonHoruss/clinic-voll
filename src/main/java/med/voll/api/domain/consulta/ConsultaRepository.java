package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDateBetween(Long idPaciente, LocalDateTime primerHorario,LocalDateTime segundoHorario);
    Boolean existsByMedicoIdAndDate(Long idMedico, LocalDateTime date);
}
