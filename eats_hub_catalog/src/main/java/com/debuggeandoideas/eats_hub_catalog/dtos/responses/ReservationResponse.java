package com.debuggeandoideas.eats_hub_catalog.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private String restaurantId;
    private String customerName;
    private String dateTime;
    private Integer partySize;
}
