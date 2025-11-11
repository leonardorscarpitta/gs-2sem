package io.github.humaniza.vitaflow.mapper;

import io.github.humaniza.vitaflow.dto.RegistroDiarioRequestDTO;
import io.github.humaniza.vitaflow.dto.RegistroDiarioResponseDTO;
import io.github.humaniza.vitaflow.model.RegistroDiario;

public class RegistroDiarioMapper {

    public RegistroDiario toEntity(RegistroDiarioRequestDTO request) {
        return new RegistroDiario(request);
    }

    public RegistroDiarioResponseDTO toResponse(RegistroDiario registroDiario) {
        return new RegistroDiarioResponseDTO(registroDiario);
    }
}
