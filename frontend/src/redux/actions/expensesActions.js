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

export const RADIO_CHANGED = "EXPENSES_ACTIONS.RADIO_CHANGED";
export function radioChanged(value) {
  return {
    type: RADIO_CHANGED,
    value: value
  }
}

export const REQUEST_JARBAS_REIMBURSEMENT = "EXPENSES_ACTIONS.REQUEST_JARBAS_REIMBURSEMENT";
export function requestJarbasReimbursement(documentCode) {
  return {
    type: REQUEST_JARBAS_REIMBURSEMENT,
    documentCode: documentCode
  };
}

export const RECEIVE_JARBAS_REIMBURSEMENT = "EXPENSES_ACTIONS.RECEIVE_JARBAS_REIMBURSEMENT";
export function receiveJarbasReimbursement(documentCode, response) {
  return {
    type: RECEIVE_JARBAS_REIMBURSEMENT,
    documentCode: documentCode,
    response: response
  };
}

export const FAILED_JARBAS_REIMBURSEMENT = "EXPENSES_ACTIONS.FAILED_JARBAS_REIMBURSEMENT";
export function failedJarbasReimbursement(documentCode, error) {
  return {
    type: FAILED_JARBAS_REIMBURSEMENT,
    documentCode: documentCode,
    error: error
  };
}

export const RESET_JARBAS_REIMBURSEMENT = "EXPENSES_ACTIONS.RESET_JARBAS_REIMBURSEMENT";
export function resetJarbasReimbursement() {
  return {
    type: RESET_JARBAS_REIMBURSEMENT,
  };
}