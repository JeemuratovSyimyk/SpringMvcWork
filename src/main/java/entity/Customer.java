package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.processing.Pattern;

import java.util.List;

@Entity
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String fullName;

    @NotNull
 //   @Pattern(regexp = "^\\+996\\d{9}$")
    private String phoneNumber;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;
}
