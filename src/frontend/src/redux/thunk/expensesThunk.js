import * as EXPENSES_ACTIONS from "../actions/expensesActions";

const baseUrl = "http://localhost:8080/"

export function requestExpenses(politicianId) {
    return (dispatch) => {
        dispatch(EXPENSES_ACTIONS.requestExpenses());
        fetch(`${baseUrl}politicians/${politicianId}/monthlyexpenses`).then((response) => {
            if(response.ok){
                return response.json();
            } else {
                dispatch(EXPENSES_ACTIONS.failedExpenses(response))
            }
        })
        .then((body) => dispatch(EXPENSES_ACTIONS.receiveExpenses(body)))
        .catch((error) => {
            dispatch(EXPENSES_ACTIONS.failedExpenses(error))
        })
    }
}