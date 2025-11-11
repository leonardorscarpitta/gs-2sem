package io.github.humaniza.vitaflow.controller;

import io.github.humaniza.vitaflow.dto.RegistroDiarioRequestDTO;
import io.github.humaniza.vitaflow.dto.RegistroDiarioResponseDTO;
import io.github.humaniza.vitaflow.service.ConvertControllersResponseEntities;
import io.github.humaniza.vitaflow.service.RegistroDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/diario")
public class RegistroDiarioController {
    private final RegistroDiarioService registroDiarioService;
    private final ConvertControllersResponseEntities convertControllersResponseEntities;

    public RegistroDiarioController(RegistroDiarioService registroDiarioService, ConvertControllersResponseEntities convertControllersResponseEntities) {
        this.registroDiarioService = registroDiarioService;
        this.convertControllersResponseEntities = convertControllersResponseEntities;
    }

    @PostMapping("/registrar")
    public ResponseEntity<HashMap<String, Object>> registrarRegistro(@RequestBody RegistroDiarioRequestDTO registroDiarioRequestDTO) {
        RegistroDiarioResponseDTO registroDiarioResponseDTO = registroDiarioService.registrarRegistro(registroDiarioRequestDTO);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(registroDiarioResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarRegistroPorId(@PathVariable Integer id) {
        RegistroDiarioResponseDTO registroDiarioResponseDTO = registroDiarioService.buscarRegistroPorId(id);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(registroDiarioResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<HashMap<String, Object>> listarRegistros() {
        List<RegistroDiarioResponseDTO> registroDiarioResponseDTO = registroDiarioService.listarRegistros();
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(registroDiarioResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<HashMap<String, Object>> atualizarRegistro(@RequestBody RegistroDiarioRequestDTO registroDiarioRequestDTO, @PathVariable Integer id) {
        RegistroDiarioResponseDTO registroDiarioResponseDTO = registroDiarioService.atualizarRegistro(registroDiarioRequestDTO, id);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(registroDiarioResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/remover/{id}")
    public ResponseEntity<HashMap<String, Object>> removerRegistroDiario(@PathVariable Integer id) {
        RegistroDiarioResponseDTO registroDiarioResponseDTO = registroDiarioService.removerRegistroDiario(id);
        HashMap<String, Object> response = convertControllersResponseEntities.manageStatus(registroDiarioResponseDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
