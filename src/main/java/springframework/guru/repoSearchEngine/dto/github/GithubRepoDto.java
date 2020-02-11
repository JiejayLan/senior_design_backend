package springframework.guru.repoSearchEngine.dto.github;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GithubRepoDto {
    @JsonProperty("items")
    private ArrayList<GithubItem> items;

    public GithubRepoDto() {
    }

    public void setItems(ArrayList<GithubItem> items) {
        this.items = items;
    }

    public ArrayList<GithubItem> getItems() {
        return items;
    }
}
