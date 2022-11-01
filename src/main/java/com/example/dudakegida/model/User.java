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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @NotNull
    private double balance;

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

    @ManyToMany(mappedBy = "userChose")
    private Set<Animal> animalUserWant;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (isActive() != user.isActive()) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getLastName().equals(user.getLastName())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getAddress().equals(user.getAddress())) return false;
        if (!getGender().equals(user.getGender())) return false;
        if (!getPhoneNumber().equals(user.getPhoneNumber())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getDayBirth().equals(user.getDayBirth())) return false;
        return  (getRole().equals(user.getRole()));
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getDayBirth().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }
}
