package com.example.priority.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToMany(mappedBy = "users")
    private Set<Expense> expenses;
    @OneToOne(mappedBy = "whoGaveMoney")
    private Expense expense;

}
