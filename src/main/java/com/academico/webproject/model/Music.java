package com.academico.webproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "music")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pdf_file")
    private String pdf_file;
    
}
