const axios = require('axios');

async function retrievePoliticians(searchQuery){
    const response = await axios.post("http://localhost:3000/getPolitician", null, {
        headers: {
            politician: searchQuery
        }
    });
    return response.data;
}

export {
    retrievePoliticians
}