package springframework.guru.repoSearchEngine.dto.github.gitlubCommit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubCommit {
    @JsonProperty("commit")
    private Commit commit;

    public GithubCommit() {}

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}
