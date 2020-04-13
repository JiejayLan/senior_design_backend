package springframework.guru.repoSearchEngine.dto.response;

public class CommitCount {
    String startDay;
    int numCommits;

    public CommitCount(String startDay, int numCommits) {
        this.startDay = startDay;
        this.numCommits = numCommits;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public int getNumCommits() {
        return numCommits;
    }

    public void setNumCommits(int numCommits) {
        this.numCommits = numCommits;
    }
}
