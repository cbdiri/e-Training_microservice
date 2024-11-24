package tn.pi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.*;
import tn.pi.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/formation")
public class FormationController {
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private SaleRepository saleRepository;


    @GetMapping(path = "/GetFormation")
    public String getAllFormation(Model model) {
        List<Formation> FormationList = formationRepository.findAll();
        model.addAttribute("formation", FormationList);
        return "GetFormation";
    }

    @GetMapping(path = "/addFormation")
    public String addFormation(Model model){
        model.addAttribute("sale", saleRepository.findAll());
        model.addAttribute("formateur", formateurRepository.findAll());


        return "addFormation";
    }

    @PostMapping("/addFormation")
    public String addFormation(@ModelAttribute Formation formation,Model model) {
       formationRepository.save(formation);
        return "redirect:/formation/GetFormation";
    }

    @PostMapping("/deleteFormation/{id}")
    public String deleteFormation(@PathVariable("id") Long id) {
        formationRepository.deleteById(id); // Supprime le formateur par ID
        return "redirect:/formation/GetFormation"; // R
    }

    @GetMapping("/editFormation/{id}")
    public String editFormation(@PathVariable("id") Long id, Model model) {
        model.addAttribute("sale", saleRepository.findAll());
        model.addAttribute("formateur", formateurRepository.findAll());
        Formation formation = formationRepository.findById(id).orElse(null);
        model.addAttribute("formation", formation); // Ajoute le formateur au modèle
        return "editFormation"; // Vue pour éditer le formateur
    }

    @PostMapping("/updateFormation")
    public String updateFormation(@ModelAttribute Formation formation,Model model) {
        formationRepository.save(formation); // Met à jour le formateur
        return "redirect:/formation/GetFormation"; // Redirige vers la liste des formateurs
    }

    @GetMapping("/detail/{id}")
    public String detailFormation(@PathVariable Long id, Model model) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée : " + id));

        List<Candidat> allCandidats = candidatRepository.findAll();
        List<Candidat> candidatsAffectes = formation.getCandidats(); // Utilise la relation Many-to-Many

        model.addAttribute("formation", formation);
        model.addAttribute("candidats", candidatsAffectes); // Les candidats déjà liés à la formation
        model.addAttribute("allCandidats", allCandidats); // Tous les candidats pour le dropdown
        return "detailFormation";
    }

    @PostMapping("/addCandidat/{id}")
    public String addCandidatToFormation(@PathVariable Long id, @RequestParam Long candidatId) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée : " + id));
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new IllegalArgumentException("Candidat non trouvé : " + candidatId));

        // Ajoute le candidat à la formation s'il n'est pas déjà présent
        if (!formation.getCandidats().contains(candidat)) {
            formation.getCandidats().add(candidat);
            formationRepository.save(formation);
        }

        return "redirect:/formation/detail/" + id; // Redirige vers la page des détails
    }

    @PostMapping("/removeCandidat/{formationId}/{candidatId}")
    public String removeCandidatFromFormation(@PathVariable Long formationId, @PathVariable Long candidatId) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée : " + formationId));
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new IllegalArgumentException("Candidat non trouvé : " + candidatId));

        // Retire le candidat de la formation
        formation.getCandidats().remove(candidat);
        formationRepository.save(formation);

        return "redirect:/formation/detail/" + formationId; // Redirige vers la page des détails
    }




}
