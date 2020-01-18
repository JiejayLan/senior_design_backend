package springframework.guru.webclientdemo.service;

import org.springframework.http.ResponseEntity;
import springframework.guru.webclientdemo.domain.Github;
import springframework.guru.webclientdemo.domain.GitLab;

public interface RepoClientEventService {
    public ResponseEntity<Github> getGithubRepo(String q);
    public ResponseEntity<GitLab[]> getGitLabRepo(String q);
}

