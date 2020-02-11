package springframework.guru.repoSearchEngine.dto;

public class RepoSearchDto {

    public RepoSearchDto(int watchers_count, int star_count, String full_name, String language) {
        this.watchers_count = watchers_count;
        this.full_name = full_name;
        this.star_count = star_count;
        this.language = language;
    }

    private int watchers_count;
    private String full_name;
    private int star_count;
    private  String language;

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
