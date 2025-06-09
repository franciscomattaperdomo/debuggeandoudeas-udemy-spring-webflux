package com.debuggeandoideas.eats_hub_catalog.validators;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface BusinessValidator<T> {

    Mono<Void> validate(T input);
}
