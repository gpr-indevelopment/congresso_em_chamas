import * as PROPOSITIONS_ACTIONS from "../actions/propositionsActions";

export default function propositionsReducer(state = {}, action) {
  switch (action.type) {
    case PROPOSITIONS_ACTIONS.REQUEST_PROPOSITIONS:
      return Object.assign({}, state);
    case PROPOSITIONS_ACTIONS.RECEIVE_PROPOSITIONS:
        console.log(action.response)
      return Object.assign({}, state);
    case PROPOSITIONS_ACTIONS.FAILED_PROPOSITIONS:
      return Object.assign({}, state);
    default:
      return Object.assign({}, state);
  }
}
