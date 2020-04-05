package springframework.guru.repoSearchEngine.dto.bitbucket;

import com.fasterxml.jackson.annotation.JsonProperty;

class Html{
    @JsonProperty("href")
    private String Profile_url;

    public String getProfile_url() {
        return Profile_url;
    }

    public void setProfile_url(String profile_url) {
        Profile_url = profile_url;
    }
}

class Avatar{
    @JsonProperty("href")
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}

class Links {
    @JsonProperty("html")
    private Html html;
    @JsonProperty("avatar")
    private Avatar avatar;

    public Html getHtml() {
        return html;
    }

    public void setHtml(Html html) {
        this.html = html;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}

public class BitbucketOwner {
    @JsonProperty("display_name")
    private String owner_name;
    @JsonProperty("links")
    private Links links;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getAvator() {
        return this.links.getAvatar().getAvatar_url();
    }

    public String getProfile() {
        return this.links.getHtml().getProfile_url();
    }
}
