package springframework.guru.repoSearchEngine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebInputException;
import springframework.guru.repoSearchEngine.controller.SearchRepoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(SearchRepoController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMsg handleError(ConstraintViolationException ex) {
        logger.error("Error: - Status {}, Body {}", ex.getMessage(),
                ex.getMessage());
        ErrorMsg responseError = new ErrorMsg(ex, HttpStatus.BAD_REQUEST);
        return responseError;
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMsg handleError(ServerWebInputException ex) {
        logger.error("Error: - Status {}, Body {}", ex.getMessage(),
                ex.getMessage());
        ErrorMsg responseError = new ErrorMsg(ex, HttpStatus.BAD_REQUEST);
        return responseError;
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMsg handleError(InternalException ex) {
        logger.error("Error: - Status {}, Body {}", ex.getMessage(),
                ex.getMessage());
        ErrorMsg responseError = new ErrorMsg(ex, ex.getStatus_code());
        return responseError;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMsg handleError(Exception ex) {
        logger.error("Error: - Status {}, Body {}", ex.getMessage(),
                ex.getMessage());
        ErrorMsg responseError = new ErrorMsg(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseError;
    }
}
