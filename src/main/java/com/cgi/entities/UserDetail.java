package com.cgi.entities;


public class UserDetail {

    private long postsNb;
    private long followersNb;
    private long subscribersNb;

    public UserDetail() {

    }

    public UserDetail(long postsNb, long followersNb, long subscribersNb) {
        this.postsNb = postsNb;
        this.followersNb = followersNb;
        this.subscribersNb = subscribersNb;
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

    @Override
    public String toString() {
        return "UserDetail{" +
                "postsNb=" + postsNb +
                ", followersNb=" + followersNb +
                ", subscribersNb=" + subscribersNb +
                '}';
    }
}
