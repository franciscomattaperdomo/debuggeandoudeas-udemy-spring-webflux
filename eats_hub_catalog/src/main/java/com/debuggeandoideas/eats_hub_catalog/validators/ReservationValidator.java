package com.debuggeandoideas.eats_hub_catalog.validators;

import com.debuggeandoideas.eats_hub_catalog.clientes.PlannerMSClient;
import com.debuggeandoideas.eats_hub_catalog.collections.ReservationCollection;
import com.debuggeandoideas.eats_hub_catalog.collections.RestaurantCollection;
import com.debuggeandoideas.eats_hub_catalog.exceptions.BusinessException;
import com.debuggeandoideas.eats_hub_catalog.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationValidator {

    private final RestaurantRepository restaurantRepository;
    private final PlannerMSClient plannerMSClient;


    public <T> Mono<Void> applyValidations(T input, List<BusinessValidator<T>> validations) {

        if (validations.isEmpty()) {
            return Mono.empty();
        }
        return validations.stream()
                .reduce(
                        Mono.empty(),
                        (chain, validator) -> chain.then(validator.validate(input)),
                        Mono::then
                );
    }

    public BusinessValidator<ReservationCollection> validateRestaurantNotClosed() {
        log.info("Validating restaurant not closed");

        return reservation -> {
            final var restaurantId = UUID.fromString(reservation.getRestaurantId());

            return this.restaurantRepository.findById(restaurantId)
                    .switchIfEmpty(Mono.error(new BusinessException("Restaurant not found")))
                    .flatMap(restaurant -> {
                        if (this.isRestaurantClosed(restaurant, reservation.getTime())) {
                            return Mono.error(new BusinessException("Restaurant already closed"));
                        }

                        return Mono.empty();
                    });
        };
    }

    public BusinessValidator<ReservationCollection> validateAvailability() {
        log.info("Validating availability");

        return reservation -> {
            final var restaurantId = UUID.fromString(reservation.getRestaurantId());

            return this.plannerMSClient.verifyAvailability(reservation.getDate(), reservation.getTime(), restaurantId)
                    .flatMap(isAvailable -> {
                        if (!isAvailable) {
                            return Mono.error(new BusinessException("Restaurant is not available"));
                        }
                        return Mono.empty();
                    });
        };
    }

    private boolean isRestaurantClosed(RestaurantCollection restaurant, String reservationTime) {
        try {

            if (Objects.isNull(restaurant.getCloseAt()) || Objects.isNull(reservationTime)) {
                return true;
            }

            LocalTime closeLocalTime = LocalTime.parse(restaurant.getCloseAt(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime reservationLocalTime = LocalTime.parse(reservationTime, DateTimeFormatter.ofPattern("HH:mm"));

            return reservationLocalTime.isAfter(closeLocalTime);

        } catch (Exception e) {
            log.error("Error on verify close tome", e);
            return true;
        }
    }

}
