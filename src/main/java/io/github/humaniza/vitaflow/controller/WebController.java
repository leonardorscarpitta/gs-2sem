package io.github.humaniza.vitaflow.controller;

import io.github.humaniza.vitaflow.dto.PacienteRequestDTO;
import io.github.humaniza.vitaflow.dto.PacienteResponseDTO;
import io.github.humaniza.vitaflow.dto.RegistroDiarioRequestDTO;
import io.github.humaniza.vitaflow.dto.RegistroDiarioResponseDTO;
import io.github.humaniza.vitaflow.model.EstadoMental;
import io.github.humaniza.vitaflow.service.PacienteService;
import io.github.humaniza.vitaflow.service.RegistroDiarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebController {
    private final PacienteService pacienteService;
    private final RegistroDiarioService registroDiarioService;

    public WebController(PacienteService pacienteService, RegistroDiarioService registroDiarioService) {
        this.pacienteService = pacienteService;
        this.registroDiarioService = registroDiarioService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        List<PacienteResponseDTO> pacientes = pacienteService.listarPacientes();
        model.addAttribute("pacientes", pacientes);
        return "pacientes/lista";
    }

    @GetMapping("/pacientes/novo")
    public String novoPacienteForm(Model model) {
        model.addAttribute("paciente", new PacienteRequestDTO(null, null, null, null));
        return "pacientes/form";
    }

    @PostMapping("/pacientes/salvar")
    public String salvarPaciente(@ModelAttribute PacienteRequestDTO paciente, RedirectAttributes redirectAttributes) {
        try {
            pacienteService.registrarPaciente(paciente);
            redirectAttributes.addFlashAttribute("mensagem", "Paciente registrado com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao registrar paciente: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/editar/{cpf}")
    public String editarPacienteForm(@PathVariable String cpf, Model model, RedirectAttributes redirectAttributes) {
        try {
            PacienteResponseDTO paciente = pacienteService.buscarPacientePorCpf(cpf);
            model.addAttribute("paciente", paciente);
            model.addAttribute("cpf", cpf);
            return "pacientes/editar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Paciente não encontrado!");
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/pacientes";
        }
    }

    @PostMapping("/pacientes/atualizar/{cpf}")
    public String atualizarPaciente(@PathVariable String cpf, @ModelAttribute PacienteRequestDTO paciente, RedirectAttributes redirectAttributes) {
        try {
            pacienteService.atualizarPaciente(paciente, cpf);
            redirectAttributes.addFlashAttribute("mensagem", "Paciente atualizado com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar paciente: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/remover/{cpf}")
    public String removerPaciente(@PathVariable String cpf, RedirectAttributes redirectAttributes) {
        try {
            pacienteService.removerPaciente(cpf);
            redirectAttributes.addFlashAttribute("mensagem", "Paciente removido com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao remover paciente: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/pacientes";
    }

    // Registros Diários
    @GetMapping("/registros")
    public String listarRegistros(Model model) {
        List<RegistroDiarioResponseDTO> registros = registroDiarioService.listarRegistros();
        model.addAttribute("registros", registros);
        return "registros/lista";
    }

    @GetMapping("/registros/novo")
    public String novoRegistroForm(Model model) {
        List<PacienteResponseDTO> pacientes = pacienteService.listarPacientes();
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("registro", new RegistroDiarioRequestDTO(null, null, null, null));
        model.addAttribute("estadosMentais", EstadoMental.values());
        return "registros/form";
    }

    @PostMapping("/registros/salvar")
    public String salvarRegistro(@ModelAttribute RegistroDiarioRequestDTO registro, RedirectAttributes redirectAttributes) {
        try {
            registroDiarioService.registrarRegistro(registro);
            redirectAttributes.addFlashAttribute("mensagem", "Registro salvo com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao salvar registro: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/registros";
    }

    @GetMapping("/registros/editar/{id}")
    public String editarRegistroForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            RegistroDiarioResponseDTO registro = registroDiarioService.buscarRegistroPorId(id);
            List<PacienteResponseDTO> pacientes = pacienteService.listarPacientes();
            model.addAttribute("registro", registro);
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("estadosMentais", EstadoMental.values());
            model.addAttribute("id", id);
            return "registros/editar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Registro não encontrado!");
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/registros";
        }
    }

    @PostMapping("/registros/atualizar/{id}")
    public String atualizarRegistro(@PathVariable Integer id, @ModelAttribute RegistroDiarioRequestDTO registro, RedirectAttributes redirectAttributes) {
        try {
            registroDiarioService.atualizarRegistro(registro, id);
            redirectAttributes.addFlashAttribute("mensagem", "Registro atualizado com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar registro: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/registros";
    }

    @GetMapping("/registros/remover/{id}")
    public String removerRegistro(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            registroDiarioService.removerRegistroDiario(id);
            redirectAttributes.addFlashAttribute("mensagem", "Registro removido com sucesso!");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao remover registro: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }
        return "redirect:/registros";
    }
}

