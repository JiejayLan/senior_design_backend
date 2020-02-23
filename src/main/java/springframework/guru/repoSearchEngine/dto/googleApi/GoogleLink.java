package springframework.guru.repoSearchEngine.dto.googleApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleLink {
    @JsonProperty("link")
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
