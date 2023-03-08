package igd.anz.sample.assessment.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse extends RepresentationModel<TransactionResponse> {

    private String accountNumber;
    private String AccountName;
    private String valueDate;
    private String currency;
    private Double debitAmount;
    private Double creditAmount;
    private String transactionType;
    private String transactionNarrative;
}
