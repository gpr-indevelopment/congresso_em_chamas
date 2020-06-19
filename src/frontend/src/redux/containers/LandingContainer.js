import { connect } from "react-redux";
import { searchSubmit } from "../actions/landingActions";
import Landing from "../../components/Landing/Landing";

const mapStateToProps = (state) => {
  return state.landing;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleSearchSubmit: (input) => dispatch(searchSubmit(input)),
  };
};

const LandingContainer = connect(mapStateToProps, mapDispatchToProps)(Landing);
export default LandingContainer;