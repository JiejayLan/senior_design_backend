package springframework.guru.repoSearchEngine.service.repoDetailService;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.response.CommitCount;
import springframework.guru.repoSearchEngine.dto.response.RepoDetail;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.service.bitbucketApiService.BitbucketApiService;
import springframework.guru.repoSearchEngine.service.githubApiService.GithubApiService;
import springframework.guru.repoSearchEngine.service.gitlabApiService.GitlabApiService;
import java.util.*;

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
            ArrayList<String> commits_total = requestRepoCommits(platform, full_name);

            ArrayList<CommitCount> commits_count_weekly = countCommitsWeekly(commits_total);
            repoInfo.setCommits(commits_count_weekly);

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
    public ArrayList<String>  requestRepoCommits(String platform, String full_name) {
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

                commits_total.addAll(commits_single_page);
                if(!checkCommitsSize(platform, commits_total, commits_single_page))
                    break;
            }
            if(platform.equals("bitbucket"))
                sortBitbucketCommits(commits_total);

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
            if(commits_total.size() +  MAX_COMMITS_PER_PAGE > 180)
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
    public ArrayList<CommitCount> countCommitsWeekly(ArrayList<String> commits_total){
        try{
            if(commits_total.isEmpty())
                return null;

            HashMap<String, Integer> commit_counter_daily = countCommitsDaily(commits_total);
            ArrayList<CommitCount> commits_count_weekly = new ArrayList<>();

            DateTime start_date = new DateTime( commits_total.get(commits_total.size()-1));
            DateTime last_date_week = new DateTime(commits_total.get(0));
            DateTime tempDate = last_date_week;
            DateTime date_before_year = new DateTime();
            date_before_year = date_before_year.minusYears(1);

            while(last_date_week.compareTo(start_date) == 1 && last_date_week.compareTo(date_before_year) == 1){
                int count = 0;
                for(int i = 0; i < 7; i++){
                    String dateStr = tempDate.toString().substring(0, 10);
                    if(commit_counter_daily.containsKey(dateStr))
                        count += commit_counter_daily.get(dateStr);
                    tempDate = tempDate.minusDays(1);
                }
                String dateStr = last_date_week.toString();
                commits_count_weekly.add(new CommitCount(dateStr.substring(0, 10), count));
                last_date_week = tempDate;
            }
            return commits_count_weekly;
        } catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public HashMap<String, Integer> countCommitsDaily(ArrayList<String> commits_total) {
        try{
            HashMap<String, Integer> commit_counter_daily = new HashMap<>();

            for(String date:commits_total){
                if(commit_counter_daily.containsKey(date)){
                    int tempCount = commit_counter_daily.get(date);
                    commit_counter_daily.replace(date, ++tempCount);

                }
                else
                    commit_counter_daily.put(date, 1);
            }
            return commit_counter_daily;
        } catch(Exception ex){
            throw ex;
        }
    }


    @Override
    public void sortBitbucketCommits(ArrayList<String> commits_total) {
            Collections.sort(commits_total, new Comparator<String>() {
                @Override
                public int compare(String object1, String object2) {
                    return -object1.compareTo(object2);
                }
            });
    }
}
