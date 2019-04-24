package codes.recursive.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="person_hibernate")
public class Person {
    public Person() {}

    public Person(String firstName, String lastName, Boolean isCool) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isCool = isCool;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false, length = 50)
    @Size(min=5, max=50)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = 50)
    @Size(min=5, max=50)
    private String lastName;

    @NotNull
    @Column(name = "is_cool", nullable = false, length = 50, columnDefinition = "NUMBER(1,0), default 1")
    private Boolean isCool;

    public Boolean getIsCool() {
        return isCool;
    }

    public void setIsCool(Boolean isCool) {
        this.isCool = isCool;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}