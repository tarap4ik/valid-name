package com.org.test.validatenametest.entity.requests;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RequestEntity {

    @Id
    private UUID requestId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_bureau_id", nullable = false)
    private CreditBureauEntity creditBureau;

}
