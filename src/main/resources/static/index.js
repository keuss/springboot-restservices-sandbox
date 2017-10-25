import React from 'react'
import ReactDOM from 'react-dom'
import { Router, Route, IndexRoute, hashHistory } from 'react-router'

import App from './js/App'
import Home from './js/screens/home/Home'
import Suggestions from './js/screens/suggestions/Suggestions'

const MOUNT_NODE = document.getElementById('root')

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={Home} />
      <Route path="home" component={Home} />
      <Route path="suggestions" component={Suggestions} />
    </Route>
  </Router>,
  MOUNT_NODE
)
