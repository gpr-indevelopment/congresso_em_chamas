import * as PROPOSITIONS_ACTIONS from "../actions/propositionsActions";

const initialState = {
  propositions: [],
  loading: false
};

export default function propositionsReducer(state = initialState, action) {
  switch (action.type) {
    case PROPOSITIONS_ACTIONS.REQUEST_PROPOSITIONS:
      return Object.assign({}, state, {
        loading: true
      });
    case PROPOSITIONS_ACTIONS.RECEIVE_PROPOSITIONS:
      return Object.assign({}, state, {
        propositions: action.response,
        loading: false
      });
    case PROPOSITIONS_ACTIONS.FAILED_PROPOSITIONS:
      return Object.assign({}, state, {
        loading: false
      });
    default:
      return Object.assign({}, state);
  }
}
