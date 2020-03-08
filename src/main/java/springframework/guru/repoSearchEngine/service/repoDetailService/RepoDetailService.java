package springframework.guru.repoSearchEngine.service.repoDetailService;

import java.util.ArrayList;

public interface RepoDetailService {
    String acquireRepoDetail(String platform, String full_name);
    ArrayList<String> acquireRepoCommits(String platform, String full_name);
}




