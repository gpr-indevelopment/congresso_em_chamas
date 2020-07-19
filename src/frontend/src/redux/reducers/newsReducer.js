import * as NEWS_ACTIONS from "../actions/newsActions";

const initialState = {
  loading: false,
  data: []
};

export default function newsReducer(state = initialState, action) {
  switch (action.type) {
    case NEWS_ACTIONS.REQUEST_NEWS:
      return Object.assign({}, state, {
        loading: true,
      });
    case NEWS_ACTIONS.RECEIVE_NEWS:
        console.log(action.response);
      return Object.assign({}, state, {
          data: action.response,
          loading: false
      });
    case NEWS_ACTIONS.FAILED_NEWS:
      console.log(action.error);
      return Object.assign({}, state, {
          loading: false
      });
    default:
      return Object.assign({}, state);
  }
}
