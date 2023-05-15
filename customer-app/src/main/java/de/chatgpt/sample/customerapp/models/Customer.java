package de.chatgpt.sample.customerapp.models;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private int id;
    
    private String salutation;
    private String title;
    private String firstName;
    private String lastName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String phone;
    private String mobile;
    private String fax;
    private String email;
    private boolean newsletter;
    
    // Constructors, getters, and setters omitted for brevity
}
