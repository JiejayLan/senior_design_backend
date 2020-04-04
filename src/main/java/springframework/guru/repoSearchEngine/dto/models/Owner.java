package springframework.guru.repoSearchEngine.dto.models;

import springframework.guru.repoSearchEngine.dto.bitbucket.BitbucketOwner;

public class Owner {
    private String owner_name;
    private String avatar_url;
    private String profile_url;

    public Owner(BitbucketOwner bitbucketOwner){
        this.owner_name = bitbucketOwner.getOwner_name();
        this.avatar_url = bitbucketOwner.getAvator();
        this.profile_url = bitbucketOwner.getProfile();
    }

    public Owner(String owner_name, String avatar_url, String profile_url) {
        owner_name = owner_name;
        this.avatar_url = avatar_url;
        this.profile_url = profile_url;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        owner_name = owner_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }
}
