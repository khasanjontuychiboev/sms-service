package com.akaxon.smsservice.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientCreateRequestDTO {
    String uniqueIdentifier;

    @NotNull(message = "full-name cannot be blank")
    @NotBlank(message = "full-name field cannot be blank")
    @Size(min = 2, message = "full-name length must be minimum 2")
    String fullName;

    @NotNull(message = "phone-number cannot be blank")
    @NotBlank(message = "phone-number field cannot be blank")
    @Size(min = 3, max = 5, message = "phone-number length must be greater than 3, less than 5")
    String phoneNumber;

    String reservedPhoneNumber;

}
