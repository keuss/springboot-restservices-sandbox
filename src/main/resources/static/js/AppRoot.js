import React from 'react'
import { Link } from 'react-router'

// Taken from : https://github.com/davezuko/react-redux-starter-kit/blob/master/src/normalize.js
/* ========================================================
   ** Browser Normalizer **
   This file is responsible for normalizing the browser environment before
   the application starts. Doing this allows us to safely use modern language
   features even when the end user is running an older browser.
   The following polyfills are included by default:
   1) Object.assign
   2) Promise
   3) Fetch
   ====================================================== */

// 1) Object.assign
// ------------------------------------
// We can't rely on Object.assign being a function since it may be buggy, so
// defer to `object-assign`. If our Object.assign implementation is correct
// (determined by `object-assign` internally) the polyfill will be discarded
// and the native implementation used.
Object.assign = require('object-assign')

// 2) Promise
// ------------------------------------
if (typeof Promise === 'undefined') {
    //By default, promises silence any unhandled rejections.
    //You can enable logging of unhandled ReferenceErrors and TypeErrors via:require('promise/lib/rejection-tracking').enable()
    // Due to the performance cost, you should only do this during development.
    // Just a pure ES6 polyfill:
    window.Promise = require('promise/lib/es6-extensions.js')
    console.log("polyfill configuration done for Promise")
}

// 3) Fetch
// ------------------------------------
// Fetch polyfill depends on a Promise implementation, so it must come after
// the feature check / polyfill above.
if (typeof window.fetch === 'undefined') {
    require('whatwg-fetch') 
    // <=> import 'whatwg-fetch' Using Node.js require vs. ES6 import/export
    // Keep in mind that there is no JavaScript engine yet that natively supports ES6 modules. 
    // Babel converts import and export declaration to CommonJS (require/module.exports) by default. 
    // So even if you use ES6 module syntax, you will be using CommonJS under the hood if you run the code in Node.
    console.log("polyfill configuration done for window.fetch")
}

console.log("polyfill configuration end")

export default class AppRoot extends React.Component {
    render() {
        return (
            <div>
                <header>
                    <div className="menu-bar row">
                        <div className="column column-25">
                            <img className="round-profil" src={"assets/images/img_avatar2.png"} />
                        </div>
                        <div className="column column-75">
                            <h1>Java & Co Instagram</h1>
                        </div>
                    </div>
                    <Link to="/home">Home</Link>
                    <Link to="/suggestions">Suggestions</Link>
                </header>
                <main>{this.props.children}</main>
            </div>
        )
    }
}