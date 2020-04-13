package springframework.guru.repoSearchEngine.dto.response;

public class CommitCount {
    String endOfWeek;
    int numCommits;

    public CommitCount(String endOfWeek, int numCommits) {
        this.endOfWeek = endOfWeek;
        this.numCommits = numCommits;
    }

    public String getEndOfWeek() {
        return endOfWeek;
    }

    public void setEndOfWeek(String endOfWeek) {
        this.endOfWeek = endOfWeek;
    }

    public int getNumCommits() {
        return numCommits;
    }

    public void setNumCommits(int numCommits) {
        this.numCommits = numCommits;
    }
}
