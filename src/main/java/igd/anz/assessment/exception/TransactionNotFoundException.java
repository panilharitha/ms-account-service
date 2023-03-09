package igd.anz.assessment.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionNotFoundException extends RuntimeException{
    private ApiError apiError;
}
