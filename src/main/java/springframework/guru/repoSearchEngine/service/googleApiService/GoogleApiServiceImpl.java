package springframework.guru.repoSearchEngine.service.googleApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.googleApi.GoogleApiDto;
import springframework.guru.repoSearchEngine.dto.googleApi.GoogleLink;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
@Service
public class GoogleApiServiceImpl implements GoogleApiService{
    private String GOOGLE_API_GITLAB_URL;
    private String GOOGLE_API_BITBUCKET_URL;

    public GoogleApiServiceImpl(@Value("${GOOGLE_API_GITLAB_URL}") String GOOGLE_API_GITLAB_URL,
                                @Value("${GOOGLE_API_BITBUCKET_URL}") String GOOGLE_API_BITBUCKET_URL) {
        this.GOOGLE_API_GITLAB_URL = GOOGLE_API_GITLAB_URL;
        this.GOOGLE_API_BITBUCKET_URL = GOOGLE_API_BITBUCKET_URL;
    }

    @Override
    public Set<String> searchGitlabRepoLinks(String searchKey){
        RestTemplate restTemplate = new RestTemplate();
        String request_url = GOOGLE_API_GITLAB_URL + "&q=" + searchKey;
        try{
            GoogleApiDto googleApiDto = restTemplate.getForObject(request_url, GoogleApiDto.class);
            ArrayList<GoogleLink> googleLinks = googleApiDto.getItems();
            Set<String> repo_links = new HashSet<>();
            for(int i =0; i < googleLinks.size(); i++){
                String repo_link = googleLinks.get(i).getLink();
                String[] split_link = repo_link.split("/");

                if(split_link.length <= 4)
                    continue;
                repo_links.add(split_link[3] + "%2f" +split_link[4]);
            }
            return repo_links;
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Set<String> searchBitbucketRepoLinks(String searchKey){
        RestTemplate restTemplate = new RestTemplate();
        String request_url = GOOGLE_API_BITBUCKET_URL + "&q=" + searchKey;
        try{
            GoogleApiDto googleApiDto = restTemplate.getForObject(request_url, GoogleApiDto.class);
            ArrayList<GoogleLink> googleLinks = googleApiDto.getItems();
            Set<String> repo_links = new HashSet<>();
            for(int i =0; i < googleLinks.size(); i++){
                String repo_link = googleLinks.get(i).getLink();
                String[] split_link = repo_link.split("/");

                if(split_link.length <= 4)
                    continue;
                repo_links.add(split_link[3]  + "/" + split_link[4]);
            }
            return repo_links;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
