package com.shubham.feedback;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> role;
    private String password;

}
