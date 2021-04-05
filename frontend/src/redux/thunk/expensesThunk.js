import * as EXPENSES_ACTIONS from "../actions/expensesActions";
import { config } from "../../constants";

export function requestExpenses(politicianId, query) {
  return (dispatch) => {
    dispatch(EXPENSES_ACTIONS.requestExpenses());
    let url = `${config.url}/politicians/${politicianId}/monthlyexpenses`;
    let search = new URLSearchParams(query).toString();
    fetch(`${url}?${search}`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          dispatch(EXPENSES_ACTIONS.failedExpenses(response));
        }
      })
      .then((body) => dispatch(EXPENSES_ACTIONS.receiveExpenses(body)))
      .catch((error) => {
        dispatch(EXPENSES_ACTIONS.failedExpenses(error));
      });
  };
}
