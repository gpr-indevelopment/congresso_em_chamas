const axios = require('axios');

async function retrievePoliticians(searchQuery){
    const response = await axios.get("http://localhost:8080/politician?name=" + searchQuery, null, {});
    let responseArray = [];
    response.data.forEach(element => {
        responseArray.push(element);
    });
    return responseArray;
}

export {
    retrievePoliticians
}