package springframework.guru.repoSearchEngine.service.gitlabApiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
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
    private static String GITLAB_API_KEY;

    public GitlabApiServiceImpl(@Value("${GITLAB_BASE_URL}") String GITLAB_BASE_URL,
                                @Value("${GITLAB_API_KEY}") String GITLAB_API_KEY
    ) {
        this.GITLAB_BASE_URL = GITLAB_BASE_URL;
        this.GITLAB_API_KEY = GITLAB_API_KEY;
    }


    @Override
    public GitlabRepoDto acquireSingleRepo(String link) {
        RestTemplate restTemplate = new RestTemplate();
        String request_url = "https://gitlab.com/api/v4/projects/zedtux%2Frails-react-devise-bootstrap";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request_url);
        UriComponents components = builder.build(true);
        URI uri = components.toUri();
        GitlabRepoDto gitlabRepoDto = restTemplate.getForObject(uri, GitlabRepoDto.class);

        return null;
    }
}

