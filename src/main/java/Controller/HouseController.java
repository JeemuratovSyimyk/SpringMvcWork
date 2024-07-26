package Controller;

import ch.qos.logback.core.model.Model;
import entity.AgencyService;
import entity.House;
import entity.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @Autowired
    private AgencyService agencyService;

    @GetMapping
    public String getAllHouses(Model model) {
        List<House> houses = houseService.getAllHouses();
        model.addText("houses");
        return "houses";
    }

    @GetMapping("/create")
    public String createHouseForm(Model model) {
        model.addText("house");
        model.addText("agencies");
        return "create-house";
    }

    @PostMapping
    public String saveHouse(@ModelAttribute("house") House house) {
        houseService.saveHouse(house);
        return "redirect:/houses";
    }

    @GetMapping("/{id}")
    public String getHouseById(@PathVariable("id") Long id, Model model) {
        Optional<House> house = houseService.getHouseById(id);
        if (house.isPresent()) {
            model.addText("house");
            return "house";
        } else {
            return "redirect:/houses";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteHouse(@PathVariable("id") Long id) {
        houseService.deleteHouse(id);
        return "redirect:/houses";
}
    }