import { connect } from "react-redux";
import * as CongressoComponents from "../../components/index";
import * as THUNK from "../thunk/newsThunk"

const mapStateToProps = (state) => {
  return state.news;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleExpensesRequest: (politicianId) => dispatch(THUNK.requestNews(politicianId))
  };
};

const NewsContainer = connect(mapStateToProps, mapDispatchToProps)(CongressoComponents.News);
export default NewsContainer;