import React from 'react'
import UserInfo from '../../components/UserInfo'
import UserPost from '../../components/UserPost'


export default class Home extends React.Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <UserInfo user={{ name: 'Marc M.', avatar: 'assets/images/img_avatar.png'}}/>
                    <UserPost />
                </div>
                <div className="row">
                    <UserInfo user={{ name: 'Steve D.', avatar: 'assets/images/img_avatar2.png' }} />
                    <UserPost />
                </div>
                <div className="row">
                    <UserInfo user={{ name: 'Lucy S.', avatar: 'assets/images/img_avatar2.png' }} />
                    <UserPost />
                </div>
                <div className="row">
                    <UserInfo user={{ name: 'Dave G.', avatar: 'assets/images/img_avatar.png' }} />
                    <UserPost />
                </div>
            </div>
        )
    }
}