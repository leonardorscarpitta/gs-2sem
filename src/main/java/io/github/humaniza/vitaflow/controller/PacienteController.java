package io.github.humaniza.vitaflow.controller;

import io.github.humaniza.vitaflow.dto.PacienteRequestDTO;
import io.github.humaniza.vitaflow.dto.PacienteResponseDTO;
import io.github.humaniza.vitaflow.service.ConvertControllersResponseEntities;
import io.github.humaniza.vitaflow.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;
    private final ConvertControllersResponseEntities convertControllersResponseEntities;

    public PacienteController(PacienteService pacienteService, ConvertControllersResponseEntities convertControllersResponseEntities) {
        this.pacienteService = pacienteService;
        this.convertControllersResponseEntities = convertControllersResponseEntities;
    }

    @PostMapping("/registrar")
    public ResponseEntity<HashMap<String, Object>> registrarPaciente(@RequestBody PacienteRequestDTO pacienteRequestDTO) {
        PacienteResponseDTO pacienteResponseDTO = pacienteService.registrarPaciente(pacienteRequestDTO);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(pacienteResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<HashMap<String, Object>> buscarPacientePorCpf(@PathVariable String cpf) {
        PacienteResponseDTO pacienteResponseDTO = pacienteService.buscarPacientePorCpf(cpf);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(pacienteResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<HashMap<String, Object>> listarPacientes() {
        List<PacienteResponseDTO> pacienteResponseDTO = pacienteService.listarPacientes();
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(pacienteResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/atualizar/{cpf}")
    public ResponseEntity<HashMap<String, Object>> atualizarPaciente(@RequestBody PacienteRequestDTO pacienteAtualizado, @PathVariable String cpf) {
        PacienteResponseDTO pacienteResponseDTO = pacienteService.atualizarPaciente(pacienteAtualizado, cpf);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(pacienteResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/remover/{cpf}")
    public ResponseEntity<HashMap<String, Object>> removerPaciente(@PathVariable String cpf) {
        PacienteResponseDTO pacienteResponseDTO = pacienteService.removerPaciente(cpf);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(pacienteResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
