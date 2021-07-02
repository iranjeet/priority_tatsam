package com.example.priority.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data@Entity
@Table(name = "assn_priority_user")
public class AssnPriorityUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive=false;

    private Long orders;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;
}
