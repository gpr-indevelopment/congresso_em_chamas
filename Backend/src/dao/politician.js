const {Schema, model} = require('mongoose');

const PoliticianSchema = new Schema({
    name:{
        type: String,
        required: true,
        unique: true,
    },
    camaraId:{
        type: Number,
        required: true,
        unique: true,
    },
    picture:{
        type: String,
        required: true
    },
    partyInitials:{
        type: String,
        required: true
    },
    twitterUsername:{
        type: String,
        required: true
    }
}, {
    timestamps: true
});

module.exports = model('Politician', PoliticianSchema)