package springframework.guru.repoSearchEngine.service.repoDetailService;

import springframework.guru.repoSearchEngine.dto.response.RepoDetail;
import java.util.ArrayList;
import java.util.List;

public interface RepoDetailService {
    RepoDetail acquireRepoDetail(String platform, String full_name);
    RepoDetail acquireRepoMeta(String platform, String full_name);
    ArrayList<String> acquireRepoCommits(String platform, String full_name) ;
    Boolean checkCommitsSize(String platform, ArrayList<String> commits_total,
                            ArrayList<String> commits_single_page);
    void checkCommitsDate(List<String> commits_single_page);
    Boolean checkDate(String date) throws Exception;
}




