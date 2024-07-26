package Controller;

import ch.qos.logback.core.model.Model;
import entity.Agency;
import entity.AgencyExistsException;
import entity.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agencies")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping
    public String getAllAgencies(Model model) {
        List<Agency> agencies = agencyService.getAllAgencies();
        model.addText("agencies");
        return "agencies";
    }

    @GetMapping("/create")
    public String createAgencyForm(Model model) {
        model.addText("agency");
        return "create-agency";
    }

    @PostMapping
    public String saveAgency(@ModelAttribute Agency agency) throws AgencyExistsException {
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}")
    public String getAgencyById(@PathVariable("id") Long id, Model model) {
        Optional<Agency> agency = agencyService.getAgencyById(id);
        if (agency.isPresent()) {
            model.addText("agency");
            return "agency";
        } else {
            return "redirect:/agencies";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAgency(@PathVariable("id") Long id) {
        agencyService.deleteAgency(id);
        return "redirect:/agencies";
    }
}