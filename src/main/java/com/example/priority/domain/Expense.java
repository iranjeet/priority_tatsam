package com.example.priority.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "expense")
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "who_gave_money", referencedColumnName = "id")
    private User whoGaveMoney;

    private Double totalAmount;

    @ManyToMany
    @JoinTable(name = "assn_user_expense", joinColumns = @JoinColumn(name = "expense_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;


}
