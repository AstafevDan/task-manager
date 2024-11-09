package com.dan.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(of = "username", callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;
}
