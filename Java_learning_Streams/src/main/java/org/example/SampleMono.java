package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SampleMono {
    private String name;
    public Mono<String> getMonoName() {
        System.out.println("Entered getMonoName");
        return Mono.fromSupplier(() -> {
            System.out.println("generating name....");
            Util.Sleep(3);
            return getName();

        }).map(String::toUpperCase);

    }

}
