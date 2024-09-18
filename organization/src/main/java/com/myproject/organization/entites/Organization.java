package com.myproject.organization.entites;

import com.myproject.organization.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organization")
@Data
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Location cannot be null")
    @NotEmpty(message = "Location cannot be empty")
    private String city;

    @NotNull(message = "Location cannot be null")
    @NotEmpty(message = "Location cannot be empty")
    private String district;

    @org.antlr.v4.runtime.misc.NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull(message = "Phone cannot be null")
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be null")
    private Category category;
}