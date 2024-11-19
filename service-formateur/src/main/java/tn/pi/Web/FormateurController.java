package tn.pi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Formateur;
import tn.pi.repositories.FormateurRepository;

import java.util.List;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

    @Autowired
    private FormateurRepository formateurRepository;

    @GetMapping(path = "/GetFormateus")
    public String getAllFormateur(Model model) {
        List<Formateur> formateurList = formateurRepository.findAll();
        model.addAttribute("formateurs", formateurList);
        return "GetFormateus";
    }

    @GetMapping(path = "/addFormateur")
    public String addFormateur(Model model){
        return "addFormateur";
    }

    @PostMapping("/addFormateur")
    public String addFormateur(@ModelAttribute Formateur formateur,Model model) {
        formateurRepository.save(formateur); // Sauvegarde le formateur dans la base de données
        return "redirect:/formateur/GetFormateus"; // Redirige vers la liste des formateurs après ajout
    }

    @PostMapping("/deleteFormateur/{id}")
    public String deleteFormateur(@PathVariable("id") Long id) {
        formateurRepository.deleteById(id); // Supprime le formateur par ID
        return "redirect:/formateur/GetFormateus"; // R
    }

    @GetMapping("/editFormateur/{id}")
    public String editFormateur(@PathVariable("id") Long id, Model model) {
        Formateur formateur = formateurRepository.findById(id).orElse(null);
        model.addAttribute("formateur", formateur); // Ajoute le formateur au modèle
        return "editFormateur"; // Vue pour éditer le formateur
    }

    @PostMapping("/updateFormateur")
    public String updateFormateur(@ModelAttribute Formateur formateur) {
        formateurRepository.save(formateur); // Met à jour le formateur
        return "redirect:/formateur/GetFormateus"; // Redirige vers la liste des formateurs
    }
}
