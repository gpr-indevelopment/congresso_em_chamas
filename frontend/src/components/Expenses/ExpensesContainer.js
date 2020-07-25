import { connect } from "react-redux";
import * as THUNK from "../../redux/thunk/expensesThunk";
import Expenses from "./Expenses";
import * as EXPENSES_ACTIONS from "../../redux/actions/expensesActions";

const mapStateToProps = (state) => {
  return state.expenses;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleExpensesRequest: (politicianId) =>
      dispatch(THUNK.requestExpenses(politicianId)),
    handleDetailsClick: (index) =>
      dispatch(EXPENSES_ACTIONS.showDetails(index)),
  };
};

const ExpensesContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(Expenses);
export default ExpensesContainer;
