package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion
) {
    public DatosRespuestaPaciente(Paciente entity) {
        this(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getDocumento(),
                new DatosDireccion(entity.getDireccion())
        );
    }
}
