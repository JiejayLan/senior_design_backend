package springframework.guru.repoSearchEngine.service.googleApiService;

import java.util.ArrayList;

public interface GoogleApiService {
    ArrayList<String> searchGitlabRepoLinks(String q);
}
