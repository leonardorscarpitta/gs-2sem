package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.EstadoMental;
import io.github.humaniza.vitaflow.model.RegistroDiario;

public record RegistroDiarioResponseDTO(Integer idPaciente,
                                        EstadoMental estadoMental,
                                        Integer qualidadeSono,
                                        Integer qualidadeHumor) {

    public RegistroDiarioResponseDTO(RegistroDiario registroDiario) {
        this(
                registroDiario.getIdPaciente().getId(),
                registroDiario.getEstadoMental(),
                registroDiario.getQualidadeSono(),
                registroDiario.getQualidadeHumor()
        );
    }
}
