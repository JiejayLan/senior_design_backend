package springframework.guru.repoSearchEngine.service;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.dto.github.GithubItem;
import springframework.guru.repoSearchEngine.dto.github.GithubRepoDto;

import springframework.guru.repoSearchEngine.dto.gitlab.GitlabRepoDto;
import springframework.guru.repoSearchEngine.dto.RepoSearchDto;
import java.util.ArrayList;

@Service
public class RepoSearchServiceImpl implements RepoSearchService {

    private GitlabService gitlabService;
    private GithubService githubService;

    public RepoSearchServiceImpl(GithubService githubService, GitlabService gitlabService) {
        this.githubService = githubService;
        this.gitlabService = gitlabService;
    }

    @Override
    public ArrayList<RepoSearchDto> getRepo(String q){
        ArrayList<RepoSearchDto> results = new ArrayList<>();;
        GithubRepoDto githubResult = githubService.searchGithubRepo(q);
        ArrayList<GithubItem> githubItems = githubResult.getItems();
        for (int i = 0; i < Math.min(githubItems.size(),10); i++){
            GithubItem githubItem= githubItems.get(i);
            results.add( new RepoSearchDto(githubItem.getWatchers_count(), githubItem.getStar_count(),"",""));
        }

        GitlabRepoDto[] gitlabResults = gitlabService.searchGitLabRepo(q);
        for (int i = 0; i < Math.min(gitlabResults.length,10); i++){
            GitlabRepoDto gitlabResult= gitlabResults[i];
            results.add(new RepoSearchDto(gitlabResult.getForks_count(), gitlabResult.getStar_count(),"",""));
        }
        return results;
    }
}