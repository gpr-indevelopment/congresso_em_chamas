import { connect } from "react-redux";
import Header from "./Header";
import * as THUNK from "../../redux/thunk/headerThunk";

const mapStateToProps = (state) => {
  return state.header;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleProfileLoading: (politicianId) => dispatch(THUNK.requestProfile(politicianId))
  };
};

const HeaderContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(Header);
export default HeaderContainer;
