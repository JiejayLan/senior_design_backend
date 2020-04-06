package springframework.guru.repoSearchEngine.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

class Owner{
    @JsonProperty("login")
    private String owner_name;
    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("html_url")
    private String profile_url;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }
}


public class GithubItem {
    private String platform = "github";

    @JsonProperty("html_url")
    private String web_url;

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("language")
    private String language;

    @JsonProperty("size")
    private int size;

    @JsonProperty("stargazers_count")
    private int star_count;

    @JsonProperty("forks_count")
    private int fork_count ;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("owner")
    private Owner owner;

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

    public String getOwner_name(){
        return this.owner.getOwner_name();
    }

    public String getAvatar_url() {
        return this.owner.getAvatar_url();
    }

    public String getProfile_url() {
        return this.owner.getProfile_url();
    }

}
