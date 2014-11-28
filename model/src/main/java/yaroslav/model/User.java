package yaroslav.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ynikolaiko on 11/24/14.
 */
@Entity
@Data @NoArgsConstructor
@EntityListeners(UserListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;

    public User(String name, Integer age){
        this.name = name;
        this.age = age;

    }

}
