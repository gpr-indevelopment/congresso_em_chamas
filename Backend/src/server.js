const express = require('express');
const cors = require('cors');
const routes = require('./routes');
const mongoose = require('mongoose');

mongoose.connect('mongodb://pimentgabriel:pimentgabriel@congressoemchamas-shard-00-00-jsxbx.mongodb.net:27017,congressoemchamas-shard-00-01-jsxbx.mongodb.net:27017,congressoemchamas-shard-00-02-jsxbx.mongodb.net:27017/CongressoEmChamas?ssl=true&replicaSet=CongressoEmChamas-shard-0&authSource=admin&retryWrites=true&w=majority', {
    useNewUrlParser: true
});

mongoose.set('useCreateIndex', true);

const app = express();

app.use(cors());
app.use(routes);
app.listen(3000);
