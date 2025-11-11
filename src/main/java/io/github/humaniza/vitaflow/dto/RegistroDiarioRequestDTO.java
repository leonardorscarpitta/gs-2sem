package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.Paciente;
import io.github.humaniza.vitaflow.model.RegistroDiario;

public record RegistroDiarioRequestDTO(Integer idPaciente,
                                        String estadoMental,
                                        Integer qualidadeSono,
                                        Integer qualidadeHumor) {
    public RegistroDiarioRequestDTO(RegistroDiario registroDiario) {
        this(
                registroDiario.getIdPaciente().getId(),
                registroDiario.getEstadoMental().name(),
                registroDiario.getQualidadeSono(),
                registroDiario.getQualidadeHumor()
        );
    }
}
