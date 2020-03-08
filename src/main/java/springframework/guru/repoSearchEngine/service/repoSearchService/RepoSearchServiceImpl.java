package springframework.guru.repoSearchEngine.service.repoSearchService;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.RepoSearchItem;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.GithubSearchDto;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.service.bitbucketApiService.BitbucketApiService;
import springframework.guru.repoSearchEngine.service.githubApiService.GithubApiService;
import springframework.guru.repoSearchEngine.service.gitlabApiService.GitlabApiService;
import springframework.guru.repoSearchEngine.service.googleApiService.GoogleApiService;
import java.util.ArrayList;
import java.util.Set;

@Service
public class RepoSearchServiceImpl implements RepoSearchService {

    private final int REPO_SIZE = 10;
    private GitlabApiService gitlabApiService;
    private GithubApiService githubAPIService;
    private BitbucketApiService bitbucketApiService;
    private GoogleApiService googleApiService;

    public RepoSearchServiceImpl(GithubApiService githubAPIService,
                                 GitlabApiService gitlabApiService,
                                 BitbucketApiService bitbucketApiService,
                                 GoogleApiService googleApiService) {
        this.githubAPIService = githubAPIService;
        this.gitlabApiService = gitlabApiService;
        this.bitbucketApiService = bitbucketApiService;
        this.googleApiService = googleApiService;
    }

    @Override
    public ArrayList<RepoSearchItem> searchRepo(String searchKey){
        ArrayList<RepoSearchItem> repos = new ArrayList<>();
        searchGithubRepo(repos, searchKey);
        searchGitlabRepo(repos, searchKey);
        searchBitbucketRepo(repos, searchKey);
        return repos;
    }

    @Override
    public void searchGithubRepo(ArrayList<RepoSearchItem> repos, String searchKey){
        try{
            GithubSearchDto githubResult = githubAPIService.searchGithubRepo(searchKey);
            ArrayList<GithubItem> githubItems = githubResult.getItems();
            for (int i = 0; i < Math.min(githubItems.size(),REPO_SIZE); i++){
                GithubItem githubItem= githubItems.get(i);
                if (githubItem == null)
                    continue;

                repos.add( new RepoSearchItem(
                        "github",
                        githubItem.getFull_name(),
                        githubItem.getLanguage(),
                        githubItem.getWatchers_count(),
                        githubItem.getStar_count()));
            }
        }
        catch (Exception ex){
            return;
        }

    }

    @Override
    public void searchGitlabRepo(ArrayList<RepoSearchItem> repos, String searchKey){
        try{
            Set<String> repo_links = googleApiService.searchGitlabRepoLinks(searchKey);
            if(repo_links == null)
                return;
            acquireGitlabRepoByLink(repos, repo_links);
        }
        catch (Exception ex){
            return;
        }

    }

    @Override
    public void acquireGitlabRepoByLink(ArrayList<RepoSearchItem> repos,Set<String> repo_links ){
        try{
            for(String link : repo_links){
                GitlabRepoDto gitlabRepoDto = gitlabApiService.acquireSingleRepo(link);
                if (gitlabRepoDto ==null)
                    continue;
                repos.add( new RepoSearchItem(
                        "gitlab",
                        gitlabRepoDto.getName(),
                        null,
                        gitlabRepoDto.getForks_count(),//missing watcher count
                        gitlabRepoDto.getStar_count()));
            }
        }
        catch(Exception ex){
            return;
        }
    }

    @Override
    public void searchBitbucketRepo(ArrayList<RepoSearchItem> repos, String searchKey){
        try{
            Set<String> repo_links = googleApiService.searchBitbucketRepoLinks(searchKey);
            if(repo_links == null)
                return;
            acquireBitbucketRepoByLink(repos, repo_links);
        }
        catch (Exception ex){
            return;
        }
    }

    @Override
    public void acquireBitbucketRepoByLink(ArrayList<RepoSearchItem> repos,Set<String> repo_links ){
        try{
            for(String link : repo_links){
                BitbucketRepoDto bitbucketRepoDto = bitbucketApiService.acquireSingleRepo(link);
                if (bitbucketRepoDto ==null)
                    continue;
                repos.add( new RepoSearchItem(
                        "bitbucket",
                        bitbucketRepoDto.getFull_name(),
                        bitbucketRepoDto.getLanguage(),
                        0,//missing watcher count
                        0));
            }
        }
        catch(Exception ex){
            return;
        }
    }
}
