package tn.pi.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.pi.entities.Sale;
import tn.pi.repositories.SaleRepository;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class saleController {
    @Autowired
    private SaleRepository saleRepository;

    @GetMapping(path = "/GetSale")
    public String getAllSale(Model model) {
        List<Sale> SaleList = saleRepository.findAll();
        model.addAttribute("sales", SaleList);
        return "GetSale";
    }

    @GetMapping(path = "/addSale")
    public String addSale(Model model){
        return "addSale";
    }

    @PostMapping("/addSale")
    public String addSale(@ModelAttribute Sale sale, Model model) {
        saleRepository.save(sale); // Sauvegarde le formateur dans la base de données
        return "redirect:/sale/GetSale"; // Redirige vers la liste des formateurs après ajout
    }

    @PostMapping("/deleteSale/{id}")
    public String deleteSale(@PathVariable("id") Long id) {
        saleRepository.deleteById(id); // Supprime le formateur par ID
        return "redirect:/sale/GetSale"; // R
    }

    @GetMapping("/editSale/{id}")
    public String editSale(@PathVariable("id") Long id, Model model) {
        Sale sale = saleRepository.findById(id).orElse(null);
        model.addAttribute("sale", sale); // Ajoute le formateur au modèle
        return "editSale"; // Vue pour éditer le formateur
    }

    @PostMapping("/updateSale")
    public String updateSale(@ModelAttribute Sale sale) {
        saleRepository.save(sale); // Met à jour le formateur
        return "redirect:/sale/GetSale"; // Redirige vers la liste des formateurs
    }
}
