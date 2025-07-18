package com.debuggeandoideas.eats_hub_catalog.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    @NotNull(message = "Restaurant ID is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                       message = "Restaurant ID must be a valid UUID")
    private String restaurantId;  //Not null is UUID format

    @NotNull(message = "Customer ID is required")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                       message = "Customer ID must be a valid UUID")
    private String customerId;  //Not null is UUID format

    @NotNull(message = "Customer name is required")
    @Size(min = 3,
                max = 21,
                message = "Customer name must be between 3 and 21 characters")
    private String customerName; //Not null length min 3 max 21

    @NotNull(message = "Customer email is required")
    @Email(message = "Customer email must be a valid email address")
    private String customerEmail; //Not null mail format

    @NotNull(message = "Date and time is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2},\\d{2}:\\d{2}$",
                       message = "DateTime must be in format YYYY-MM-DD,HH:mm (example: 2025-06-16,15:30)")
    private String dateTime; // valid format: example 2025-06-16,15:30

    @NotNull(message = "Party size is required")
    @Min(value = 1, message = "Party size must be at least 1")
    @Max(value = 20, message = "Party size cannot exceed 20")
    private Integer partySize; //not null

    private String comment;
}
