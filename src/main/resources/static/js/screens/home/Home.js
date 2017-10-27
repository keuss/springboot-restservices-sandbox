import React from 'react'
import UserInfo from '../../components/UserInfo'
import UserPost from '../../components/UserPost'


export default class Home extends React.Component {

    constructor(props) {
        super(props);
        // initial state 
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        fetch(`${window.location.origin}/api/users`)
            .then(response => response.json())
            .then(json => { this.setState({ users: json }) })
            .catch(ex => { console.log('parsing failed', ex) })
    }

    renderPost(user) {
        return (
            <div key={user.id} className="row user-post">
                <UserInfo user={ user } />
                <UserPost />
            </div>
        )
    }

    render() {
        return (
            <div className="container">
                { this.state.users.map(u => this.renderPost(u)) }
            </div>
        )
    }
}