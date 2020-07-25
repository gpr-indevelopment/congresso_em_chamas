export const REQUEST_PROFILE = "HEADER_ACTIONS.REQUEST_PROFILE";
export function requestProfile() {
  return {
    type: REQUEST_PROFILE,
  };
}

export const RECEIVE_PROFILE = "HEADER_ACTIONS.RECEIVE_PROFILE";
export function receiveProfile(response) {
  return {
    type: RECEIVE_PROFILE,
    response: response
  };
}

export const FAILED_PROFILE = "HEADER_ACTIONS.FAILED_PROFILE";
export function failedProfile(error) {
  return {
    type: FAILED_PROFILE,
    error: error
  };
}

