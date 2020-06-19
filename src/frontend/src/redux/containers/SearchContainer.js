import { connect } from "react-redux";
import { searchSubmit } from "../actions/searchActions";
import Search from "../../components/Search/Search";

const mapStateToProps = (state) => {
  return state.search;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleSearchSubmit: (input) => dispatch(searchSubmit(input)),
  };
};

const SearchContainer = connect(mapStateToProps, mapDispatchToProps)(Search);
export default SearchContainer;
