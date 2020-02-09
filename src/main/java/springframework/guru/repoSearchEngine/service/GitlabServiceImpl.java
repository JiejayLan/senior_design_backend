package springframework.guru.repoSearchEngine.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;

@Service
public class GitlabServiceImpl implements GitlabService {
    private static final String GITLAB_BASE_URL = "https://gitlab.com/api/v4/search";

    public GitlabServiceImpl() {
    }

    @Override
    public GitlabRepoDto[] searchGitLabRepo(String q) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", "y4iEVLmaqqU8Mge8fmUD");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<GitlabRepoDto[]> gitLabRepo = restTemplate.exchange(GITLAB_BASE_URL + "?scope=projects&search=" + q,
                HttpMethod.GET, entity,
                GitlabRepoDto[].class);
        GitlabRepoDto[] gitlab = gitLabRepo.getBody();
        return gitlab;
    }
}
