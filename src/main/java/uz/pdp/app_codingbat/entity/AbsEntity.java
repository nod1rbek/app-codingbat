package uz.pdp.app_codingbat.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @OneToMany
    private Set<Task> task;
}
