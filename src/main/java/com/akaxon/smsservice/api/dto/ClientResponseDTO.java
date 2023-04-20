package com.akaxon.smsservice.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientResponseDTO {

    @NonNull
    Long clientId;

    String uniqueIdentifier;

    @NonNull
    String fullName;

    @NonNull
    String phoneNumber;

    String reservedPhoneNumber;

}
