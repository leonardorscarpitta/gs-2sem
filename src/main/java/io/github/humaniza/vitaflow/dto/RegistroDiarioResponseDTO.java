package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.RegistroDiario;

public record RegistroDiarioResponseDTO(Integer idPaciente,
                                        String estadoMental,
                                        Integer qualidadeSono,
                                        Integer qualidadeHumor) {

    public RegistroDiarioResponseDTO(RegistroDiario registroDiario) {
        this.idPaciente = registroDiario.getIdPaciente().getId();
        this.estadoMental = registroDiario.getEstadoMental();
        this.qualidadeSono = registroDiario.getQualidadeSono();
        this.qualidadeHumor = registroDiario.getQualidadeHumor();
    }
}
