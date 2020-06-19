import * as SEARCH_ACTIONS from "../actions/searchActions";
import history from "../../history";

const initialState = {
  politicianName: null
}

export default function landingReducer(state = initialState, action) {
  switch (action.type) {
    case SEARCH_ACTIONS.SEARCH_SUBMIT:
      history.push({
        pathname: "/search",
      });
      return Object.assign({}, state, {
        politicianName: action.input
      });
    default:
      return Object.assign({}, state);
  }
}
