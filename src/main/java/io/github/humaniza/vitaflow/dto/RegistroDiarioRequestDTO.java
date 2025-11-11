package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.EstadoMental;
import io.github.humaniza.vitaflow.model.RegistroDiario;

public record RegistroDiarioRequestDTO(Integer idPaciente,
                                        EstadoMental estadoMental,
                                        Integer qualidadeSono,
                                        Integer qualidadeHumor) {
    public RegistroDiarioRequestDTO(RegistroDiario registroDiario) {
        this(
                registroDiario.getPaciente().getId(),
                registroDiario.getEstadoMental(),
                registroDiario.getQualidadeSono(),
                registroDiario.getQualidadeHumor()
        );
    }
}
