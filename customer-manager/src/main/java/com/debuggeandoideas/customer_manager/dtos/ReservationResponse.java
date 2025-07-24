package com.debuggeandoideas.customer_manager.dtos;

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
