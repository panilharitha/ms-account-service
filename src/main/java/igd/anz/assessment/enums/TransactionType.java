package igd.anz.assessment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransactionType {

    CREDIT("Credit"), DEBIT("Debit");
    private String name;
}
