package springframework.guru.webclientdemo.service;

import springframework.guru.webclientdemo.domain.GithubSearchResponse;
import springframework.guru.webclientdemo.domain.GitLabSearchResponse;
import springframework.guru.webclientdemo.domain.Repo;

public interface RepoClientEventService {
    public GithubSearchResponse getGithubRepo(String q);
    public GitLabSearchResponse[] getGitLabRepo(String q);
    public Repo[] getRepo(String q);
}

