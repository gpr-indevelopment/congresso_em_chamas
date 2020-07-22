import searchReducer from "./searchReducer";
import expensesReducer from "./expensesReducer";
import newsReducer from "./newsReducer";

export default function rootReducer(state = {}, action){
    return {
        search: searchReducer(state.search, action),
        expenses: expensesReducer(state.expenses, action),
        news: newsReducer(state.news, action)
    };
}