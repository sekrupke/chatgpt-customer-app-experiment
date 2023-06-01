package de.chatgpt.sample.customerapp;

import de.chatgpt.sample.customerapp.controller.CustomerController;
import de.chatgpt.sample.customerapp.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        //when(customerController.createCustomer()).thenReturn(customers);
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer-list"))
                .andExpect(model().attribute("customers", customers));
    }
    // Add more test methods for other controller actions
}
