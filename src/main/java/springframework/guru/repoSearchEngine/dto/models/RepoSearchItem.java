package springframework.guru.repoSearchEngine.dto.models;

import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;

public class RepoSearchItem {

    private String platform;

    private String full_name;

    private String language;

    private int star_count;

    private String description;

    private String updated_at;

    public RepoSearchItem() { }

    public RepoSearchItem(GithubItem githubItem) {
        this.platform = "github";
        this.full_name = githubItem.getFull_name();
        this.language = githubItem.getLanguage();
        this.star_count = githubItem.getStar_count();
        this.description = githubItem.getDescription();
        this.updated_at = githubItem.getUpdated_at();
    }

    public RepoSearchItem(BitbucketRepoDto bitbucketRepoDto) {
        this.platform = "bitbucket";
        this.full_name = bitbucketRepoDto.getFull_name();
        this.language = bitbucketRepoDto.getLanguage();
        this.description = bitbucketRepoDto.getDescription();
        this.updated_at = bitbucketRepoDto.getUpdated_at();
    }

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStar_count() {
        return star_count;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
