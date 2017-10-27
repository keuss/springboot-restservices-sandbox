import React from 'react'

export default class UserDetail extends React.Component {
    render() {
        return (
            <div className="column column-80">
                <p className="user-stats">
                    <span>{this.props.userDetail.postsNb} posts</span>
                    <span>{this.props.userDetail.followersNb} followers</span>
                    <span>{this.props.userDetail.followingNb} following</span>
                </p>
            </div>
        )
    }
}