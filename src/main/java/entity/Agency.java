package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
@Entity
@Setter
@Getter
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "agency")
    private List<House> houses;

    @OneToMany(mappedBy = "agency")
    private List<Customer> customers;
}
