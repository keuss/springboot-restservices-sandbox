import React from 'react'
import UserInfo from '../../components/UserInfo'
import UserPost from '../../components/UserPost'

import api from '../../shared/api'


export default class Home extends React.Component {

    constructor(props) {
        super(props);
        // initial state 
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        api.get('users')
            .then(json => { this.setState({ users: json }) })
            .catch(ex => { console.log('Error fetch', ex) })
    }

    renderPost(user, index) {
        return (
            <div key={index} className="row user-post">
                <UserInfo user={user} />
                <UserPost />
            </div>
        )
    }

    render() {
        const rows = this.state.users.map((u, index) => this.renderPost(u, index))
        return (
            <div className="container">
                {rows}
            </div>
        )
    }
}