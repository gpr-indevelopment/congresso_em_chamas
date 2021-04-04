import * as EXPENSES_ACTIONS from "../actions/expensesActions";

const initialState = {
  loading: false,
  rawData: [],
  expenseData: {},
  detailsData: [],
  activeRadio: 1,
  jarbasReimbursements: new Map(),
  jarbasSuspicionsCount: 0,
};

export default function expensesReducer(state = initialState, action) {
  switch (action.type) {
    case EXPENSES_ACTIONS.REQUEST_EXPENSES:
      return Object.assign({}, state, {
        loading: true,
        detailsData: [],
      });
    case EXPENSES_ACTIONS.RECEIVE_EXPENSES:
      return Object.assign({}, state, {
        loading: false,
        rawData: action.response,
        expenseData: buildExpenseData(action.response),
      });
    case EXPENSES_ACTIONS.FAILED_EXPENSES:
      return Object.assign({}, state, {
        loading: false,
      });
    case EXPENSES_ACTIONS.SHOW_DETAILS:
      return Object.assign({}, state, {
        detailsData: state.expenseData.monthlyExpenses[action.index].expenses,
      });
    case EXPENSES_ACTIONS.RADIO_CHANGED:
      return Object.assign({}, state, {
        activeRadio: action.value,
      });
    case EXPENSES_ACTIONS.REQUEST_JARBAS_REIMBURSEMENT:
      return Object.assign({}, state, {
        jarbasReimbursements: new Map(state.jarbasReimbursements).set(
          action.documentCode,
          {
            loading: true,
          }
        ),
      });
    case EXPENSES_ACTIONS.RECEIVE_JARBAS_REIMBURSEMENT:
      return Object.assign({}, state, {
        jarbasReimbursements: new Map(state.jarbasReimbursements).set(
          action.documentCode,
          {
            loading: false,
            reimbursement: action.response,
          }
        ),
      });
    case EXPENSES_ACTIONS.FAILED_JARBAS_REIMBURSEMENT:
      return Object.assign({}, state, {
        jarbasReimbursements: new Map(state.jarbasReimbursements).set(
          action.documentCode,
          {
            loading: false,
            reimbursement: null,
          }
        ),
      });
    case EXPENSES_ACTIONS.RESET_JARBAS_REIMBURSEMENT:
      return Object.assign({}, state, {
        jarbasReimbursements: new Map(),
      });
    default:
      return Object.assign({}, state);
  }
}

let buildExpenseData = (expenses) => {
  let expenseData = {
    values: [],
    dates: [],
    monthlyExpenses: [],
  };
  expenses.sort((a, b) => {
    return (
      new Date(a.yearMonth.split("-")[0], a.yearMonth.split("-")[1]) -
      new Date(b.yearMonth.split("-")[0], b.yearMonth.split("-")[1])
    );
  });
  expenses.forEach((expense) => {
    let currentYear = expense.yearMonth.split("-")[0];
    let currentMonth = expense.yearMonth.split("-")[1];
    expenseData.values.push(expense.value.toFixed(2));
    expenseData.dates.push(`${currentMonth}/${currentYear}`);
    expenseData.monthlyExpenses.push(expense);
  });
  return expenseData;
};
