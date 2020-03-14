package springframework.guru.repoSearchEngine.service.googleApiService;

import java.util.Set;

public interface GoogleApiService {
    Set<String> searchRepoLinks(String platform, String searchKey);
}
