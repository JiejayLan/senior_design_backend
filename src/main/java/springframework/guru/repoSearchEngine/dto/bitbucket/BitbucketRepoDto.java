package springframework.guru.repoSearchEngine.dto.bitbucket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitbucketRepoDto {

    private String platform = "bitbucket";

    @JsonProperty("web_url")
    private String web_url;

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("language")
    private String language;

    @JsonProperty("size")
    private int size;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_on")
    private String created_at;

    @JsonProperty("updated_on")
    private String updated_at;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
