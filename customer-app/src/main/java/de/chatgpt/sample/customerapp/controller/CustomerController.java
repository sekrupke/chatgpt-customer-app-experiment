package de.chatgpt.sample.customerapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.chatgpt.sample.customerapp.models.Customer;
import de.chatgpt.sample.customerapp.repositories.CustomerRepository;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String customerList(Model model) {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customer_list";
    }
}
