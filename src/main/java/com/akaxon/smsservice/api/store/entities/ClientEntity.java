package com.akaxon.smsservice.api.store.entities;

import com.akaxon.smsservice.api.domains.SmsState;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "client_id")
    Long clientId;

    String uniqueIdentifier;

    @NonNull
    String fullName;

    @NonNull
    String phoneNumber;

    String reservedPhoneNumber;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    List<SmsEntity> smses =new ArrayList<>();


}
