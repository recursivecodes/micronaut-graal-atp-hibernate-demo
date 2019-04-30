package codes.recursive.service;

import codes.recursive.model.Person;
import codes.recursive.util.SortingAndOrderArguments;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
public class PersonRepositoryImpl implements PersonRepository {

    @PersistenceContext
    public final EntityManager entityManager;

    public PersonRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "first_name", "last_name", "is_cool");

    @Transactional(readOnly = true)
    public List<Person> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT p FROM Person as p";

        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY p." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }

        TypedQuery<Person> query = entityManager.createQuery(qlString, Person.class);
        query.setMaxResults(args.getMax().orElse(10));
        args.getOffset().ifPresent(query::setFirstResult);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    @Transactional
    public Person save(@NotBlank String firstName, @NotBlank String lastName, @NotBlank Boolean isCool) {
        Person Person = new Person(firstName, lastName, isCool);
        entityManager.persist(Person);
        return Person;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(Person -> entityManager.remove(Person));
    }

    @Override
    @Transactional
    public int update(@NotNull Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank Boolean isCool) {
        return entityManager.createQuery("UPDATE Person g SET first_name = :firstName, last_name = :lastName, is_cool = :isCool where id = :id")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("isCool", isCool)
                .setParameter("id", id)
                .executeUpdate();
    }
}