package springframework.guru.webclientdemo.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubItem {
    @JsonProperty("watchers_count")
    private int watchers_count;
    @JsonProperty("language")
    private  String language;
    @JsonProperty("full_name")
    private String full_name;
    @JsonProperty("star_count")
    private int star_count;

    public int getStar_count() {
        return star_count;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
