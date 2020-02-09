package springframework.guru.repoSearchEngine.dto.github;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GithubRepoDto {
    @JsonProperty("total_count")
    private int total_count;
    @JsonProperty("incomplete_results")
    private boolean incomplete_results;
    @JsonProperty("items")
    private ArrayList<GithubItem> items;

    public GithubRepoDto() {
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

    public void setItems(ArrayList<GithubItem> items) {
        this.items = items;
    }

    public ArrayList<GithubItem> getItems() {
        return items;
    }
}
