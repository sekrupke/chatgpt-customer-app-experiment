package de.chatgpt.sample.customerapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "customer-list";
    }

    @GetMapping("/customers/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    @PostMapping("/customers")
    public String createCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "create-customer";
        } else {
            // Save the customer to the database
            customerRepository.save(customer);
            return "redirect:/customers";
        }
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
        model.addAttribute("customer", customer);
        return "edit-customer";
    }

    @PostMapping("/customers/edit/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("customer") Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
        // Update the fields of the existing customer with the values from the updated customer
        customer.setSalutation(updatedCustomer.getSalutation());
        customer.setTitle(updatedCustomer.getTitle());
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setBirthDate(updatedCustomer.getBirthDate());
        customer.setStreet(updatedCustomer.getStreet());
        customer.setHouseNumber(updatedCustomer.getHouseNumber());
        customer.setPostalCode(updatedCustomer.getPostalCode());
        customer.setCity(updatedCustomer.getCity());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setMobile(updatedCustomer.getMobile());
        customer.setFax(updatedCustomer.getFax());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setNewsletter(updatedCustomer.isNewsletter());
        // Save the updated customer to the database
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer id: " + id));
        // Delete the customer from the database
        customerRepository.delete(customer);
        return "redirect:/customers";
    }
}
