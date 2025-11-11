package io.github.humaniza.vitaflow.mapper;

import io.github.humaniza.vitaflow.dto.PacienteRequestDTO;
import io.github.humaniza.vitaflow.dto.PacienteResponseDTO;
import io.github.humaniza.vitaflow.model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteMapper {

    public Paciente toEntity(PacienteRequestDTO request) {
        return new Paciente(request);
    }

    public PacienteResponseDTO toResponse(Paciente paciente) {
        return new PacienteResponseDTO(paciente);
    }
}
