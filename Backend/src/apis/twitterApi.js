const Twit = require('twit');
const config = require('./config/twitterApiConfig.js');

module.exports = {
    async getTwitterAccountByName(searchQuery) {
        var T = new Twit(config);
        const response = await T.get('users/search', {
            q: searchQuery,
            count: 20,
        });
        return response.data[0].screen_name;
    },
}

