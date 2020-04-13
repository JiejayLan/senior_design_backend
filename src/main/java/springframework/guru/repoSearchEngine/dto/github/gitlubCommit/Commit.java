package springframework.guru.repoSearchEngine.dto.github.gitlubCommit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commit {
    @JsonProperty("committer")
    private Committer committer;

    public Commit() {

    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }
}
