package springframework.guru.repoSearchEngine.dto.bitbucket.bitbucketCommit;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BitbucketCommitsDto {
    @JsonProperty("values")
    private BitbucketCommit[] commits;

    public BitbucketCommitsDto() {
    }

    public BitbucketCommit[] getCommits() {
        return commits;
    }

    public void setCommits(BitbucketCommit[] commits) {
        this.commits = commits;
    }

}
