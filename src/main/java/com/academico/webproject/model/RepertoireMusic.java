package com.academico.webproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repertoire_music")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepertoireMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_in_repertoire")
    private int orderInRepertoire;

    //If a song is removed from the repertoire
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "repertoire_id")
    private Repertoire repertoire;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}
