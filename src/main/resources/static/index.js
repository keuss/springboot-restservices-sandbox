import React from 'react'
import ReactDOM from 'react-dom'

const MOUNT_NODE = document.getElementById('root')

// A functional component using an ES2015 (ES6) arrow function
var Welcome = (props) => {
  return <h2>Hello, world from {props.name} !</h2>
}

const element = <Welcome name="Keuss" />

ReactDOM.render(
  element,
  MOUNT_NODE
)
