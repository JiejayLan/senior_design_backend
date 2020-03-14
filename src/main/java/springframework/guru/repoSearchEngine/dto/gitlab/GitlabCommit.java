package springframework.guru.repoSearchEngine.dto.gitlab;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitlabCommit {
    @JsonProperty("created_at")
    private String date;
    public GitlabCommit() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
