package io.github.humaniza.vitaflow.model;

import io.github.humaniza.vitaflow.dto.RegistroDiarioRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registroDiario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    private EstadoMental estadoMental;

    @NotNull
    private Integer qualidadeSono;

    @NotNull
    private Integer qualidadeHumor;

    public RegistroDiario(RegistroDiarioRequestDTO registroDiarioRequestDTO) {
        this.estadoMental = registroDiarioRequestDTO.estadoMental();
        this.qualidadeSono = registroDiarioRequestDTO.qualidadeSono();
        this.qualidadeHumor = registroDiarioRequestDTO.qualidadeHumor();
    }

    public void atualizarRegistro(RegistroDiarioRequestDTO registroDiarioRequestDTO) {
        this.estadoMental = registroDiarioRequestDTO.estadoMental();
        this.qualidadeSono = registroDiarioRequestDTO.qualidadeSono();
        this.qualidadeHumor = registroDiarioRequestDTO.qualidadeHumor();
    }
}
