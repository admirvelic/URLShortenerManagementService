package com.vella.URLShortenerManagmenetService.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Hashtable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hashtebleUrl")
@Getter
@Setter
public class HashtebleUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Hashtable<Long, String> hashtable;

}
