export const REQUEST_PROPOSITIONS = "PROPOSITIONS_ACTIONS.REQUEST_PROPOSITIONS";
export function requestPropositions() {
  return {
    type: REQUEST_PROPOSITIONS,
  };
}

export const RECEIVE_PROPOSITIONS = "PROPOSITIONS_ACTIONS.RECEIVE_PROPOSITIONS";
export function receivePropositions(response) {
  return {
    type: RECEIVE_PROPOSITIONS,
    response: response,
  };
}

export const FAILED_PROPOSITIONS = "PROPOSITIONS_ACTIONS.FAILED_PROPOSITIONS";
export function failedPropositions(error) {
  return {
    type: FAILED_PROPOSITIONS,
    error: error,
  };
}
