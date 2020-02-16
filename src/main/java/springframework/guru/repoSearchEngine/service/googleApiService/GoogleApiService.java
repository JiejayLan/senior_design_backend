package springframework.guru.repoSearchEngine.service.googleApiService;

import java.util.Set;

public interface GoogleApiService {
    Set<String> searchGitlabRepoLinks(String q);
}
