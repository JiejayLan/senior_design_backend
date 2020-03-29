package springframework.guru.repoSearchEngine.service.googleApiService;

import springframework.guru.repoSearchEngine.dto.googleApi.GoogleLink;
import java.util.ArrayList;
import java.util.Set;

public interface GoogleApiService {
    Set<String> searchRepoLinks(String platform, String searchKey,int start);
    void extractFullname(Set<String> repo_fullnames, ArrayList<GoogleLink> googleLinks);
}
