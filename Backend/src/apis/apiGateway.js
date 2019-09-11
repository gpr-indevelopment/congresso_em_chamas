const camaraAPI = require('../apis/camaraApi.js');
const twitterAPI = require('../apis/twitterApi.js');

module.exports = {
    async getTwitterAccountByName(politician){
        return await twitterAPI.getTwitterAccountByName(politician);
    },

    async getPoliticiansFromCamara(inputName){
        return await camaraAPI.getPoliticiansFromCamara(inputName);
    }
}