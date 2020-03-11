const Politician = require('./politician.js');

module.exports = {

    async findPoliticianByName(politician){
        const {name} = politician;
        return await Politician.findOne({name: name});
    },

    async savePolitician(politician){
        return await Politician.create(politician);
    }
}