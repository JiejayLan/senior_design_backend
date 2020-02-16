package springframework.guru.repoSearchEngine.service;

import springframework.guru.repoSearchEngine.dto.RepoSearchItem;

import java.util.ArrayList;
import java.util.Set;

public interface RepoSearchService {

    ArrayList<RepoSearchItem> searchRepo(String searchKey);

    void searchGithubRepo(ArrayList<RepoSearchItem> repos, String searchKey);

    void searchGitlabRepo(ArrayList<RepoSearchItem> repos, String searchKey);

    void acquireGitlabRepoByLink(ArrayList<RepoSearchItem> repos,Set<String> repo_links );
}

