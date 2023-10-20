package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistro) {
        this.activo = true;
        this.nombre = datosRegistro.nombre();
        this.telefono = datosRegistro.telefono();
        this.email = datosRegistro.email();
        this.documento = datosRegistro.documento();
        this.especialidad = datosRegistro.especialidad();
        this.direccion = new Direccion(datosRegistro.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizar) {
        if (datosActualizar.nombre() != null) {
            this.nombre = datosActualizar.nombre();
        }
        if (datosActualizar.telefono() != null) {
            this.telefono = datosActualizar.telefono();
        }
        if(datosActualizar.direccion() != null){
            this.direccion.actualizarDatos(datosActualizar.direccion());
        }
    }

    public void desactivar() {
        activo=false;
    }
}
