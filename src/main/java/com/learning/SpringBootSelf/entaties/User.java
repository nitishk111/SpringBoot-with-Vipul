package com.learning.SpringBootSelf.entaties;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_details")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userID;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<JournalEntry> entries;
}
