package springframework.guru.repoSearchEngine.exception;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class ErrorMsg {
    private int status;
    private String message;
    private Timestamp timestamp;

    public ErrorMsg(Exception ex) {
        this.message = ex.getMessage();
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public ErrorMsg(Exception ex, HttpStatus status) {
        this.status = status.value();
        this.message = ex.getMessage();
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public ErrorMsg(InternalException ex) {
        this.status = ex.getStatus_code().value();
        this.message = ex.getMessage();
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
