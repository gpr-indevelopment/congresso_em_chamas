import searchReducer from "./searchReducer";
import landingReducer from "./landingReducer";

export default function rootReducer(state = {}, action){
    return {
        search: searchReducer(state.search, action),
        landing: landingReducer(state.landing, action)
    };
}