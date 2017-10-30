import React from 'react'

export default class ButtonSuggestion extends React.Component {
    render() {
        return (
            <div className="column column-80">
                <button className="button button-outline">Follow</button>
                <button className="button button-outline" onClick={this.props.handlerClickIgnore}>Ignore</button>
            </div>
        )
    }
}