package com.shubham.feedback;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String feedback;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;
}
