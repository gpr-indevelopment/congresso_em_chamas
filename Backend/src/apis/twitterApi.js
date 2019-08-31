const Twit = require('twit');
const config = require('./twitterApiConfig.js');

async function searchTwitterUser(searchQuery){

    var T = new Twit(config);

    const response = await T.get('users/search', {
        q: searchQuery,
        count: 20,
    });
    console.log(response.data[0].screen_name);
    return response.data[0].screen_name;
}

module.exports = searchTwitterUser;

