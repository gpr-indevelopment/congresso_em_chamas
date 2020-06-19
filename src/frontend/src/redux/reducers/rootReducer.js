import searchReducer from "./searchReducer";

export default function rootReducer(state = {}, action){
    return {
        search: searchReducer(state.search, action)
    };
}