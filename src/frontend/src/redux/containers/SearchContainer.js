import { connect } from "react-redux";
import Search from "../../components/Search/Search";
import * as THUNK from "../thunk/searchThunk";

const mapStateToProps = (state) => {
  return state.search;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleSearchRequest: (input) => dispatch(THUNK.requestSearch(input)),
  };
};

const SearchContainer = connect(mapStateToProps, mapDispatchToProps)(Search);
export default SearchContainer;
