package springframework.guru.webclientdemo.domain;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GitLab {

    @JsonProperty("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
