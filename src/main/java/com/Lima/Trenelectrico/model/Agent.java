package com.Lima.Trenelectrico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String station;
}
