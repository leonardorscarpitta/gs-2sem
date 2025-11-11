package io.github.humaniza.vitaflow.service;

import io.github.humaniza.vitaflow.dto.RegistroDiarioRequestDTO;
import io.github.humaniza.vitaflow.dto.RegistroDiarioResponseDTO;
import io.github.humaniza.vitaflow.mapper.PacienteMapper;
import io.github.humaniza.vitaflow.mapper.RegistroDiarioMapper;
import io.github.humaniza.vitaflow.model.Paciente;
import io.github.humaniza.vitaflow.model.RegistroDiario;
import io.github.humaniza.vitaflow.repository.PacienteRepository;
import io.github.humaniza.vitaflow.repository.RegistroDiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroDiarioService {
    private final RegistroDiarioRepository registroDiarioRepository;
    private final PacienteRepository pacienteRepository;
    private final RegistroDiarioMapper registroDiarioMapper;

    public RegistroDiarioService(RegistroDiarioRepository registroDiarioRepository, PacienteRepository pacienteRepository, RegistroDiarioMapper registroDiarioMapper) {
        this.registroDiarioRepository = registroDiarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.registroDiarioMapper = registroDiarioMapper;
    }

    public RegistroDiarioResponseDTO registrarRegistro(RegistroDiarioRequestDTO registroDiarioRequestDTO) {
        Paciente paciente = pacienteRepository.findById(registroDiarioRequestDTO.idPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        RegistroDiario registroSalvo = registroDiarioMapper.toEntity(registroDiarioRequestDTO);
        registroSalvo.setPaciente(paciente);
        registroDiarioRepository.save(registroSalvo);
        return registroDiarioMapper.toResponse(registroSalvo);
    }

    public RegistroDiarioResponseDTO buscarRegistroPorId(Integer registroId) {
        RegistroDiario registroBuscado = registroDiarioRepository.findById(registroId)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado"));
        return registroDiarioMapper.toResponse(registroBuscado);
    }

    public List<RegistroDiarioResponseDTO> listarRegistros() {
        return registroDiarioRepository.findAll()
                .stream()
                .map(RegistroDiarioResponseDTO::new)
                .toList();
    }

    @Transactional
    public RegistroDiarioResponseDTO atualizarRegistro(RegistroDiarioRequestDTO registroDiarioRequestDTO, Integer idASerAtualizado) {
        RegistroDiario registro = registroDiarioRepository.findById(idASerAtualizado)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado"));

        if (registroDiarioRequestDTO.idPaciente() != null) {
            Paciente paciente = pacienteRepository.findById(registroDiarioRequestDTO.idPaciente())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
            registro.setPaciente(paciente);
        }

        registro.atualizarRegistro(registroDiarioRequestDTO);
        return registroDiarioMapper.toResponse(registro);
    }

    @Transactional
    public RegistroDiarioResponseDTO removerRegistroDiario(Integer registroId) {
        RegistroDiarioResponseDTO registroASerRemovido = this.buscarRegistroPorId(registroId);
        Integer idRegistroRemovido = registroASerRemovido.idPaciente();
        return registroDiarioRepository.removeRegistroDiarioById(idRegistroRemovido);
    }
}
