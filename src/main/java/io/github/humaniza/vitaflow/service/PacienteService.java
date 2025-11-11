package io.github.humaniza.vitaflow.service;

import io.github.humaniza.vitaflow.dto.PacienteRequestDTO;
import io.github.humaniza.vitaflow.dto.PacienteResponseDTO;
import io.github.humaniza.vitaflow.model.Paciente;
import io.github.humaniza.vitaflow.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponseDTO registrarPaciente(PacienteRequestDTO pacienteRequestDTO) {
        Paciente pacienteSalvo = new Paciente(pacienteRequestDTO);
        pacienteRepository.save(pacienteSalvo);
        return new PacienteResponseDTO(pacienteSalvo);
    }

    public PacienteResponseDTO buscarPacientePorCpf(String pacienteCpf) {
        Paciente pacienteBuscado = pacienteRepository.findByCpf(pacienteCpf);
        return new PacienteResponseDTO(pacienteBuscado);
    }

    public List<PacienteResponseDTO> listarPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteResponseDTO::new)
                .toList();
    }

    @Transactional
    public PacienteResponseDTO atualizarPaciente(PacienteRequestDTO pacienteRequestDTO, String cpfASerAtualizado) {
        Paciente paciente = pacienteRepository.findByCpf(cpfASerAtualizado);
        paciente.atualizarDados(pacienteRequestDTO);
        return new PacienteResponseDTO(paciente);
    }

    @Transactional
    public PacienteResponseDTO removerPaciente(String pacienteCpf) {
        PacienteResponseDTO pacienteASerRemovido = this.buscarPacientePorCpf(pacienteCpf);
        String emailPacienteRemovido = pacienteASerRemovido.email();
        return pacienteRepository.removePacienteByCpf(pacienteCpf);
    }

}
