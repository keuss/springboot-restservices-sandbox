import React from 'react'
import UserInfo from '../../components/UserInfo'
import UserDetail from '../../components/UserDetail'

import api from '../../shared/api'


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
        api.get(`users/${userId}`)
            .then(json => { this.setState({ user: json }) })
            .catch(ex => { console.log('Error fetch', ex) })

        api.get(`users/${userId}/detail`)
            .then(json => { this.setState({ userDetail: json }) })
            .catch(ex => { console.log('Error fetch', ex) })
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