package springframework.guru.repoSearchEngine.service;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.RepoSearchItem;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;
import springframework.guru.repoSearchEngine.service.githubApiService.GithubApiService;
import springframework.guru.repoSearchEngine.service.gitlabApiService.GitlabApiService;
import springframework.guru.repoSearchEngine.service.googleApiService.GoogleApiService;
import java.util.ArrayList;

@Service
public class RepoSearchServiceImpl implements RepoSearchService {

    private final int REPO_SIZE = 10;
    private GitlabApiService gitlabApiService;
    private GithubApiService githubAPIService;
    private GoogleApiService googleApiService;

    public RepoSearchServiceImpl(GithubApiService githubAPIService,
                                 GitlabApiService gitlabApiService,
                                 GoogleApiService googleApiService) {
        this.githubAPIService = githubAPIService;
        this.gitlabApiService = gitlabApiService;
        this.googleApiService = googleApiService;
    }
    //分拆成多个小的methods
    @Override
    public ArrayList<RepoSearchItem> searchRepo(String q){
        ArrayList<RepoSearchItem> repos = new ArrayList<>();
        GithubSearchDto githubResult = githubAPIService.searchGithubRepo(q);
        ArrayList<GithubItem> githubItems = githubResult.getItems();
        for (int i = 0; i < Math.min(githubItems.size(),REPO_SIZE); i++){
            GithubItem githubItem= githubItems.get(i);
            repos.add( new RepoSearchItem(
                    githubItem.getFull_name(),
                    githubItem.getLanguage(),
                    githubItem.getWatchers_count(),
                    githubItem.getStar_count()));
        }
        googleApiService.searchGitlabRepo(q);




//        GitlabRepoDto[] gitlabResults = gitlabApiService.searchGitLabRepo(q);
//        for (int i = 0; i < Math.min(gitlabResults.length,10); i++){
//            GitlabRepoDto gitlabResult= gitlabResults[i];
//            results.add(new RepoSearchItem(gitlabResult.getForks_count(), gitlabResult.getStar_count(),"",""));
//        }
        return repos;
    }
}
