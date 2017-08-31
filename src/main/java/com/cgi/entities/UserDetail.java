package com.cgi.entities;


import java.util.ArrayList;
import java.util.List;

public class UserDetail {

    private long postsNb;
    private long followersNb;
    private long subscribersNb;

    //FIXME : very bad to store with the UserDetail for performance, just here for example
    private List<Integer> suggestions;

    public UserDetail() {

    }

    public UserDetail(long postsNb, long followersNb, long subscribersNb) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.subscribersNb = subscribersNb;
        this.suggestions = new ArrayList<>();
    }

    public UserDetail(long postsNb, long followersNb, long subscribersNb, List<Integer> suggestions) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.subscribersNb = subscribersNb;
        this.suggestions = suggestions;
    }

    public long getPostsNb() {
        return postsNb;
    }

    public void setPostsNb(long postsNb) {
        this.postsNb = postsNb;
    }

    public long getFollowersNb() {
        return followersNb;
    }

    public void setFollowersNb(long followersNb) {
        this.followersNb = followersNb;
    }

    public long getSubscribersNb() {
        return subscribersNb;
    }

    public void setSubscribersNb(long subscribersNb) {
        this.subscribersNb = subscribersNb;
    }

    public List<Integer> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Integer> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "postsNb=" + postsNb +
                ", followersNb=" + followersNb +
                ", subscribersNb=" + subscribersNb +
                ", suggestions=" + suggestions +
                '}';
    }
}
