package springframework.guru.repoSearchEngine.dto.models;

import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;

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

    private Owner owner;

    private ArrayList<String> commits;

    public RepoDetail() {
    }

    public RepoDetail(BitbucketRepoDto bitbucketRepoDto) {
        this.platform = bitbucketRepoDto.getPlatform();
        this.web_url = "https://bitbucket.org/" + bitbucketRepoDto.getFull_name();
        this.full_name = bitbucketRepoDto.getFull_name();
        this.language = bitbucketRepoDto.getLanguage();
        this.size = bitbucketRepoDto.getSize();
        this.description = bitbucketRepoDto.getDescription();
        this.created_at = bitbucketRepoDto.getCreated_at();
        this.updated_at = bitbucketRepoDto.getUpdated_at();
        this.owner  = new Owner(bitbucketRepoDto.getOwner()) ;

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

    public ArrayList<String> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<String> commits) {
        this.commits = commits;
    }
}
