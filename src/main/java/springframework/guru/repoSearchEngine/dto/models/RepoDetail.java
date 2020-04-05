package springframework.guru.repoSearchEngine.dto.models;

import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import java.util.ArrayList;

public class RepoDetail {
    private String platform;

    private String web_url;

    private String full_name;

    private String language;

    private int size;

    private int star_count;

    private int fork_count ;

    private String description;

    private String created_at;

    private String updated_at;

    private String owner_name;

    private String avatar_url;

    private String profile_url;

    private ArrayList<String> commits;

    public RepoDetail() {
    }

    public RepoDetail(GithubItem githubItem){
        this.platform = githubItem.getPlatform();
        this.web_url = githubItem.getWeb_url();
        this.full_name = githubItem.getFull_name();
        this.language = githubItem.getLanguage();
        this.size = githubItem.getSize();
        this.star_count = githubItem.getStar_count();
        this.fork_count =githubItem.getFork_count();
        this.description = githubItem.getDescription();
        this.created_at = githubItem.getCreated_at().substring(0,10);
        this.updated_at = githubItem.getUpdated_at().substring(0,10);
        this.owner_name = githubItem.getOwner_name();
        this.avatar_url = githubItem.getAvatar_url();
        this.profile_url = githubItem.getProfile_url();
    }

    public RepoDetail(BitbucketRepoDto bitbucketRepoDto) {
        this.platform = bitbucketRepoDto.getPlatform();
        this.web_url = "https://bitbucket.org/" + bitbucketRepoDto.getFull_name();
        this.full_name = bitbucketRepoDto.getFull_name();
        this.language = bitbucketRepoDto.getLanguage();
        this.size = bitbucketRepoDto.getSize();
        this.description = bitbucketRepoDto.getDescription();
        this.created_at = bitbucketRepoDto.getCreated_at().substring(0,10);
        this.updated_at = bitbucketRepoDto.getUpdated_at().substring(0,10);
        this.owner_name = bitbucketRepoDto.getOwner().getOwner_name();
        this.avatar_url = bitbucketRepoDto.getOwner().getAvator();
        this.profile_url = bitbucketRepoDto.getOwner().getProfile();
    }

    public RepoDetail(GitlabRepoDto gitlabRepoDto){
        this.platform = gitlabRepoDto.getPlatform();
        this.web_url = "https://gitlab.com/" + gitlabRepoDto.getFull_name();
        this.full_name = gitlabRepoDto.getFull_name();
        this.star_count = gitlabRepoDto.getStar_count();
        this.fork_count = gitlabRepoDto.getFork_count();
        this.description = gitlabRepoDto.getDescription();
        this.created_at = gitlabRepoDto.getCreated_at().substring(0,10);
        this.updated_at = gitlabRepoDto.getUpdated_at().substring(0,10);
        this.owner_name = gitlabRepoDto.getOwner_name();
        this.avatar_url = "https://assets.gitlab-static.net/"+gitlabRepoDto.getAvatar_url();
        this.profile_url = gitlabRepoDto.getProfile_url();
    }

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

    public ArrayList<String> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<String> commits) {
        this.commits = commits;
    }
}
