package com.debuggeandoideas.eats_hub_catalog.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private String restaurantId;  //Not null is UUID format
    private String customerId;  //Not null is UUID format
    private String customerName; //Not null length min 3 max 21
    private String customerEmail; //Not null mail format
    private String dateTime; // valid format: example 2025-06-16,15:30
    private Integer partySize; //not null
    private String comment;
}
