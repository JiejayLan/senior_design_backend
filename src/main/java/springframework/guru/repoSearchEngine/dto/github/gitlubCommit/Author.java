package springframework.guru.repoSearchEngine.dto.github.gitlubCommit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {
    @JsonProperty("date")
    private String date;

    public Author() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
