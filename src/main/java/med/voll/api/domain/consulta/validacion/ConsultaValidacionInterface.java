package med.voll.api.domain.consulta.validacion;

import med.voll.api.domain.consulta.DatosEntradaConsultaInterface;

public interface ConsultaValidacionInterface<E extends DatosEntradaConsultaInterface> {
    void validar(E datosEntrada);
}
