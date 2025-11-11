package io.github.humaniza.vitaflow.service;

import io.github.humaniza.vitaflow.dto.RegistroDiarioRequestDTO;
import io.github.humaniza.vitaflow.dto.RegistroDiarioResponseDTO;
import io.github.humaniza.vitaflow.model.RegistroDiario;
import io.github.humaniza.vitaflow.repository.RegistroDiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroDiarioService {
    private final RegistroDiarioRepository registroDiarioRepository;

    public RegistroDiarioService(RegistroDiarioRepository registroDiarioRepository) {
        this.registroDiarioRepository = registroDiarioRepository;
    }

    public RegistroDiarioResponseDTO registrarRegistro(RegistroDiarioRequestDTO registroDiarioRequestDTO) {
        RegistroDiario registroSalvo = new RegistroDiario(registroDiarioRequestDTO);
        registroDiarioRepository.save(registroSalvo);
        return new RegistroDiarioResponseDTO(registroSalvo);
    }

    public RegistroDiarioResponseDTO buscarRegistroPorId(Integer registroId) {
        RegistroDiario registroBuscado = registroDiarioRepository.findById(registroId)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado"));
        return new RegistroDiarioResponseDTO(registroBuscado);
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
        registro.atualizarRegistro(registroDiarioRequestDTO);
        return new RegistroDiarioResponseDTO(registro);
    }

    @Transactional
    public RegistroDiarioResponseDTO removerRegistroDiario(Integer registroId) {
        RegistroDiarioResponseDTO registroASerRemovido = this.buscarRegistroPorId(registroId);
        Integer idRegistroRemovido = registroASerRemovido.idPaciente();
        return registroDiarioRepository.removeRegistroDiarioById(idRegistroRemovido);
    }
}
