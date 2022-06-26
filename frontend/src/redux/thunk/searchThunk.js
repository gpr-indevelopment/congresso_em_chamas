import * as SEARCH_ACTIONS from "../actions/searchActions";
import { config } from "../../constants";

export function requestSearch(name, state) {
    return (dispatch) => {
        dispatch(SEARCH_ACTIONS.requestSearch(name, state));
        fetch(`${config.url}/profiles?name=${name}&stateInitials=${state}`).then((response) => {
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