import React from 'react'
import { Link } from 'react-router'

export default class UserInfo extends React.Component {
    
    render() {
        const profileLink = `/profile/${this.props.user.id}`
        const avatard = this.props.user && this.props.user.avatar ? this.props.user.avatar : 'assets/images/img_avatar1.png'
        return (
            <div className="column column-20">
                <Link to={profileLink}>
                    <img className="round" src={avatard} />
                    <span>{this.props.user.name}</span>
                    <br/>
                    <span>{this.props.user.email}</span>
                </Link>
            </div>
        )
    }
}

