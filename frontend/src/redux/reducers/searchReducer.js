import * as SEARCH_ACTIONS from "../actions/searchActions";
import history from "../../history";

const initialState = {
  profiles: [],
  loading: false
}

export default function landingReducer(state = initialState, action) {
  switch (action.type) {
    case SEARCH_ACTIONS.REQUEST_SEARCH: 

      var search = `/search?`;
      search = action.politicianName !== '' ? search + `&politicianName=${action.politicianName}` : search;
      search = action.stateInitials !== '' ? search + `&uf=${action.stateInitials}` : search;
      history.push(search);
      return Object.assign({}, state, {
        loading: true
      });
    case SEARCH_ACTIONS.RECEIVE_SEARCH:
      return Object.assign({}, state, {
        loading: false,
        profiles: action.response
      });
    case SEARCH_ACTIONS.FAILED_SEARCH:
      return Object.assign({}, state, {
        loading: false
      });
    default:
      return Object.assign({}, state);
  }
}
