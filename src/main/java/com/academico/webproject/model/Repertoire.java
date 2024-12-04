package com.academico.webproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repertoire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repertoire {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

}
