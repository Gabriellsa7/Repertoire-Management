package com.academico.webproject.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    //The leader will indicate the next song to be played based on the order.
    @Column(name = "is_leader")
    private Boolean is_leader;

    @ManyToMany(mappedBy = "members")
    private Set<Band> bands;

}

