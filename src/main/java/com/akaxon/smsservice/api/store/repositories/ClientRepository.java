package com.akaxon.smsservice.api.store.repositories;

import com.akaxon.smsservice.api.store.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByFullName(String fullName);

    Stream<ClientEntity> streamAllBy();

    Stream<ClientEntity> streamAllByFullNameStartsWithIgnoreCase(String prefixName);
}
