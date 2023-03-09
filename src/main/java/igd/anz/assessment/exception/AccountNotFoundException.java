package igd.anz.assessment.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@AllArgsConstructor
public class AccountNotFoundException extends RuntimeException{
    private ApiError apiError;

}
