package springframework.guru.repoSearchEngine.service.gitlabApiService;

import springframework.guru.repoSearchEngine.dto.gitlab.GitlabCommit;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;

import java.util.ArrayList;

public interface GitlabApiService {
    GitlabRepoDto acquireSingleRepo(String link);
    String convertLink(String link);
    ArrayList<String> getRepoCommits(String path, int page);
    ArrayList<String> extractDateString(GitlabCommit[] gitlabCommit);
}
