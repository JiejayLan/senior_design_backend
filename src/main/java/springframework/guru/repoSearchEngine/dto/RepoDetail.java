package springframework.guru.repoSearchEngine.dto;

import java.util.ArrayList;

public class RepoDetail {
    private int watchers_count;
    private String language;
    private String full_name;
    private int star_count;
    private ArrayList<String> commits;

    public RepoDetail() {
    }

    public RepoDetail(int watchers_count, String language, String full_name, int star_count, ArrayList<String> commits) {
        this.watchers_count = watchers_count;
        this.language = language;
        this.full_name = full_name;
        this.star_count = star_count;
        this.commits = commits;
    }

    public ArrayList<String> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<String> commits) {
        this.commits = commits;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
}
