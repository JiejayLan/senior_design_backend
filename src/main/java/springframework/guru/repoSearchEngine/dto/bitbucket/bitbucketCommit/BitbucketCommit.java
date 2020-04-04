package springframework.guru.repoSearchEngine.dto.bitbucket.bitbucketCommit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitbucketCommit {
    @JsonProperty("date")
    private String date;

    public BitbucketCommit() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}