package igd.anz.sample.assessment.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private ApiError apiError;
}
