package com.debuggeandoideas.customer_manager.controllers;

import com.debuggeandoideas.customer_manager.clients.ReservationCrudClient;
import com.debuggeandoideas.customer_manager.dtos.ReservationRequest;
import com.debuggeandoideas.customer_manager.dtos.ReservationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "reservation")
@Tag(name = "Reservations", description = "API to management reservations")
public class ReservationController {

    private final ReservationCrudClient reservationCrudClient;

    @Operation(summary = "Create a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Mono<ResponseEntity<Object>> postReservation(
              @Valid @RequestBody ReservationRequest reservationRequest) {
        log.info("POST customer/reservation");

        return this.reservationCrudClient.create(reservationRequest)
                .map(resource -> ResponseEntity.created(URI.create(resource)).build())
                .onErrorResume(IllegalArgumentException.class, error -> {
                    log.error("POST customer/reservation failed", error);
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .onErrorResume(RuntimeException.class, error -> {
                    log.error("POST customer/reservation failed", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    @Operation(summary = "Get reservation by ID",
            description = "Retrieves the details of a specific reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found",
                    content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "{id}")
    public Mono<ResponseEntity<ReservationResponse>> getReservation(@PathVariable String id) {
        log.info("GET customer/reservation/{}", id);

        return this.reservationCrudClient.read(id)
                .map(ResponseEntity::ok)
                .onErrorResume(IllegalArgumentException.class, error -> {
                    log.error("GET customer/reservation failed", error);
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .onErrorResume(RuntimeException.class, error -> {
                    log.error("GET customer/reservation failed", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successfully updated",
                    content = @Content(schema = @Schema(implementation = ReservationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(path = "{id}")
    public Mono<ResponseEntity<ReservationResponse>> putReservation(
            @Valid @PathVariable String id, @RequestBody ReservationRequest reservationRequest) {
        log.info("PUT customer/reservation/{}", id);

        return this.reservationCrudClient.update(id, reservationRequest)
                .map(ResponseEntity::ok)
                .onErrorResume(IllegalArgumentException.class, error -> {
                    log.error("PUT customer/reservation failed", error);
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .onErrorResume(RuntimeException.class, error -> {
                    log.error("PUT customer/reservation failed", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }

    @Operation(summary = "Delete reservation",
            description = "Deletes a reservation from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reservation successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "{id}")
    public Mono<ResponseEntity<Object>> deleteReservation(@PathVariable String id) {
        log.info("DELETE customer/reservation/{}", id);

        return this.reservationCrudClient.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(IllegalArgumentException.class, error -> {
                    log.error("DELETE customer/reservation failed", error);
                    return Mono.just(ResponseEntity.badRequest().build());
                })
                .onErrorResume(RuntimeException.class, error -> {
                    log.error("DELETE customer/reservation failed", error);
                    return Mono.just(ResponseEntity.internalServerError().build());
                });
    }
}
