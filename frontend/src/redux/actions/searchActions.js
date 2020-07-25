export const REQUEST_SEARCH = "SEARCH_ACTIONS.REQUEST_SEARCH";
export function requestSearch(politicianName) {
  return {
    type: REQUEST_SEARCH,
    politicianName: politicianName
  };
}

export const RECEIVE_SEARCH = "SEARCH_ACTIONS.RECEIVE_SEARCH";
export function receiveSearch(response) {
  return {
    type: RECEIVE_SEARCH,
    response: response,
  };
}

export const FAILED_SEARCH = "SEARCH_ACTIONS.FAILED_SEARCH";
export function failedSearch(error) {
  return {
    type: FAILED_SEARCH,
    error: error,
  };
}

