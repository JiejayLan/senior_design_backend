package springframework.guru.repoSearchEngine.dto.github;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GithubSearchDto {
    @JsonProperty("items")
    private GithubItem[] items;

    public GithubSearchDto() {
    }

    public void setItems(GithubItem[] items) {
        this.items = items;
    }

    public GithubItem[] getItems() {
        return items;
    }
}
