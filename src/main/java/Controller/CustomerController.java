package Controller;

import ch.qos.logback.core.model.Model;
import entity.AgencyService;
import entity.Customer;
import entity.CustomerExistsException;
import entity.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AgencyService agencyService;

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addText("customers");
        return "customers";
    }

    @GetMapping("/create")
    public String createCustomerForm(Model model) {
        model.addText("customer");
        model.addText("agencies");
        return "create-customer";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute(
            ) Customer customer) throws CustomerExistsException {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            model.addText("customer");
            return "customer";
        } else {
            return "redirect:/customers";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}