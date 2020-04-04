package springframework.guru.repoSearchEngine.exception;

import org.springframework.http.HttpStatus;

public class InternalException extends RuntimeException{

    private HttpStatus status_code;

    public InternalException(HttpStatus status_code) {
        this.status_code = status_code;
    }

    public InternalException(HttpStatus status_code, String message) {
        super(message);
        this.status_code = status_code;
    }

    public InternalException(String message) {
        super(message);
    }

    public HttpStatus getStatus_code() {
        return status_code;
    }

    public void setStatus_code(HttpStatus status_code) {
        this.status_code = status_code;
    }
}
