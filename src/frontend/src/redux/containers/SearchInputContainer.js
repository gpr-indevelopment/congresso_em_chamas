import { connect } from "react-redux";
import { searchSubmit } from "../actions/searchActions";
import SearchInput from "../../components/SearchInput/SearchInput"

const mapStateToProps = (state) => {
  return state.search;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleSearchSubmit: (input) => dispatch(searchSubmit(input)),
  };
};

const SearchInputContainer = connect(mapStateToProps, mapDispatchToProps)(SearchInput);
export default SearchInputContainer;