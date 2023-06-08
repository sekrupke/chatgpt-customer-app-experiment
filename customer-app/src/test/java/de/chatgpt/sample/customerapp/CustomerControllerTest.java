package de.chatgpt.sample.customerapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import de.chatgpt.sample.customerapp.controller.CustomerController;
import de.chatgpt.sample.customerapp.models.Customer;
import de.chatgpt.sample.customerapp.repositories.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class CustomerControllerTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCustomerList() {
        // Create a list of customers for the mock
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        // Mock the behavior of customerRepository.findAll() to return the list of customers
        doReturn(customers).when(customerRepository).findAll();

        // Call the method under test
        customerController.customerList(model);

        // Verify that the model contains the expected attribute
        verify(model).addAttribute(eq("customers"), any());
    }

    @Test
    void testShowCreateForm() {
        // Call the method under test
        customerController.showCreateForm(model);

        // Verify that the model contains the expected attribute
        verify(model).addAttribute(eq("customer"), any(Customer.class));
    }

    @Test
    void testCreateCustomer() {
        // Create a new customer
        Customer customer = new Customer();

        // Mock the behavior of customerRepository.save() to return the customer itself
        doReturn(customer).when(customerRepository).save(any(Customer.class));

        // Call the method under test
        String redirectUrl = customerController.createCustomer(customer, bindingResult);

        // Verify that the customer is saved to the repository
        verify(customerRepository).save(customer);

        // Verify that the redirect URL is correct
        assertEquals("redirect:/customers", redirectUrl);
    }

    @Test
    void testShowEditForm() {
        // Create a customer with an ID
        Customer customer = new Customer();
        customer.setId(1L);

        // Mock the behavior of customerRepository.findById() to return the customer
        doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        // Call the method under test
        customerController.showEditForm(1L, model);

        // Verify that the model contains the expected attribute
        verify(model).addAttribute(eq("customer"), eq(customer));
    }

    @Test
    void testUpdateCustomer() {
        // Create a customer with an ID
        Customer customer = new Customer();
        customer.setId(1L);

        // Mock the behavior of customerRepository.findById() to return the customer
        doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        // Mock the behavior of customerRepository.save() to return the customer itself
        doReturn(customer).when(customerRepository).save(any(Customer.class));

        // Call the method under test
        String redirectUrl = customerController.updateCustomer(1L, customer);

        // Verify that the existing customer is updated in the repository
        verify(customerRepository).save(customer);

        // Verify that the redirect URL is correct
        assertEquals("redirect:/customers", redirectUrl);
    }

    @Test
    void testDeleteCustomer() {
        // Create a customer with an ID
        Customer customer = new Customer();
        customer.setId(1L);

        // Mock the behavior of customerRepository.findById() to return the customer
        doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        // Call the method under test
        String redirectUrl = customerController.deleteCustomer(1L);

        // Verify that the customer is deleted from the repository
        verify(customerRepository).delete(customer);

        // Verify that the redirect URL is correct
        assertEquals("redirect:/customers", redirectUrl);
    }
}
