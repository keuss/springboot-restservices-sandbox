import React from 'react'

export default class Suggestions extends React.Component {

    componentDidMount() {
        console.log('Suggestions did mount !')
        fetch(window.location.origin + '/api/users')
            .then(function (response) {
                console.log('response', response)
                return response.json()
            }).then(function (json) {
                console.log('parsed json', json)
            }).catch(function (ex) {
                console.log('parsing failed', ex)
            })
    }

    render() {
        return (
            <div>
                <h1>Suggestions</h1>
            </div>
        )
    }
}