package springframework.guru.repoSearchEngine.dto.github.gitlubCommit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commit {
    @JsonProperty("author")
    private Author author;

    public Commit() {

    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
