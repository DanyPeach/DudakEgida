package com.example.dudakegida.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private boolean isActive;

    @NotNull
    private String address;

    @NotNull
    private String gender;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayBirth;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    private Set<Role> role;

    private String picture;

//    @ElementCollection(targetClass = Animal.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_pets", joinColumns = @JoinColumn(name = "pets_id"))
//    @NotNull
//    @Column(name = "pets")
//    private Set<Animal> pets;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Animal> animal;

    public User(long id, String firstName, String lastName, String login, String password, boolean isActive,  String address,
                String gender, String phoneNumber, String email, LocalDate dayBirth, Set<Role> role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isActive = isActive;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dayBirth = dayBirth;
        this.role = role;

    }

}
