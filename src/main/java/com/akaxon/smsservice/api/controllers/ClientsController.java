package com.akaxon.smsservice.api.controllers;

import com.akaxon.smsservice.api.dto.AckDto;
import com.akaxon.smsservice.api.dto.ClientCreateRequestDTO;
import com.akaxon.smsservice.api.dto.ClientResponseDTO;
import com.akaxon.smsservice.api.dto.ClientUpdateRequestDTO;
import com.akaxon.smsservice.api.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/clients")
@Transactional
public class ClientsController {

    @Autowired
    ClientService clientService;


    @GetMapping(path = "/{clientId}")
    public ClientResponseDTO getSingleClient(@PathVariable("clientId") Long id){
        return clientService
                .getClient(id);
    }
    @GetMapping
    public List<ClientResponseDTO> fetchClients(@RequestParam(value = "preifxName", required = false)
                                                    Optional<String> optionalPrefixName) {
        return clientService
                .fetchAllClients(optionalPrefixName);
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ClientResponseDTO createClient(@RequestBody @Valid ClientCreateRequestDTO clientRequest)
    {
        return clientService
                .createClient(clientRequest);

    }

    @PatchMapping(path = "/{clientId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ClientResponseDTO editClient(@PathVariable("clientId") Long id,
                                        @RequestBody @Valid ClientUpdateRequestDTO clientUpdateRequestDTO){
        return clientService
                .updateClient(id, clientUpdateRequestDTO);
    }

    @DeleteMapping(path = "/{clientId}")
    public AckDto deleteClient(@PathVariable("clientId") Long id){
        return clientService
                .deleteClient(id);
    }


}
