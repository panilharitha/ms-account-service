package igd.anz.sample.assessment.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TransactionType {

    CREDIT("Credit"), DEBIT("Debit");
    private String name;
}
