package com.org.test.validatenametest.entity.requests;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "credit_bureau")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CreditBureauEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long creditBureauId;

    @OneToMany(fetch = FetchType.LAZY, cascade = PERSIST, mappedBy = "creditBureau")
    private Set<RequestEntity> requests;

    @OneToMany(fetch = FetchType.LAZY, cascade = PERSIST , mappedBy = "creditBureau")
    private Set<AccountInfoEntity> accountInfos;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "verified_id")
    private VerifiedPersonEntity verifiedPerson;

}
