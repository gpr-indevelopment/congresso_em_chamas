const apiGateway = require("../apis/apiGateway.js");
const dao = require("../dao/dao.js");

module.exports = {
    async processPoliticianFromInput(userInput) {
        var response = [];
        const camaraResponse = await getPoliticiansFromCamara(userInput);
        for (var politician of camaraResponse) {
            var politicianInDatabase = await politicianExistsInDatabase(politician);
            if(politicianInDatabase){
                response.push(politicianInDatabase);
            }
            else{
                await completePoliticianInfo(politician);
                await savePolitician(politician);
                response.push(politician);
            }
        }
        return response;
    }
}

async function getPoliticiansFromCamara(input) {
    return await apiGateway.getPoliticiansFromCamara(input);
}

async function politicianExistsInDatabase(politician) {
    const {name} = politician;
    return await dao.findPoliticianByName(name);
}

async function completePoliticianInfo(politician) {
    const {name} = politician;
    const twitterUsername = await apiGateway.getTwitterAccountByName(name);
    Object.assign(politician, {twitterUsername: twitterUsername});
}

async function savePolitician(politician) {
    await dao.savePolitician(politician);
}