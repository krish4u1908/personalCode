package guru.springframework.reactiveexamples2;

import guru.springframework.reactiveexamples2.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;
    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();

    }

    @Test
    void getById() {
        Mono<Person> personMono = personRepository.getById(1);

        Person person = personMono.block();

        System.out.println(person.toString());
    }
    @Test
    void getByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }
    @Test
    void getByIdMapFunction() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.map(person -> {
            System.out.println(person.toString());
            return person;
        }).subscribe(firstName -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("from map "+firstName);
        });
        System.out.println("return from getByIdMapFunction ");
    }
}