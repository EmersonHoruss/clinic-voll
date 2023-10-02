package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    private String documento;
    private Boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistro) {
        this.activo = true;
        this.nombre = datosRegistro.nombre();
        this.telefono = datosRegistro.telefono();
        this.email = datosRegistro.email();
        this.documento = datosRegistro.documento();
        this.direccion = new Direccion(datosRegistro.direccion());
    }

    public void actualizarDatos(DatosActualizarPaciente datosActualizar) {
        if (datosActualizar.nombre() != null) {
            this.nombre = datosActualizar.nombre();
        }
        if (datosActualizar.documento() != null) {
            this.documento = datosActualizar.documento();
        }
        if(datosActualizar.direccion() != null){
            this.direccion.actualizarDatos(datosActualizar.direccion());
        }
    }

    public void desactivar() {
        activo=false;
    }
}
