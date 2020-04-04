package springframework.guru.repoSearchEngine.exception;
import java.sql.Timestamp;

public class ErrorMsg {
    private int status;
    private String message;
    private Timestamp timestamp;

    public ErrorMsg(Exception ex) {
        this.status = 500;
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

    public void setStatus(int status) {
        this.status = status;
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
