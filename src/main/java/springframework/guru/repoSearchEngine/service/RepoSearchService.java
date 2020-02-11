package springframework.guru.repoSearchEngine.service;

import springframework.guru.repoSearchEngine.dto.RepoSearchItem;

import java.util.ArrayList;

public interface RepoSearchService {

    ArrayList<RepoSearchItem> searchRepo(String q);
}

