import org.springframework.http.codec.multipart.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestController
public class YourController {

    private final MultipartHttpMessageReader multipartReader;

    public YourController(MultipartHttpMessageReader multipartReader) {
        this.multipartReader = multipartReader;
    }

    @PostMapping("/yourEndpoint")
    public Mono<String> handleRequest(ServerHttpRequest request) {
        return request
                .getBody()
                .flatMap(body -> {
                    // Read multipart content using MultipartHttpMessageReader
                    return multipartReader
                            .readMultiPart(body, request.getHeaders().getContentType())
                            .flatMap(part -> {
                                // Process each part of the multipart request
                                // Here, you can access part headers, content, etc.
                                return part.content()
                                        .map(dataBuffer -> {
                                            // Process the dataBuffer, for example, convert it to a string
                                            return dataBuffer.toString(StandardCharsets.UTF_8);
                                        });
                            });
                })
                .collectList() // Collect all processed parts into a list
                .map(parts -> {
                    // Handle the list of processed parts as needed
                    // For demonstration, just concatenate them into a single string
                    return "Processed parts: " + parts.toString();
                });
    }
}
