package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.Paciente;

public record PacienteRequestDTO(String nome,
                                 String dataNascimento,
                                 String email,
                                 String cpf) {
        public PacienteRequestDTO(Paciente paciente) {
            this(
                    paciente.getNome(),
                    paciente.getDataNascimento().toString(),
                    paciente.getEmail(),
                    paciente.getCpf()
            );
    }
}
