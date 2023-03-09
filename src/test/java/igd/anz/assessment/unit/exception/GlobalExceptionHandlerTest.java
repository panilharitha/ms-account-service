package igd.anz.assessment.unit.exception;

import igd.anz.assessment.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GlobalExceptionHandlerTest {


    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


}
