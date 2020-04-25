package springframework.guru.repoSearchEngine.dto.github.gitlubCommit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Committer {
    @JsonProperty("date")
    private String date;

    public Committer() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
