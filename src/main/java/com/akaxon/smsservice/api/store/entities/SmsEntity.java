package com.akaxon.smsservice.api.store.entities;

import com.akaxon.smsservice.api.domains.SmsState;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "sms")
public class SmsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sms_id")
    Long smsId;

    @NonNull
    String text;

    @NonNull
    @Column(name = "client_id")
    Long clientId;


    @NonNull
    String recieverPhoneNumber;

    @NonNull
    SmsState smsState;

    @NonNull
    @Builder.Default
    Instant sentAt = Instant.now();


}
