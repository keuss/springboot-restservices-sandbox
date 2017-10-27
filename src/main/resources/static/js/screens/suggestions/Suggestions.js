import React from 'react'
import UserInfo from '../../components/UserInfo'

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
        fetch(`${window.location.origin}/api/users/${userId}/suggestions`)
            .then(response => response.json())
            .then(json => { this.setState({ suggestions: json }) })
            .catch(ex => { console.log('parsing failed', ex) })
    }

    renderSuggestions(user) {
        return (
            <div key={user.id} className="row user-suggestion">
                <UserInfo user={user} />
                <div className="column column-80">
                    <button className="button button-outline">Follow</button>
                    <button className="button button-outline" onClick={this.onClickIgnore(user.id)}>Ignore</button>
                </div>
            </div>
        )
    }

    onClickIgnore(userIdSuggestion) {
        return () => {
            // For test
            const currentUserId = 0
            fetch(`${window.location.origin}/api/users/${currentUserId}/suggestions/${userIdSuggestion}`, { method: 'delete' })
                .then(response => {
                    if(response.ok)
                        this.setState({ suggestions: this.state.suggestions.filter(u => u.id != userIdSuggestion) })
                    else
                        alert("Erreur du serveur ...")
                })
                .catch(ex => { console.log('fetch failed', ex) })
        }
    }

    render() {
        return (
            <div className="container">
                <h2>Find friends !</h2>
                {this.state.suggestions.map(u => this.renderSuggestions(u))}
            </div>
        )
    }
}