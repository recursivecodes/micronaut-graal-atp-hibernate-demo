package codes.recursive.controller;

import codes.recursive.model.Person;
import codes.recursive.service.PersonRepository;
import codes.recursive.util.SortingAndOrderArguments;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Validated
@Controller("/person")
@Tag(name = "Person")
public class PersonController {

    protected final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * @return A 200 OK result
     */
    @ApiResponse(responseCode = "200", description = "No body.")
    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    /**
     * Get a person by id
     * @param id The person's id
     * @return A person
     */
    @Operation(summary = "Get a person by id")
    @ApiResponse(responseCode = "200", description = "A person.")
    @ApiResponse(responseCode = "404", description = "No person found matching the ID given.")
    @Get("/{id}")
    public Optional<Person> getPerson(Long id) {
        return personRepository.findById(id);
    }

    /**
     * List persons
     * @param max The max number of persons to return
     * @param offset The offset from which to start retrieving persons
     * @return A list of persons
     */
    @Operation(summary = "List persons")
    @ApiResponse(responseCode = "200", description = "A list of persons.")
    @Get("/list{?arguments*}")
    List<Person> getPersons(@Nullable @Valid SortingAndOrderArguments arguments) {
        if( arguments == null ) {
            arguments = new SortingAndOrderArguments();
        }
        return personRepository.findAll(arguments);
    }

    /**
     * Save a new person
     * @param person The person object to save
     * @return The new person.
     */
    @Operation(summary = "Save a new person")
    @ApiResponse(responseCode = "201", description = "The new person, save successful.")
    @Post("/")
    HttpResponse<Person> savePerson(@Body @Valid Person person) {
        Person savedPerson = personRepository.save(person.getFirstName(), person.getLastName(), person.getIsCool());
        return HttpResponse.created(savedPerson);
    }

    /**
     * Update an existing person
     * @param person The person object to update
     * @return No content.  Returns the path to the person in the location header.
     */
    @Operation(summary = "Update an existing person")
    @ApiResponse(responseCode = "204", description = "No content, update successful. Returns the path to the person in the location header.")
    @Put("/")
    HttpResponse updatePerson(@Body @Valid Person person) {
        personRepository.update(person.getId(), person.getFirstName(), person.getLastName(), person.getIsCool());
        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(person.getId()).getPath());
    }

    /**
     * Eliminate a person by id
     * @param id The id of the person to delete
     * @return No content, delete successful.
     */
    @Operation(summary = "Eliminate a person by id")
    @ApiResponse(responseCode = "204", description = "No content, delete successful.")
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