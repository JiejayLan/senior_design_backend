package springframework.guru.repoSearchEngine.service.googleApiService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public Set<String> searchRepoLinks(Set<String> all_fullnames,String platform, String searchKey, int start){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String request_url;

            if(platform == "gitlab")
                request_url = GOOGLE_API_GITLAB_URL + "&q=" + searchKey +"&start=" + start;
            else
                request_url = GOOGLE_API_BITBUCKET_URL + "&q=" + searchKey +"&start=" + start;

            Set<String> repo_fullnames= new HashSet<>();
            GoogleApiDto googleApiDto = restTemplate.getForObject(request_url, GoogleApiDto.class);
            ArrayList<GoogleLink> googleLinks = googleApiDto.getItems();
            extractFullname(all_fullnames,repo_fullnames,googleLinks);

            if(repo_fullnames == null)
                throw new InternalException("Fail to Connect");

            return repo_fullnames;
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    @Override
    public void extractFullname(Set<String> all_fullnames, Set<String> repo_fullnames, ArrayList<GoogleLink> googleLinks){
        try{
            for(int i =0; i < googleLinks.size(); i++){
                String repo_link = googleLinks.get(i).getLink();
                String[] split_link = repo_link.split("/");
                if(split_link.length <= 4)
                    continue;
                String new_fullname = split_link[3] + "/" +split_link[4];
                if(!all_fullnames.contains(new_fullname)){
                    repo_fullnames.add(new_fullname);
                    all_fullnames.add(new_fullname);
                }
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }

}
