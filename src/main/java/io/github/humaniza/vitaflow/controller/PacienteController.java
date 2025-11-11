package io.github.humaniza.vitaflow.controller;

import io.github.humaniza.vitaflow.service.ConvertControllersResponseEntities;
import io.github.humaniza.vitaflow.service.PacienteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;
    private final ConvertControllersResponseEntities convertControllersResponseEntities;

    public PacienteController(PacienteService pacienteService, ConvertControllersResponseEntities convertControllersResponseEntities) {
        this.pacienteService = pacienteService;
        this.convertControllersResponseEntities = convertControllersResponseEntities;
    }
}
