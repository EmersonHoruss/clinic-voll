package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Cancelacion cancelacion;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime date) {
        this.medico = medico;
        this.paciente = paciente;
        this.date = date;
    }

    public void cancelar(Cancelacion cancelacion) {
        this.cancelacion = cancelacion;
    }
}
