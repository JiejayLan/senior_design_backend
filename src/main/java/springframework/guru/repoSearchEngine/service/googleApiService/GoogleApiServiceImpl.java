package springframework.guru.repoSearchEngine.service.googleApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.googleApi.GoogleApiDto;
import springframework.guru.repoSearchEngine.dto.googleApi.GoogleLink;
import java.util.ArrayList;

@Service
public class GoogleApiServiceImpl implements GoogleApiService{
    private String GOOGLE_API_GITLAB_URL;
    public GoogleApiServiceImpl(@Value("${GOOGLE_API_GITLAB_URL}") String GOOGLE_API_GITLAB_URL) {
        this.GOOGLE_API_GITLAB_URL = GOOGLE_API_GITLAB_URL;
    }

    @Override
    public ArrayList<String> searchGitlabRepoLinks(String searchKey){
        RestTemplate restTemplate = new RestTemplate();
        String request_url = GOOGLE_API_GITLAB_URL + "&q=" + searchKey;
        GoogleApiDto googleApiDto = restTemplate.getForObject(request_url, GoogleApiDto.class);
        ArrayList<GoogleLink> googleLinks = googleApiDto.getItems();

        ArrayList<String> repo_links = new ArrayList<>();
        for(int i =0; i < googleLinks.size(); i++){
            String repo_link = googleLinks.get(i).getLink();
            String[] split_link = repo_link.split("/");
            repo_links.add(split_link[3] + "%2f" +split_link[4]);
        }
        return repo_links;
    }
}
