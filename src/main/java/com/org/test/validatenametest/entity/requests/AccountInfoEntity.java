package com.org.test.validatenametest.entity.requests;

import com.org.test.validatenametest.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "account_info")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "creditBureau")
@Builder
@Setter
@Getter
public class AccountInfoEntity {

    @Id
    private BigInteger accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum accountStatus;

    @Column(nullable = false)
    private Double currentBalance;

    @Column(nullable = false)
    private LocalDate dateOpened;

    @Column(nullable = false)
    private Integer daysInArrears;

    @Column(nullable = false)
    private String delinquencyCode;

    @Column(nullable = false)
    private Integer highestDaysInArrears;

    @Column(nullable = false)
    private Boolean isYourAccount;

    @Column(nullable = false)
    private Double lastPaymentAmount;

    @Column
    private LocalDate lastPaymentDate;

    @Column(nullable = false)
    private LocalDate loadedAt;

    @Column(nullable = false)
    private Double originalAmount;

    @Column(nullable = false)
    private Double overdueBalance;

    @Column
    private LocalDate overdueDate;

    @Column(nullable = false)
    private Integer productTypeId;

    @ManyToOne
    @JoinColumn(name = "credit_bureau_id", nullable = false)
    private CreditBureauEntity creditBureau;


}
