const politicianProcessor = require("../processor/politicianProcessor.js");

//TODO: Improve organization and separation fo responsibilities. Make business logic clearer.
module.exports = {
    async getPolitician(req, res) {
        const inputName = req.headers.politician;
        return res.json(await politicianProcessor.processPoliticianFromInput(inputName));
    },
}


