package springframework.guru.repoSearchEngine.service.gitlabApiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import springframework.guru.repoSearchEngine.dto.github.gitlubCommit.GithubCommit;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabCommit;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.exception.InternalException;

import java.net.URI;
import java.util.ArrayList;


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
    public String convertLink(String link){
        String updateLink = link.replace("/", "%2f");
        return updateLink;
    }

    @Override
    public GitlabRepoDto acquireSingleRepo(String link) {
        try{
            link = convertLink(link);
            RestTemplate restTemplate = new RestTemplate();
            String request_url =  GITLAB_BASE_URL + link;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request_url);
            UriComponents components = builder.build(true);
            URI uri = components.toUri();
            GitlabRepoDto gitlabRepoDto = restTemplate.getForObject(uri, GitlabRepoDto.class);
            return gitlabRepoDto;
        }
        catch (Exception ex) {
            throw new InternalException(HttpStatus.INTERNAL_SERVER_ERROR, "GITLAB API INTERNAL_SERVER_ERROR");
        }
    }
    @Override
    public ArrayList<String> getRepoCommits(String path, int page){
        try{
            path = convertLink(path);
            String request_url =  GITLAB_BASE_URL + path + "/repository/commits?page=" +page;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request_url);
            UriComponents components = builder.build(true);
            URI uri = components.toUri();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GitlabCommit[]> responseEntity = restTemplate.getForEntity(uri, GitlabCommit[].class);
            GitlabCommit[] gitlabCommits = responseEntity.getBody();

            return extractDateString(gitlabCommits);
        }
        catch (Exception ex) {
            throw new InternalException(HttpStatus.INTERNAL_SERVER_ERROR, "GITLAB API INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public ArrayList<String> extractDateString(GitlabCommit[] gitlabCommits){
        try{
            ArrayList<String> dates = new ArrayList<>();
            for(GitlabCommit commit : gitlabCommits){
                String dateStr = commit.getDate();
                dates.add(dateStr.substring(0,10));
            }

            return dates;
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}

