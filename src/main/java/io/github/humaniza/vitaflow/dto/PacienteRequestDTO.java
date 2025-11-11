package io.github.humaniza.vitaflow.dto;

import io.github.humaniza.vitaflow.model.Paciente;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PacienteRequestDTO(String nome,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                 LocalDate dataNascimento,
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
