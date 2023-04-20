package com.akaxon.smsservice.api.factories;

import com.akaxon.smsservice.api.dto.ClientResponseDTO;
import com.akaxon.smsservice.api.store.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientDTOFactory {

    public ClientResponseDTO makeClientDTO(ClientEntity clientEntity) {
        return ClientResponseDTO.builder()
                .clientId(clientEntity.getClientId())
                .uniqueIdentifier(clientEntity.getUniqueIdentifier())
                .fullName(clientEntity.getFullName())
                .phoneNumber(clientEntity.getPhoneNumber())
                .reservedPhoneNumber(clientEntity.getReservedPhoneNumber())
                .build();
    }
}
