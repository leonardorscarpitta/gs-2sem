package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.Paciente;

import java.time.LocalDate;

public record PacienteResponseDTO(
        String nome,
        LocalDate dataNascimento,
        String email,
        String cpf) {
    public PacienteResponseDTO(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getEmail(),
                paciente.getCpf()
        );
    }
}
