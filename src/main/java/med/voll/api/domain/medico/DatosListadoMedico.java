package med.voll.api.domain.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public DatosListadoMedico(Medico entity){
        this(
                entity.getId(),
                entity.getNombre(),
                entity.getEspecialidad().toString(),
                entity.getDocumento(),
                entity.getEmail()
        );
    }
}
