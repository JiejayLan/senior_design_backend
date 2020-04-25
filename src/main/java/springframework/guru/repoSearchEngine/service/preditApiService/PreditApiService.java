package springframework.guru.repoSearchEngine.service.preditApiService;

import org.json.JSONArray;
import org.json.JSONException;
import springframework.guru.repoSearchEngine.dto.response.CommitCount;

import java.util.ArrayList;

public interface PreditApiService {
    void predictCommits(ArrayList<CommitCount> commits_count_weekly);
    String prepareRequestBody(ArrayList<CommitCount> commits_count_weekly);
    void appendFutureCommits(ArrayList<CommitCount> commits_count_weekly, JSONArray predit_commits);
    boolean isActiveRepo(ArrayList<CommitCount> commits_count_weekly);
}
