import { connect } from "react-redux";
import { Search } from "../";
import * as THUNK from "../../redux/thunk/searchThunk";

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
