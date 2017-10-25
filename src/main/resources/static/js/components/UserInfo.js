import React from 'react'

export default class UserInfo extends React.Component {
    render() {
        return (
            <div className="column column-20">
                <img className="round" src={this.props.user.avatar} />
                <span>{this.props.user.name}</span>
            </div>
        )
    }
}

