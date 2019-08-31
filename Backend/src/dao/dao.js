const Politician = require('./politician.js');

module.exports = {

    async politicianExists(politician){
        const {name} = politician;
        const foundPolitician = await Politician.findOne({name: name});
        return foundPolitician;
    },

    async savePolitician(politician){
        const response = await Politician.create(politician);
        return response;
    }
}