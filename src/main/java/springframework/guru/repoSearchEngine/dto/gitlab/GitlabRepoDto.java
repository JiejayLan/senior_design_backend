package springframework.guru.repoSearchEngine.dto.gitlab;
import com.fasterxml.jackson.annotation.JsonProperty;

class NameSpace{
    @JsonProperty("name")
    private String owner_name;

    @JsonProperty("web_url")
    private String profile_url;

    @JsonProperty("avatar_url")
    private String avatar__url;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getAvatar__url() {
        return avatar__url;
    }

    public void setAvatar__url(String avatar__url) {
        this.avatar__url = avatar__url;
    }
}

public class GitlabRepoDto {

    private String platform = "gitlab";

    @JsonProperty("path_with_namespace")
    private String full_name;

    @JsonProperty("star_count")
    private int star_count;

    @JsonProperty("fork_count")
    private int fork_count;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("last_activity_at")
    private String updated_at;

    @JsonProperty("namespace")
    private NameSpace namspace;


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

    public String getOwner_name() {
        return this.namspace.getOwner_name();
    }

    public NameSpace getNamspace() {
        return namspace;
    }

    public void setNamspace(NameSpace namspace) {
        this.namspace = namspace;
    }

    public String getAvatar_url(){
        return this.getNamspace().getAvatar__url();
    }

    public String getProfile_url(){
        return this.getNamspace().getProfile_url();
    }

}
