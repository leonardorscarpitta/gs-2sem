package io.github.humaniza.vitaflow.model;

import io.github.humaniza.vitaflow.dto.PacienteRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3)
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @CPF
    @Column(unique = true, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroDiario> registrosDiarios;

    public Paciente(PacienteRequestDTO pacienteRequestDTO) {
        this.nome = pacienteRequestDTO.nome();
        this.dataNascimento = pacienteRequestDTO.dataNascimento();
        this.email = pacienteRequestDTO.email();
        this.cpf = pacienteRequestDTO.cpf();
    }

    public void atualizarDados(PacienteRequestDTO pacienteRequestDTO) {
        this.nome = pacienteRequestDTO.nome();
        this.dataNascimento = pacienteRequestDTO.dataNascimento();
        this.email = pacienteRequestDTO.email();
        this.cpf = pacienteRequestDTO.cpf();
    }
}
