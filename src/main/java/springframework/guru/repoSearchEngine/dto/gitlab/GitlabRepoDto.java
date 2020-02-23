package springframework.guru.repoSearchEngine.dto.gitlab;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GitlabRepoDto {


    @JsonProperty("star_count")
    private int star_count;

    @JsonProperty("forks_count")
    private int forks_count;

    @JsonProperty("path_with_namespace")
    private String full_name;



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
        return full_name;
    }

    public void setName(String full_name) {
        this.full_name = full_name;
    }


}
