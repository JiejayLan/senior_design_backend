package springframework.guru.repoSearchEngine.service.bitbucketApiService;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketCommitsDto;
import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketRepoDto;

import java.util.ArrayList;

public interface BitbucketApiService {
    BitbucketRepoDto acquireSingleRepo(String path);
    ArrayList<String> getRepoCommits(String path, int page);
    ArrayList<String> extractDateString(BitbucketCommitsDto bitbucketCommitsDto);
}
