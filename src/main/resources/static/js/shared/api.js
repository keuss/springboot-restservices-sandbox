class Api {

    constructor(basePath) {
        this.basePath = basePath
    }

    // Serialize a map of properties (as a JavaScript object) to a query string
    serializeQuery(paramsObj) {
        var k, params = []
        for (k in paramsObj) {
            if (paramsObj.hasOwnProperty(k) && paramsObj[k] !== undefined) {
                params.push(encodeURIComponent(k) + '=' + encodeURIComponent(paramsObj[k]))
            }
        }
        return params.join('&')
    }

    encodeUrl(path, queryParams) {
        return this.basePath + '/' + path +
            (queryParams ? '?' + this.serializeQuery(queryParams) : '')
    }

    filterSuccess(httpResponse) {
        const status = httpResponse.status
        return (status >= 200 && status < 300 || status === 304) ?
            Promise.resolve(httpResponse) :
            Promise.reject(httpResponse)
    }

    // Returns a Promise with the response.
    // To automatically send cookies for the current domain, the credentials option must be provided.
    // The "same-origin" value makes fetch behave similarly to XMLHttpRequest with regards to cookies.Otherwise, 
    // cookies won't get sent, resulting in these requests not preserving the authentication session.
    get(path, queryParams) {
        return fetch(this.encodeUrl(path, queryParams), { credentials: 'same-origin' })
            .then(this.filterSuccess)
            .then(response => response.json())
    }

    // delete must retourn nothing
    delete(path, queryParams) {
        return fetch(this.encodeUrl(path, queryParams), { method: 'delete', credentials: 'same-origin' })
            .then(this.filterSuccess)
    }

}

// Create instance
const api = new Api(`${window.location.origin }/api`)
export default api