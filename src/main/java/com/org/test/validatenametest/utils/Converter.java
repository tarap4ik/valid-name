package com.org.test.validatenametest.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.org.test.validatenametest.entity.requests.*;
import com.org.test.validatenametest.enums.StatusEnum;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class Converter {

    public CreditBureauEntity convert(JsonNode jsonNode) {

        CreditBureauEntity creditBureauEntity = new CreditBureauEntity();
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setRequestId(UUID.fromString(jsonNode.get("loanRequestID").asText()));

        var verified = jsonNode.get("creditBureau").get("verified_name");
        VerifiedPersonEntity verifiedPersonEntity = new VerifiedPersonEntity();
        verifiedPersonEntity.setFirstName(verified.get("first_name").asText());
        verifiedPersonEntity.setSurname(verified.get("surname").asText());
        if (verified.get("other_name") != null) {
            verifiedPersonEntity.setOtherName(verified.get("other_name").asText());
        }
        verifiedPersonEntity.setCreditBureau(creditBureauEntity);

        var account = jsonNode.get("creditBureau").get("account_info");
        Set<AccountInfoEntity> accountInfoEntities = new HashSet<>();
        for (int i = 0; i < account.size(); i++) {
            AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
            accountInfoEntity.setAccountNumber(new BigInteger(account.get(i).get("account_number").asText()));
            accountInfoEntity.setAccountStatus(StatusEnum.getStatusEnumByValue(account.get(i).get("account_status").asText()));
            accountInfoEntity.setCurrentBalance(account.get(i).get("current_balance").asDouble());
            accountInfoEntity.setDateOpened(LocalDate.parse(account.get(i).get("date_opened").asText()));
            accountInfoEntity.setDaysInArrears(account.get(i).get("days_in_arrears").asInt());
            accountInfoEntity.setDelinquencyCode(account.get(i).get("delinquency_code").asText());
            accountInfoEntity.setHighestDaysInArrears(account.get(i).get("highest_days_in_arrears").asInt());
            accountInfoEntity.setIsYourAccount(account.get(i).get("is_your_account").asBoolean());
            accountInfoEntity.setLastPaymentAmount(account.get(i).get("last_payment_amount").asDouble());
            accountInfoEntity.setLastPaymentDate(account.get(i).get("last_payment_date") == null ?
                    LocalDate.parse(account.get(i).get("last_payment_date").asText()) : null);
            accountInfoEntity.setLoadedAt(LocalDate.parse(account.get(i).get("loaded_at").asText()));
            accountInfoEntity.setOriginalAmount(account.get(i).get("original_amount").asDouble());
            accountInfoEntity.setOverdueBalance(account.get(i).get("overdue_balance").asDouble());
            accountInfoEntity.setOverdueDate(account.get(i).get("overdue_date") == null ?
                    LocalDate.parse(account.get(i).get("overdue_date").asText()) : null);
            accountInfoEntity.setProductTypeId(account.get(i).get("product_type_id").asInt());
            accountInfoEntity.setCreditBureau(creditBureauEntity);
            accountInfoEntities.add(accountInfoEntity);
        }

        creditBureauEntity.setVerifiedPerson(verifiedPersonEntity);
        creditBureauEntity.setAccountInfos(accountInfoEntities);
        creditBureauEntity.setRequests(Set.of(requestEntity));

        var person = jsonNode.get("regPerson");
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(person.get("firstName").asText());
        personEntity.setLastName(person.get("lastName").asText());
        if (person.get("middleName") != null) {
            personEntity.setMiddleName(person.get("middleName").asText());
        }
        personEntity.setRequests(Set.of(requestEntity));

        requestEntity.setPerson(personEntity);
        requestEntity.setCreditBureau(creditBureauEntity);

        return creditBureauEntity;
    }

}
