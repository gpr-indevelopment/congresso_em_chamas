import * as NEWS_ACTIONS from "../actions/newsActions";
import { config } from "../../constants";

export function requestNews(politicianId) {
    return (dispatch) => {
        dispatch(NEWS_ACTIONS.requestNews());
        fetch(`${config.url}/politicians/${politicianId}/news`).then((response) => {
            if(response.ok){
                return response.json();
            } else {
                dispatch(NEWS_ACTIONS.failedNews(response))
            }
        })
        .then((body) => dispatch(NEWS_ACTIONS.receiveNews(body)))
        .catch((error) => {
            dispatch(NEWS_ACTIONS.failedNews(error))
        })
    }
}