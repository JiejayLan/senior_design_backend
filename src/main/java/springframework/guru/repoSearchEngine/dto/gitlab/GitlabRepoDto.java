package springframework.guru.webclientdemo.dto.gitlab;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GitlabRepoDto {

    @JsonProperty("description")
    private String description;

    @JsonProperty("star_count")
    private int star_count;

    @JsonProperty("forks_count")
    private int forks_count;

    @JsonProperty("name")
    private String name;

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
