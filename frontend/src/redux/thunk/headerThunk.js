import * as HEADER_ACTIONS from "../actions/headerActions";
import { config } from "../../constants";

export function requestProfile(politicianId) {
  return (dispatch) => {
    dispatch(HEADER_ACTIONS.requestProfile());
    fetch(`${config.url}/politicians/${politicianId}`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          dispatch(HEADER_ACTIONS.failedProfile(response));
        }
      })
      .then((body) => dispatch(HEADER_ACTIONS.receiveProfile(body)))
      .catch((error) => {
        dispatch(HEADER_ACTIONS.failedProfile(error));
      });
  };
}
