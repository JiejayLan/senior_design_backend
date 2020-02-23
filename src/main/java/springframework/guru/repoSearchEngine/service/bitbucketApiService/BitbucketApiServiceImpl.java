package springframework.guru.repoSearchEngine.service.bitbucketApiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import java.net.URI;

@Service
public class BitbucketApiServiceImpl implements BitbucketApiService {
    private static String BITBUCKET_BASE_URL;

    public BitbucketApiServiceImpl(@Value("${BITBUCKET_BASE_URL}") String BITBUCKET_BASE_URL) {
        this.BITBUCKET_BASE_URL = BITBUCKET_BASE_URL;
    }

    public BitbucketRepoDto acquireSingleRepo(String link){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String request_url =  BITBUCKET_BASE_URL + "/repositories/"+ link;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request_url);
            UriComponents components = builder.build(true);
            URI uri = components.toUri();
            BitbucketRepoDto bitbucketRepoDto = restTemplate.getForObject(uri, BitbucketRepoDto.class);
            return bitbucketRepoDto;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
