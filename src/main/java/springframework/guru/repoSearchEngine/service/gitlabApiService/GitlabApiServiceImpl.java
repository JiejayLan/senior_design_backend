package springframework.guru.repoSearchEngine.service.gitlabApiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import java.net.URI;


@Service
@ComponentScan
@EnableAutoConfiguration
public class GitlabApiServiceImpl implements GitlabApiService {
    private static String GITLAB_BASE_URL;

    public GitlabApiServiceImpl(@Value("${GITLAB_BASE_URL}") String GITLAB_BASE_URL
    ) {
        this.GITLAB_BASE_URL = GITLAB_BASE_URL;

    }

    @Override
    public GitlabRepoDto acquireSingleRepo(String link) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            String request_url =  GITLAB_BASE_URL + "/projects/"+ link;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request_url);
            UriComponents components = builder.build(true);
            URI uri = components.toUri();
            GitlabRepoDto gitlabRepoDto = restTemplate.getForObject(uri, GitlabRepoDto.class);
            return gitlabRepoDto;
        }
        catch (Exception ex) {
            return null;
        }
    }
}

