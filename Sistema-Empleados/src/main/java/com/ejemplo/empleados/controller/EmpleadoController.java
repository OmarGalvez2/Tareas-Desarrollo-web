package com.ejemplo.empleados.controller;

import com.ejemplo.empleados.model.Empleado;
import com.ejemplo.empleados.repository.EmpleadoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmpleadoController {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/empleados";
    }

    @GetMapping("/empleados")
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoRepository.findAllByOrderByApellidoAscNombreAsc());
        return "empleados/lista";
    }

    @GetMapping("/empleados/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "empleados/formulario";
    }

    @GetMapping("/empleados/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);

        if (empleado == null) {
            redirectAttributes.addFlashAttribute("error", "El empleado no existe");
            return "redirect:/empleados";
        }

        model.addAttribute("empleado", empleado);
        return "empleados/formulario";
    }

    @PostMapping("/empleados/guardar")
    public String guardar(@Valid @ModelAttribute Empleado empleado,
                          BindingResult resultado,
                          RedirectAttributes redirectAttributes) {
        Long idParaValidar = empleado.getId() == null ? -1L : empleado.getId();

        if (empleadoRepository.existsByEmailAndIdNot(empleado.getEmail(), idParaValidar)) {
            resultado.rejectValue("email", "duplicado", "Ya existe un empleado con este correo");
        }

        if (resultado.hasErrors()) {
            return "empleados/formulario";
        }

        boolean esNuevo = empleado.getId() == null;
        empleadoRepository.save(empleado);
        redirectAttributes.addFlashAttribute(
                "mensaje",
                esNuevo ? "Empleado registrado correctamente" : "Empleado actualizado correctamente"
        );
        return "redirect:/empleados";
    }

    @PostMapping("/empleados/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (!empleadoRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("error", "El empleado no existe");
            return "redirect:/empleados";
        }

        empleadoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Empleado eliminado correctamente");
        return "redirect:/empleados";
    }
}
