
//@Service
//public class GitlabApiServiceImpl implements GitlabApiService {
//    private static String GITLAB_BASE_URL;
//    private static String GITLAB_API_KEY;
//
//    public GitlabApiServiceImpl(@Value("${GITLAB_BASE_URL}") String GITLAB_BASE_URL,
//                                @Value("${GITLAB_API_KEY}") String GITLAB_API_KEY
//                             ) {
//        this.GITLAB_BASE_URL = GITLAB_BASE_URL;
//        this.GITLAB_API_KEY = GITLAB_API_KEY;
//    }
//    @Override
//    public GitlabRepoDto[] acquireSingleRepo(String searchKey) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("PRIVATE-TOKEN", GITLAB_API_KEY);
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        String request_url = GITLAB_BASE_URL + "/search"+"?scope=projects&search=" + searchKey;
//        ResponseEntity<GitlabRepoDto[]> gitLabRepo = restTemplate.exchange(
//                request_url,
//                HttpMethod.GET, entity,
//                GitlabRepoDto[].class);
//        GitlabRepoDto[] gitlab = gitLabRepo.getBody();
//        return gitlab;
//    }
//}


package springframework.guru.repoSearchEngine.service.gitlabApiService;

        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.http.*;
        import org.springframework.stereotype.Service;
        import org.springframework.web.client.RestTemplate;
        import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;
        import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;

        import java.util.Arrays;

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
        HttpHeaders headers = new HttpHeaders();
//        headers.set("PRIVATE-TOKEN", GITLAB_API_KEY);
        HttpEntity entity = new HttpEntity(headers);
        String request_url = GITLAB_BASE_URL.concat("/projects/").concat(link);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//
//
        ResponseEntity<GitlabRepoDto> gitLabRepo = restTemplate.exchange(
                "https://api.hearthstonejson.com/v1/19776/enUS/cards.json",
                HttpMethod.GET, entity,
                GitlabRepoDto.class);
        GitlabRepoDto gitlab  = restTemplate.getForObject("https://api.bitbucket.org/2.0/repositories/phlogistonjohn/tweakmsg", GitlabRepoDto.class);
//        RestTemplate rt = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        String url = "https://jsonplaceholder.typicode.com/posts/1";
//        ResponseEntity<String> res = rt.exchange(url, HttpMethod.GET, entity, String.class);

        return null;
    }
}

