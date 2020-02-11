package springframework.guru.repoSearchEngine.service.gitlabApiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;

@Service
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
    public GitlabRepoDto[] searchGitLabRepo(String q) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", GITLAB_API_KEY);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String request_url = GITLAB_BASE_URL + "?scope=projects&search=" + q;
        ResponseEntity<GitlabRepoDto[]> gitLabRepo = restTemplate.exchange(
                request_url,
                HttpMethod.GET, entity,
                GitlabRepoDto[].class);
        GitlabRepoDto[] gitlab = gitLabRepo.getBody();
        return gitlab;
    }
}
