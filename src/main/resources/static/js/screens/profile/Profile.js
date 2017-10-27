import React from 'react'
import UserInfo from '../../components/UserInfo'
import UserDetail from '../../components/UserDetail'


export default class Profile extends React.Component {

    constructor(props) {
        super(props);
        // initial state 
        this.state = { 
            user: { },
            userDetail: { }
        }
    }

    componentDidMount() {
        // from the path `profile/:userId`
        const userId = this.props.params.userId
        fetch(`${window.location.origin}/api/users/${userId}`)
            .then(response => response.json())
            .then(json => { this.setState({ user: json }) })
            .catch(ex => { console.log('parsing failed', ex) })

        fetch(`${window.location.origin}/api/users/${userId}/detail`)
            .then(response => response.json())
            .then(json => { this.setState({ userDetail: json }) })
            .catch(ex => { console.log('parsing failed', ex) })
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <UserInfo user={ this.state.user } />
                    <UserDetail userDetail={ this.state.userDetail } />
                </div>
            </div>
        )
    }
}