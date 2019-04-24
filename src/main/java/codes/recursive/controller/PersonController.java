package codes.recursive.controller;

import codes.recursive.model.Person;
import codes.recursive.service.PersonRepository;
import codes.recursive.util.SortingAndOrderArguments;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Validated
@Controller("/person")
public class PersonController {
    protected final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Get("/{id}")
    public Optional<Person> getPerson(Long id) {
        return personRepository.findById(id);
    }

    @Get("/list{?arguments*}")
    List<Person> getPersons(@Valid SortingAndOrderArguments arguments) {
        return personRepository.findAll(arguments);
    }

    @Post("/")
    HttpResponse<Person> savePerson(@Body @Valid Person person) {
        Person savedPerson = personRepository.save(person.getFirstName(), person.getLastName(), person.getIsCool());
        return HttpResponse.created(savedPerson);
    }

    @Put("/")
    HttpResponse updatePerson(@Body @Valid Person person) {
        personRepository.update(person.getId(), person.getFirstName(), person.getLastName(), person.getIsCool());
        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(person.getId()).getPath());
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        personRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/person/" + id);
    }

    protected URI location(Person person) {
        return location(person.getId());
    }
}