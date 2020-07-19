import { connect } from "react-redux";
import { News } from "../../components/index";
import * as THUNK from "../thunk/newsThunk";

const mapStateToProps = (state) => {
  return state.news;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleExpensesRequest: (politicianId) =>
      dispatch(THUNK.requestNews(politicianId)),
  };
};

const NewsContainer = connect(mapStateToProps, mapDispatchToProps)(News);
export default NewsContainer;
