package com.academico.webproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "band_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
}
