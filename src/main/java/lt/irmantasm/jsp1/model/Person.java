package lt.irmantasm.jsp1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static lt.irmantasm.jsp1.model.Person.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person", schema = "test")
@NamedQuery(name = COUNT_ALL, query = "select count(p.id) from Person p")
@NamedQuery(name = FIND_PAGE,query = "select p from Person p order by id desc")
@NamedQuery(name = GET_IMAGE,query = "select p.image from Person p")
@NamedQuery(name = GET_BY_ID,query = "select p from Person p")
public class Person {
    public static final String FIND_PAGE = "Person.findPage";
    public static final String COUNT_ALL = "Person.count";
    public static final String DELETE_BY_ID = "Person.delete";
    public static final String GET_BY_ID = "Person.findByID";
    public static final String GET_IMAGE = "Person.image";
    @Id
    @SequenceGenerator(name="person_id_seq", schema = "test", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "person_id_seq")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Long age;
    @Column(name = "image")
    private byte image[];
    @Column(name = "image_type")
    private String imageType;

    public Person(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public Person(Long id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id != null ? id.equals(person.id) : person.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
