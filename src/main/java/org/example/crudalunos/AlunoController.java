package org.example.crudalunos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlunoController {

    private final AlunoRepository repositorio;

    public AlunoController(AlunoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("alunos", repositorio.findAll());
        return "lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "form";
    }

    @PostMapping("/salvar")
    public String salvar(Aluno aluno) {
        repositorio.save(aluno);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", repositorio.findById(id).orElse(null));
        return "form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repositorio.deleteById(id);
        return "redirect:/";
    }
}
