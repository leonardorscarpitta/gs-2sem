package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.Paciente;

import java.time.LocalDateTime;

public record PacienteRequestDTO(String nome,
                                 LocalDateTime dataNascimento,
                                 String email,
                                 String cpf) {
        public PacienteRequestDTO(Paciente paciente) {
            this(
                    paciente.getNome(),
                    paciente.getDataNascimento(),
                    paciente.getEmail(),
                    paciente.getCpf()
            );
    }
}
