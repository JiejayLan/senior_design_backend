package springframework.guru.repoSearchEngine.service.repoDetailService;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.response.RepoDetail;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.service.bitbucketApiService.BitbucketApiService;
import springframework.guru.repoSearchEngine.service.githubApiService.GithubApiService;
import springframework.guru.repoSearchEngine.service.gitlabApiService.GitlabApiService;
import java.util.ArrayList;

@Service
public class RepoDetailServiceImpl implements RepoDetailService{
    private GitlabApiService gitlabApiService;
    private GithubApiService githubAPIService;
    private BitbucketApiService bitbucketApiService;

    public RepoDetailServiceImpl(GitlabApiService gitlabApiService,
                                 GithubApiService githubAPIService,
                                 BitbucketApiService bitbucketApiService) {
        this.gitlabApiService = gitlabApiService;
        this.githubAPIService = githubAPIService;
        this.bitbucketApiService = bitbucketApiService;
    }

    @Override
    public RepoDetail acquireRepoDetail(String platform, String full_name){
        try{
            RepoDetail repoInfo = acquireRepoMeta(platform, full_name);
            ArrayList<String> commits = acquireRepoCommits(platform, full_name);
            repoInfo.setCommits(commits);
            return repoInfo;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public RepoDetail acquireRepoMeta(String platform, String full_name) {
        try{
            RepoDetail repoInfo = null;
            if(platform.equals("bitbucket")) {
                BitbucketRepoDto bitbucketRepoDto= bitbucketApiService.acquireSingleRepo(full_name);
                repoInfo = new RepoDetail(bitbucketRepoDto);
            }
            else if(platform.equals("gitlab")){
                GitlabRepoDto gitlabRepoDto = gitlabApiService.acquireSingleRepo(full_name);
                repoInfo = new RepoDetail(gitlabRepoDto);
            }
            else if(platform.equals("github")){
                GithubItem githubItem = githubAPIService.acquireSingleRepo(full_name);
                repoInfo = new RepoDetail(githubItem);
            }
            return repoInfo;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    //acquire the first 300 latest Commits
    @Override
    public ArrayList<String>  acquireRepoCommits(String platform, String full_name){
        try{
            ArrayList<String> commits_total = new ArrayList<>();
            int MAX_COMMITS_PER_PAGE = platform.equals("gitlab")? 20 : 30;
            int current_page = 1;

            while(true){
                ArrayList<String> commits_single_page =new ArrayList<>();
                if(platform.equals("bitbucket"))
                    commits_single_page = bitbucketApiService.getRepoCommits(full_name, current_page++);
                else if(platform.equals("gitlab"))
                    commits_single_page = gitlabApiService.getRepoCommits(full_name, current_page++);
                else if(platform.equals("github"))
                    commits_single_page = githubAPIService.getRepoCommits(full_name,current_page++);
                commits_total.addAll(commits_single_page);

                if(commits_total.size() > 300 - MAX_COMMITS_PER_PAGE)
                    break;
                if(commits_single_page.size() < MAX_COMMITS_PER_PAGE)
                    break;
            }
            return commits_total;
        }
        catch(Exception ex){
            throw ex;
        }
    }
}
