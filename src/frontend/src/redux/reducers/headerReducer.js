import * as HEADER_ACTIONS from "../actions/headerActions";

const initialState = {
  profile: {},
};

export default function headerReducer(state = initialState, action) {
  switch (action.type) {
    case HEADER_ACTIONS.REQUEST_PROFILE:
      return Object.assign({}, state);
    case HEADER_ACTIONS.RECEIVE_PROFILE:
      return Object.assign({}, state, {
        profile: action.response,
      });
    case HEADER_ACTIONS.FAILED_PROFILE:
      return Object.assign({}, state);
    default:
      return Object.assign({}, state);
  }
}
