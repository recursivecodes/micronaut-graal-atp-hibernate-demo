package codes.recursive.service;


import codes.recursive.model.Person;
import codes.recursive.util.SortingAndOrderArguments;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> findAll(@NotNull SortingAndOrderArguments args);
    Optional<Person> findById(@NotNull Long id);
    Person save(@NotBlank String firstName, @NotBlank String lastName, @NotBlank Boolean isCool);
    int update(@NotNull Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank Boolean isCool);
    void deleteById(@NotNull Long id);
}