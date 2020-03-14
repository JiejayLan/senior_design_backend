package springframework.guru.repoSearchEngine.dto.bitbucket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitbucketRepoDto {

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("language")
    private String language;

    @JsonProperty("updated_on")
    private String updated_on;

    public BitbucketRepoDto() { }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }
}
