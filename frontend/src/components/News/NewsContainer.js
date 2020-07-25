import { connect } from "react-redux";
import News from "./News";
import * as THUNK from "../../redux/thunk/newsThunk";

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
