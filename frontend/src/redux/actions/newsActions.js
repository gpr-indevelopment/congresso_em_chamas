export const REQUEST_NEWS = "NEWS_ACTIONS.REQUEST_NEWS";
export function requestNews() {
  return {
    type: REQUEST_NEWS,
  };
}

export const RECEIVE_NEWS = "NEWS_ACTIONS.RECEIVE_NEWS";
export function receiveNews(response) {
  return {
    type: RECEIVE_NEWS,
    response: response
  };
}

export const FAILED_NEWS = "NEWS_ACTIONS.FAILED_NEWS";
export function failedNews(error) {
  return {
    type: FAILED_NEWS,
    error: error
  };
}

