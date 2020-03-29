package springframework.guru.repoSearchEngine.service.repoSearchService;

import springframework.guru.repoSearchEngine.dto.models.RepoSearchItem;

import java.util.ArrayList;
import java.util.Set;

public interface RepoSearchService {

    ArrayList<RepoSearchItem> searchRepo(String searchKey);

    void searchGithubRepo(ArrayList<RepoSearchItem> repos, String searchKey);

    void searchGitlabRepo(ArrayList<RepoSearchItem> repos, String searchKey);

    void acquireSingalGitlabRepo(ArrayList<RepoSearchItem> repos, Set<String> repo_links );

    void searchBitbucketRepo(ArrayList<RepoSearchItem> repos, String searchKey);

    void acquireSingalBitbucketRepo(ArrayList<RepoSearchItem> repos, Set<String> repo_fullnames, final int MAX_REPO_NUM );


}

