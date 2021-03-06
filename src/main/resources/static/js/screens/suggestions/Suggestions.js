import React from 'react'
import UserInfo from '../../components/UserInfo'
import ButtonSuggestion from '../../components/ButtonSuggestion'

import api from '../../shared/api'

export default class Suggestions extends React.Component {

    constructor(props) {
        super(props);
        // initial state 
        this.state = {
            suggestions: []
        }
    }

    componentDidMount() {
        // for test
        const userId = 0
        api.get(`users/${userId}/suggestions`)
            .then(json => { this.setState({ suggestions: json }) })
            .catch(ex => { console.log('Error fetch', ex) })
    }

    renderSuggestions(user, index) {
        return (
            <div key={index} className="row user-suggestion">
                <UserInfo user={user} />
                <ButtonSuggestion onClickIgnore={this.handleClickIgnore(user.id)} />
            </div>
        )
    }

    handleClickIgnore(userIdSuggestion) {
        return () => {
            // For test
            const currentUserId = 0
            api.delete(`users/${currentUserId}/suggestions/${userIdSuggestion}`)
                .then(() => { this.setState({ suggestions: this.state.suggestions.filter(u => u.id != userIdSuggestion) }) })
                .catch(ex => { console.log('Error fetch', ex) })
        }
    }

    render() {
        const row = this.state.suggestions.map((u, index) => this.renderSuggestions(u, index))
        return (
            <div className="container">
                <h2>Find friends !</h2>
                {row}
            </div>
        )
    }
}