package springframework.guru.webclientdemo.domain;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Github {
    @JsonProperty("total_count")
    private int total_count;
    @JsonProperty("incomplete_results")
    private boolean incomplete_results;
    
    public Github() {
    }

    public int getTotal_count() {
        return total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }
}
