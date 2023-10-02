package med.voll.api.domain.paciente;

public record DatosListadoPaciente(
        Long id,
        String nombre,
        String documento,
        String email
) {
    public DatosListadoPaciente(Paciente entity){
        this(
                entity.getId(),
                entity.getNombre(),
                entity.getDocumento(),
                entity.getEmail()
        );
    }
}
