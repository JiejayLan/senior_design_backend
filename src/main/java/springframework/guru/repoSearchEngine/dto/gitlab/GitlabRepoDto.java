package springframework.guru.repoSearchEngine.dto.gitlab;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GitlabRepoDto {

    @JsonProperty("description")
    private String description;

    @JsonProperty("star_count")
    private int star_count;

    @JsonProperty("forks_count")
    private int forks_count;

    @JsonProperty("name")
    private String name;

    @JsonProperty("tag_list")
    public String[] tag_list;

    @JsonProperty("shared_with_groups")
    public String[] shared_with_groups;

    public String[] getShared_with_groups() {
        return shared_with_groups;
    }

    public String[] getTag_list() {
        return tag_list;
    }

    public void setTag_list(String[] tag_list) {
        this.tag_list = tag_list;
    }

    public void setShared_with_groups(String[] shared_with_groups) {
        this.shared_with_groups = shared_with_groups;
    }

    public int getStar_count() {
        return star_count;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
