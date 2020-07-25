import { connect } from "react-redux";
import Propositions from "./Propositions";
import * as THUNK from "../../redux/thunk/propositionsThunk";

const mapStateToProps = (state) => {
  return state.propositions;
};

const mapDispatchToProps = (dispatch) => {
  return {
    handlePropositionsRequest: (politicianId) => dispatch(THUNK.requestPropositions(politicianId))
  };
};

const PropositionsContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(Propositions);
export default PropositionsContainer;
