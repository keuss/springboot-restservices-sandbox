package com.cgi.entities;


import java.util.ArrayList;
import java.util.List;

public class UserDetail {

    private Long postsNb;
    private Long followersNb;
    private Long followingNb;

    //FIXME : very bad to store with the UserDetail for performance, just here for example
    private List<Integer> suggestions;

    public UserDetail() {

    }

    public UserDetail(Long postsNb, Long followersNb, Long followingNb) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.followingNb = followingNb;
        this.suggestions = new ArrayList<>();
    }

    public UserDetail(Long postsNb, Long followersNb, Long followingNb, List<Integer> suggestions) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.followingNb = followingNb;
        this.suggestions = suggestions;
    }

    public Long getPostsNb() {
        return postsNb;
    }

    public void setPostsNb(Long postsNb) {
        this.postsNb = postsNb;
    }

    public Long getFollowersNb() {
        return followersNb;
    }

    public void setFollowersNb(Long followersNb) {
        this.followersNb = followersNb;
    }

    public Long getFollowingNb() {
        return followingNb;
    }

    public void setFollowingNb(Long followingNb) {
        this.followingNb = followingNb;
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
                ", followingNb=" + followingNb +
                ", suggestions=" + suggestions +
                '}';
    }
}
