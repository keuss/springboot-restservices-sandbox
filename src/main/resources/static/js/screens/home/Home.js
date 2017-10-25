import React from 'react'

export default class Home extends React.Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="column column-10">
                        <img className="round" src={"assets/images/img_avatar.png"} />
                    </div>
                    <div className="column column-90">
                        <blockquote>Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...</blockquote>
                    </div>
                </div>
                <div className="row">
                    <div className="column column-10">
                        <img className="round" src={"assets/images/img_avatar2.png"} />
                    </div>
                    <div className="column column-90">
                        <blockquote>Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...</blockquote>
                    </div>
                </div>
            </div>
        )
    }
}