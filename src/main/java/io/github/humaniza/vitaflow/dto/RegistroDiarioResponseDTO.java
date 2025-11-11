package io.github.humaniza.vitaflow.dto;

public record RegistroDiarioResponseDTO(Integer idPaciente,
                                        String estadoMental,
                                        Integer qualidadeSono,
                                        Integer qualidadeHumor) {

    public RegistroDiarioResponseDTO(Integer idPaciente, String estadoMental, Integer qualidadeSono, Integer qualidadeHumor) {
        this.idPaciente = idPaciente;
        this.estadoMental = estadoMental;
        this.qualidadeSono = qualidadeSono;
        this.qualidadeHumor = qualidadeHumor;
    }
}
