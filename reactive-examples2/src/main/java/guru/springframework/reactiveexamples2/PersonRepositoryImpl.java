package guru.springframework.reactiveexamples2;

import guru.springframework.reactiveexamples2.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {
    Person michael = new Person(1,"Michael","Weston");
    Person fiona = new Person(2,"Fiona","Gleanne");
    Person sam= new Person(3,"Sam","Axe");
    Person jesse =  new Person(4,"Jesse","Porter");

    @Override
    public Mono<Person> getById(Integer id) {

        return Mono.just(michael);
    }

    @Override
    public Flux<Person> findAll() {

        return Flux.just(michael,fiona,sam,jesse);
    }
}
