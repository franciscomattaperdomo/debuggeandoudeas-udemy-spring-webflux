package com.debuggeandoideas.customer_manager.util;

import com.debuggeandoideas.customer_manager.dtos.RatingRequest;
import com.debuggeandoideas.customer_manager.exceptions.RetryableStreamException;
import com.debuggeandoideas.customer_manager.exceptions.StreamTerminateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.util.retry.Retry;
import org.springframework.kafka.support.KafkaHeaders;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class StreamService2 {

    /*private final Sinks.Many<Message<String>> ratingRequestSink;
    private final ObjectMapper objectMapper;

    public Mono<Object> sendRatingToStream(RatingRequest ratingRequest) {

        return Mono.fromCallable(() -> objectMapper.writeValueAsString(ratingRequest))
                .flatMap(json -> {

                    Message<String> msg = MessageBuilder.withPayload(json)
                            .setHeader(KafkaHeaders.KEY,
                                    ratingRequest.getIdRestaurant().toString().getBytes())
                            .setHeader("contentType", "text/plain")
                            .build();

                    Sinks.EmitResult result = ratingRequestSink.tryEmitNext(msg);


                    if (result.isSuccess()) {
                        log.info("Emitted to sink");
                        return Mono.empty();
                    }
                    return Mono.error(mapEmitResult(result));
                })
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                        .filter(ex -> ex instanceof RetryableStreamException)
                        .onRetryExhaustedThrow((spec, sig) -> sig.failure()))
                .doOnError(err -> log.error("Error sending to stream", err));
    }

    private RuntimeException mapEmitResult(Sinks.EmitResult res) {
        return switch (res) {
            case FAIL_OVERFLOW, FAIL_NON_SERIALIZED ->
                    new RetryableStreamException("Transient sink failure");
            case FAIL_CANCELLED, FAIL_TERMINATED ->
                    new StreamTerminateException("Sink cancelled or terminated");
            default -> new RuntimeException("Unexpected emit result: " + res);
        };
    }*/
}
