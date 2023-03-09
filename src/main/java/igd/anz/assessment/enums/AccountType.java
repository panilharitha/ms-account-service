package igd.anz.assessment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AccountType {

    SAVINGS("Savings"), CURRENT("Current");
    private String name;
}
