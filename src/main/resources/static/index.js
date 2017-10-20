import React from 'react'
import ReactDOM from 'react-dom'

const MOUNT_NODE = document.getElementById('root')


var UserTab = (props) => {
  return (
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Height</th>
            <th>Location</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{props.name}</td>
            <td>27</td>
            <td>1,91</td>
            <td>Akron, OH</td>
          </tr>
          <tr>
            <td>Klay Thompson</td>
            <td>25</td>
            <td>2,01</td>
            <td>Los Angeles, CA</td>
          </tr>
        </tbody>
      </table>
  )
}

// A functional component using an ES2015 (ES6) arrow function
var Welcome = (props) => {
  return (
          <div>
            <h2>Hello, world from {props.name} !</h2>
            <UserTab name={props.name} />
            <a className="button" href="#">Default Button</a>
            <button className="button button-outline">Outlined Button</button>
            <input className="button button-clear" type="submit" value="Clear Button"/>
          </div>  
         )
}

const element = <Welcome name="Keuss" />

ReactDOM.render(
  element,
  MOUNT_NODE
)
