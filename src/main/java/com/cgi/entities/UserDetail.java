package com.cgi.entities;


import java.util.ArrayList;
import java.util.List;

public class UserDetail {

    private long postsNb;
    private long followersNb;
    private long subscribersNb;

    //FIXME : very bad to store with the UserDetail for performance, just here for example
    private List<Integer> followers;
    private List<Integer> subscribers;

    public UserDetail() {

    }

    public UserDetail(long postsNb, long followersNb, long subscribersNb) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.subscribersNb = subscribersNb;
        this.followers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public UserDetail(long postsNb, long followersNb, long subscribersNb, List<Integer> followers, List<Integer> subscribers) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.subscribersNb = subscribersNb;
        this.followers = followers;
        this.subscribers = subscribers;
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

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Integer> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "postsNb=" + postsNb +
                ", followersNb=" + followersNb +
                ", subscribersNb=" + subscribersNb +
                ", followers=" + followers +
                ", subscribers=" + subscribers +
                '}';
    }
}
