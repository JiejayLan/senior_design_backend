package springframework.guru.repoSearchEngine.service.repoDetailService;

import springframework.guru.repoSearchEngine.dto.response.CommitCount;
import springframework.guru.repoSearchEngine.dto.response.RepoDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface RepoDetailService {
    RepoDetail acquireRepoDetail(String platform, String full_name);
    RepoDetail acquireRepoMeta(String platform, String full_name);
    ArrayList<String> requestRepoCommits(String platform, String full_name) ;
    ArrayList<CommitCount> countCommitsWeekly(ArrayList<String> commits_total);
    HashMap<String, Integer> countCommitsDaily(ArrayList<String> commits_total);
    Boolean checkCommitsSize(String platform, ArrayList<String> commits_total,
                            ArrayList<String> commits_single_page);
    void sortBitbucketCommits(ArrayList<String> commits_total);

}




