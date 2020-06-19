import landingReducer from "./landingReducer";

export default function rootReducer(state = {}, action){
    return {
        landing: landingReducer(state.landing, action)
    };
}