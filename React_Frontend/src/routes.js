import React from 'react';
import {BrowserRouter, Route} from 'react-router-dom';
import Init from './pages/init';
import Showcase from './pages/showcase';

export default function Routes(){
    return(
        <BrowserRouter>
            <Route
                path="/" exact
                component={Init}
            />
            <Route
                path="/politicians/:key"
                component={Showcase}
            />
        </BrowserRouter>
    )
}