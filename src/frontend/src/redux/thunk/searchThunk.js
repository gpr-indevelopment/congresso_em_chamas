import * as SEARCH_ACTIONS from "../actions/searchActions";

const baseUrl = "http://localhost:8080/"

export function requestSearch(politicianName) {
    return (dispatch) => {
        dispatch(SEARCH_ACTIONS.requestSearch(politicianName));
        fetch(`${baseUrl}/profiles?name=${politicianName}`).then((response) => {
            if(response.ok){
                return response.json();
            } else {
                dispatch(SEARCH_ACTIONS.failedSearch(response))
            }
        })
        .then((body) => dispatch(SEARCH_ACTIONS.receiveSearch(body)))
        .catch((error) => {
            dispatch(SEARCH_ACTIONS.failedSearch(error))
        })
    }
}