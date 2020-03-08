package springframework.guru.repoSearchEngine.service.repoDetailService;
import org.springframework.stereotype.Service;
import springframework.guru.repoSearchEngine.service.repoSearchService.RepoSearchService;

@Service
public class RepoDetailServiceImpl implements RepoDetailService{

    @Override
    public String acquireRepoDetail(String platform, String full_name){
        return "OK";
    }
}



