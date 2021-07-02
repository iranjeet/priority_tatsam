package com.example.priority.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "updated_date")
    private Long updatedDate;

    private Boolean isActive = false;

//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    private List<AssnPriorityUser> assnPriorityUsers;
}
