package springframework.guru.repoSearchEngine.service.preditApiService;

import springframework.guru.repoSearchEngine.dto.response.CommitCount;

import java.util.ArrayList;

public interface PreditApiService {
    void predictCommits(ArrayList<CommitCount> commits_count_weekly);

    String prepareRequestBody(ArrayList<CommitCount> commits_count_weekly);
}
