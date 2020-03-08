package springframework.guru.repoSearchEngine.service.repoDetailService;
import org.springframework.stereotype.Service;
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
    public String acquireRepoDetail(String platform, String full_name){

        acquireRepoCommits(platform, full_name);
        return "OK";
    }

    //acquire the first 300 latest Commits
    @Override
    public ArrayList<String>  acquireRepoCommits(String platform, String full_name){
        ArrayList<String> commits_total = new ArrayList<>();
        final int MAX_COMMITS_PER_PAGE = 30;
        int current_page = 1;

        while(true){
            ArrayList<String> commits_single_page =new ArrayList<>();

            if(platform.equals("bitbucket")){
                commits_single_page = bitbucketApiService.getRepoCommits(full_name, current_page);
            }
            else if(platform.equals("gitlab")){

            }
            else if(platform.equals("github")){

            }

            int new_commits_size = commits_single_page.size() + commits_total.size();
            if( new_commits_size > 300)
                break;
            else{
                commits_total.addAll(commits_single_page);
                current_page += 1;
            }

            if(commits_single_page.size() < MAX_COMMITS_PER_PAGE)
                break;
        }

        return commits_total;
    }
}



