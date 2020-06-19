export const SEARCH_SUBMIT = "SEARCH_ACTIONS.SEARCH_SUBMIT";
export function searchSubmit(input) {
    return {
        type: SEARCH_SUBMIT,
        input: input
    }
}