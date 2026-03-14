package com.arcuriaj.savestack.entities;


import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID id;

    @Column(name = "user_name")
    private String userName;

}
