const getPoliticiansFromCamara = require('../apis/camaraApi.js');
const searchTwitterUser = require('../apis/twitterApi.js');
const { politicianExists, savePolitician } = require('../dao/dao.js');

//TODO: Improve organization and separation fo responsibilities. Make business logic clearer.
module.exports = {
    async getPolitician(req, res) {
        const { politician } = req.headers;
        const response = await getPoliticiansFromCamara(politician);
        var responseArray = [];
        for (var politicianFromCamara of response) {
            const foundPolitician = await politicianExists(politicianFromCamara);
            if (foundPolitician) {
                responseArray.push(foundPolitician);
            }
            else {
                const completedPolitician = await appendTwitterUserInfo(politicianFromCamara);
                const savedPolitician = await savePolitician(completedPolitician);
                responseArray.push(savedPolitician);
            }
        }
        return res.json(responseArray);
    },
}

async function appendTwitterUserInfo(politician) {
    const { name, picture, partyInitials, id } = politician;
    const twitterUsername = await searchTwitterUser(name);
    const completedPolitician = {
        name: name,
        camaraId: id,
        picture: picture,
        partyInitials: partyInitials,
        twitterUsername: twitterUsername
    }
    return completedPolitician;
}


