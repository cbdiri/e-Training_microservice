package tn.pi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Candidat;
import tn.pi.repositories.CandidatRepository;

import java.util.List;

@Controller
@RequestMapping("/candidat")
public class CandidatRestController {

private final CandidatRepository candidatRepository;

    public CandidatRestController(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }
    @GetMapping("/candidats")
    public List<Candidat> getAllCandidat() {
        List<Candidat> candidats = candidatRepository.findAll();

        return candidats;
    }

    @GetMapping(path = "/GetCandidats")
    public String getAllFormateur(Model model) {
        List<Candidat> candidatList = candidatRepository.findAll();
        model.addAttribute("candidats", candidatList);
        return "GetCandidats";
    }

    @GetMapping(path = "/addCandidat")
    public String addCandidat(Model model){
        return "addCandidat";
    }

    @PostMapping("/addCandidat")
    public String addCandidat(@ModelAttribute Candidat candidat, Model model) {

        candidatRepository.save(candidat);
        return "redirect:/candidat/GetCandidats";
    }

    @PostMapping("/deleteCandidat/{id}")
    public String deleteFormateur(@PathVariable("id") Long id) {
        candidatRepository.deleteById(id);
        return "redirect:/candidat/GetCandidats";
    }

    @GetMapping("/editCandidat/{id}")
    public String editCandidat(@PathVariable("id") Long id, Model model) {
        Candidat candidat = candidatRepository.findById(id).orElse(null);
        model.addAttribute("candidat", candidat);
        return "editCandidat"; // Vue pour éditer le formateur
    }

    @PostMapping("/updateCandidat")
    public String updateCandidat(@ModelAttribute Candidat candidat) {
        candidatRepository.save(candidat);
        return "redirect:/candidat/GetCandidats";
    }

}
