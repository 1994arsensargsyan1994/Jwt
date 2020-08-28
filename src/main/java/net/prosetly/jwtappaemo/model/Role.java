package net.prosetly.jwtappaemo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return super.toString() + "Role{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
