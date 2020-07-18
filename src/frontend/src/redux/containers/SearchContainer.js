import { connect } from "react-redux";
import * as CongressoComponents from "../../components/index";
import * as THUNK from "../thunk/searchThunk";

const mapStateToProps = (state) => {
  return state.search;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleSearchRequest: (input) => dispatch(THUNK.requestSearch(input)),
  };
};

const SearchContainer = connect(mapStateToProps, mapDispatchToProps)(CongressoComponents.Search);
export default SearchContainer;
