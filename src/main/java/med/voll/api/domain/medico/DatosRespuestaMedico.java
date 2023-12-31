package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        Especialidad especialidad,
        String documento,
        DatosDireccion direccion
) {
    public DatosRespuestaMedico(Medico entity) {
        this(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getEspecialidad(),
                entity.getDocumento(),
                new DatosDireccion(entity.getDireccion())
        );
    }
}
