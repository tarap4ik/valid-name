package com.org.test.validatenametest.entity.requests;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "verified_person")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Setter
@Getter
public class VerifiedPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verifiedId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String otherName;

    @Column(nullable = false)
    private String surname;

    @OneToOne(mappedBy = "verifiedPerson")
    private CreditBureauEntity creditBureau;

}
