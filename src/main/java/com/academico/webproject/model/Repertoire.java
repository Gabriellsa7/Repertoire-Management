package com.academico.webproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "repertoire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repertoire {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @OneToMany(mappedBy = "repertoire", cascade = CascadeType.ALL)
    private Set<RepertoireMusic> musicLinks;

}
