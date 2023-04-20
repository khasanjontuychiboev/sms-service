package com.akaxon.smsservice.api.service;

import com.akaxon.smsservice.api.dto.AckDto;
import com.akaxon.smsservice.api.dto.ClientCreateRequestDTO;
import com.akaxon.smsservice.api.dto.ClientResponseDTO;
import com.akaxon.smsservice.api.dto.ClientUpdateRequestDTO;
import com.akaxon.smsservice.api.exceptions.BadRequestException;
import com.akaxon.smsservice.api.exceptions.NotFoundException;
import com.akaxon.smsservice.api.factories.ClientDTOFactory;
import com.akaxon.smsservice.api.store.entities.ClientEntity;
import com.akaxon.smsservice.api.store.repositories.ClientRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientDTOFactory clientDTOFactory;

    public ClientResponseDTO getClient(Long id) {

        ClientEntity client = clientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Client with \"%s\" doesn't exist.", id))
                );

        return clientDTOFactory
                .makeClientDTO(client);
    }

    public List<ClientResponseDTO> fetchAllClients(Optional<String> optionalPrefixName) {

        optionalPrefixName = optionalPrefixName
                .filter(
                        prefixName -> !prefixName
                                .trim()
                                .isEmpty()
                );

        Stream<ClientEntity> clietnStream = optionalPrefixName
                .map(clientRepository::streamAllByFullNameStartsWithIgnoreCase)
                .orElseGet(clientRepository::streamAllBy);

        return clietnStream
                .map(clientDTOFactory::makeClientDTO)
                .collect(Collectors.toList());

    }

    public ClientResponseDTO createClient(ClientCreateRequestDTO clientCreateRequestDTO) {

        ClientEntity client = clientRepository.saveAndFlush(
                ClientEntity.builder()
                        .fullName(clientCreateRequestDTO.getFullName())
                        .phoneNumber(clientCreateRequestDTO.getPhoneNumber())
                        .reservedPhoneNumber(clientCreateRequestDTO.getReservedPhoneNumber())
                        .build()
        );
        return clientDTOFactory.makeClientDTO(client);
    }

    public ClientResponseDTO updateClient(Long id, ClientUpdateRequestDTO clientUpdateRequestDTO) {

        ClientEntity clientEntity = clientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Client with \"%s\" doesn't exist.", id))
                );

        clientRepository
                .findByFullName(clientUpdateRequestDTO.getFullName())
                .filter(anotherClient -> !Objects.equals(anotherClient.getClientId(), id))
                .ifPresent( anotherClient -> {
                    throw  new BadRequestException(String.format("Client \"%s\" already exists.", clientUpdateRequestDTO.getFullName()));
                });

        clientEntity.setFullName(clientUpdateRequestDTO.getFullName());
        clientEntity.setPhoneNumber(clientUpdateRequestDTO.getPhoneNumber());
        clientEntity.setReservedPhoneNumber(clientUpdateRequestDTO.getReservedPhoneNumber());

        return clientDTOFactory.makeClientDTO(clientEntity);
    }

    public AckDto deleteClient(Long id) {

        clientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Client with id = \"%s\" doesn't exist.", id))
                );

        clientRepository
                .deleteById(id);
        return AckDto.makeDefault(true);
    }

}
