package io.github.humaniza.vitaflow.service;

import io.github.humaniza.vitaflow.dto.PacienteRequestDTO;
import io.github.humaniza.vitaflow.dto.PacienteResponseDTO;
import io.github.humaniza.vitaflow.mapper.PacienteMapper;
import io.github.humaniza.vitaflow.model.Paciente;
import io.github.humaniza.vitaflow.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteResponseDTO registrarPaciente(PacienteRequestDTO pacienteRequestDTO) {
        Paciente pacienteSalvo = pacienteMapper.toEntity(pacienteRequestDTO);
        pacienteRepository.save(pacienteSalvo);
        return pacienteMapper.toResponse(pacienteSalvo);
    }

    public PacienteResponseDTO buscarPacientePorCpf(String pacienteCpf) {
        Paciente pacienteBuscado = pacienteRepository.findByCpf(pacienteCpf);
        return pacienteMapper.toResponse(pacienteBuscado);
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
        return pacienteMapper.toResponse(paciente);
    }

    @Transactional
    public PacienteResponseDTO removerPaciente(String pacienteCpf) {
        PacienteResponseDTO pacienteASerRemovido = this.buscarPacientePorCpf(pacienteCpf);
        return pacienteRepository.removePacienteByCpf(pacienteCpf);
    }

}
