import React from 'react'
import ReactDOM from 'react-dom'
import { Router, Route, IndexRoute, hashHistory } from 'react-router'

import AppRoot from './js/AppRoot'
import Home from './js/screens/home/Home'
import Suggestions from './js/screens/suggestions/Suggestions'
import Profile from './js/screens/profile/Profile'

const MOUNT_NODE = document.getElementById('root')

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={AppRoot}>
      <IndexRoute component={Home} />
      <Route path="home" component={Home} />
      <Route path="suggestions" component={Suggestions} />
      <Route path="profile/:userId" component={Profile} />
    </Route>
  </Router>,
  MOUNT_NODE
)
