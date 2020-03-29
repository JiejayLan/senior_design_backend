package springframework.guru.repoSearchEngine.dto.gitlab;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GitlabRepoDto {

    private String platform = "gitlab";

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("star_count")
    private int star_count;

    @JsonProperty("fork_count")
    private int fork_count;

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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getStar_count() {
        return star_count;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public int getFork_count() {
        return fork_count;
    }

    public void setFork_count(int fork_count) {
        this.fork_count = fork_count;
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
