package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.Paciente;

import java.time.LocalDateTime;

public record PacienteResponseDTO(
        String nome,
        LocalDateTime dataNascimento,
        String email) {
    public PacienteResponseDTO(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getEmail()
        );
    }
}
