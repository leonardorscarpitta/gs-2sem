package io.github.humaniza.vitaflow.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente idPaciente;

    @Enumerated(EnumType.STRING)
    private EstadoMental estadoMental;

    private Integer qualidadeSono;

    private Integer qualidadeHumor;
}
