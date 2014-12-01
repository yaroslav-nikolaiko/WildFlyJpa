package yaroslav.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by ynikolaiko on 11/24/14.
 */
@Entity
@Data @NoArgsConstructor
@EntityListeners(UserListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    List<Address> addresses;


    public User(String name, Integer age){
        this.name = name;
        this.age = age;

    }

}
