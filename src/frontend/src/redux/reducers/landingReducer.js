import * as LANDING_ACTIONS from "../actions/landingActions";
import history from "../../history";

export default function landingReducer(state = {}, action) {
  switch (action.type) {
    case LANDING_ACTIONS.SEARCH_SUBMIT:
      history.push({
        pathname: "/search",
        search: "?politicianName=" + action.input,
      });
      return Object.assign({}, state);
    default:
      return Object.assign({}, state);
  }
}
