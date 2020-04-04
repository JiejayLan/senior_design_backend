package springframework.guru.repoSearchEngine.service.repoDetailService;

import springframework.guru.repoSearchEngine.dto.models.RepoDetail;
import java.util.ArrayList;

public interface RepoDetailService {
    RepoDetail acquireRepoDetail(String platform, String full_name);
    RepoDetail acquireRepoMeta(String platform, String full_name);
    ArrayList<String> acquireRepoCommits(String platform, String full_name);
}




