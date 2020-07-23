import * as PROPOSITIONS_ACTIONS from "../actions/propositionsActions";
import { config } from "../../constants";

export function requestPropositions(politicianId) {
  return (dispatch) => {
    dispatch(PROPOSITIONS_ACTIONS.requestPropositions());
    fetch(`${config.url}/politicians/${politicianId}/propositions`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          dispatch(PROPOSITIONS_ACTIONS.failedPropositions(response));
        }
      })
      .then((body) => dispatch(PROPOSITIONS_ACTIONS.receivePropositions(body)))
      .catch((error) => {
        dispatch(PROPOSITIONS_ACTIONS.failedPropositions(error));
      });
  };
}
