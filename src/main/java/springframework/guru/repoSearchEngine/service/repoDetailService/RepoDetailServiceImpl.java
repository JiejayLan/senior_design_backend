package springframework.guru.repoSearchEngine.service.repoDetailService;

import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.response.RepoDetail;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.service.bitbucketApiService.BitbucketApiService;
import springframework.guru.repoSearchEngine.service.githubApiService.GithubApiService;
import springframework.guru.repoSearchEngine.service.gitlabApiService.GitlabApiService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    @Override
    public ArrayList<String>  acquireRepoCommits(String platform, String full_name) {
        try{
            ArrayList<String> commits_total = new ArrayList<>();
            int current_page = 1;
            while(true){
                ArrayList<String> commits_single_page =new ArrayList<>();
                if(platform.equals("bitbucket"))
                    commits_single_page = bitbucketApiService.getRepoCommits(full_name, current_page++);
                else if(platform.equals("gitlab"))
                    commits_single_page = gitlabApiService.getRepoCommits(full_name, current_page++);
                else if(platform.equals("github"))
                    commits_single_page = githubAPIService.getRepoCommits(full_name,current_page++);

                checkCommitsDate(commits_single_page);
                commits_total.addAll(commits_single_page);
                if(!checkCommitsSize(platform, commits_total, commits_single_page))
                    break;
            }
            return commits_total;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public Boolean checkCommitsSize(String platform, ArrayList<String> commits_total,
                                ArrayList<String> commits_single_page){
        try{
            int MAX_COMMITS_PER_PAGE = platform.equals("gitlab")? 20 : 30;
            if(commits_total.size() +  MAX_COMMITS_PER_PAGE > 300)
                return false;
            else if(commits_single_page.size() < MAX_COMMITS_PER_PAGE)
                return false;
            else
                return true;
        } catch(Exception ex){

            throw ex;
        }
    }

    @Override
    public void checkCommitsDate(List<String> commits_single_page){
        String last_date = commits_single_page.get(0);
        if(!checkDate(last_date)){
            commits_single_page.clear();
            return;
        }

        String first_date = commits_single_page.get(commits_single_page.size() - 1);
        if(checkDate(first_date))
            return;

        for (int i = 0; i < commits_single_page.size(); i++) {
            String tempDate = commits_single_page.get(i);
            if(checkDate(tempDate))
                continue;
            else {
                List<String> temp_commits =  commits_single_page.subList(i, commits_single_page.size());
                temp_commits.clear();
                break;
            }
        }
    }

    @Override
    public Boolean checkDate(String date) {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date startDate = formatter.parse(date.substring(0,10));
            Date endDate = new Date();
            long diffIn_millies = Math.abs(endDate.getTime() - startDate.getTime());
            long diff_dates = TimeUnit.DAYS.convert(diffIn_millies, TimeUnit.MILLISECONDS);
            if (diff_dates > 365 * 2)
                return false;
            return true;
        } catch(java.text.ParseException ex){
            return false;
        } catch(Exception ex){
            throw ex;
        }
    }
}
