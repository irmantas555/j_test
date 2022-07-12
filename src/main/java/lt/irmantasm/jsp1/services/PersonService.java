package lt.irmantasm.jsp1.services;

import lombok.SneakyThrows;
import lt.irmantasm.jsp1.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {
    @PersistenceContext()
    private EntityManager em;

    public Person savePerson(Person person){
        em.persist(person);
        return person;
    }

    public List<Person> retrievePersons(){
        TypedQuery<Person> select_p_from_person_p = em.createQuery("select p from Person p", Person.class);
        return select_p_from_person_p.getResultList();
    }

    public Person retrievePersonById(Long id) {
        TypedQuery<Person> query = em.createQuery("select p from Person p where p.id = ?1", Person.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @SneakyThrows
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveImage(Long id,String name, byte[] image) {
        String[] split = name.split("/");
        String ext = split[split.length - 1];
        em.createQuery("update Person set image = ?1,  imageType = ?2 where id = ?3")
                .setParameter(1, image)
                .setParameter(2, ext != null ? ext : "")
                .setParameter(3,id)
                .executeUpdate();
    }
}
