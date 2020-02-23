package springframework.guru.repoSearchEngine.service.bitbucketApiService;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;

public interface BitbucketApiService {
    BitbucketRepoDto acquireSingleRepo(String link);
}
