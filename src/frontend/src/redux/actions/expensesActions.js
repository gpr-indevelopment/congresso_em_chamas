export const REQUEST_EXPENSES = "EXPENSES_ACTIONS.REQUEST_EXPENSES";
export function requestExpenses() {
  return {
    type: REQUEST_EXPENSES,
  };
}

export const RECEIVE_EXPENSES = "EXPENSES_ACTIONS.RECEIVE_EXPENSES";
export function receiveExpenses(response) {
  return {
    type: RECEIVE_EXPENSES,
    response: response
  };
}

export const FAILED_EXPENSES = "EXPENSES_ACTIONS.FAILED_EXPENSES";
export function failedExpenses(error) {
  return {
    type: FAILED_EXPENSES,
    error: error
  };
}

export const SHOW_DETAILS = "EXPENSES_ACTIONS.SHOW_DETAILS";
export function showDetails(index){
  return {
    type: SHOW_DETAILS,
    index: index
  }
}
