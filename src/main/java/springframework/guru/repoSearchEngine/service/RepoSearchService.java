package springframework.guru.repoSearchEngine.service;

import springframework.guru.repoSearchEngine.dto.RepoSearchDto;
import java.util.ArrayList;

public interface RepoSearchService {


    ArrayList<RepoSearchDto> getRepo(String q);
}

