package org.example;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Util {

    public static void Sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sampleMonoCode1() {
        SampleMono sampleMono = new SampleMono("My name");
        System.out.println("without subscribe " + sampleMono.getMonoName());
        String name = sampleMono.getMonoName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        System.out.println(name);
        sampleMono.getMonoName();
    }

    public static void sampleMonoCode2() {
        Mono<Integer> mono = Mono.just(1).subscribeOn(Schedulers.parallel());
        mono.subscribe(
                item -> System.out.println("Received value " + item + " ,Thread ID : " + Thread.currentThread().getName()),
                error -> System.out.println("Error : " + error),
                () -> System.out.println("Done Thread : " + Thread.currentThread().getName())
        );
    }
}
