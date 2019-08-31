import React, { useEffect, useState } from 'react';
import {retrievePoliticians} from '../services';
import './showcase.css';

export default function Showcase(props) {

    const [politicians, setPoliticians] = useState({ picture: "" });

    const params = {
        politicianId: props.match.params.key,
        history: props.history
    }

    useEffect(() => {
        async function getData(params) {
            const politicianId = params.politicianId;
            const history = params.history;
            if(!(politicians.length > 0)){
                const response = await retrievePoliticians(politicianId);
                if(response.length > 0){
                    setPoliticians(response);
                }
                else{
                    history.push('/');
                }
            }
        }
        getData(params);
    }, [params, politicians])

    return (
        <div className="global-container">
            {politicians.length > 0 ? (
                <div className="main-container">
                    <img src={politicians[0].picture} className="politician-image" alt="politician_image" />
                    <div className="description">

                    </div>
                    <div className="to-do">
                        <div className="twitter">
                            Teste twitter
                        </div>
                        <div className="google-search">
                            Teste google-search
                        </div>
                        <div className="last-votes">
                            Teste last-votes
                        </div>
                        <div className="law-projects">
                            teste law-projects
                        </div>
                    </div>
                </div>
            ) : (
                    <div className="main-container">
                        <img src="" className="politician-image" alt="politician_image" />
                        <div className="description">

                        </div>
                        <div className="to-do">

                        </div>
                    </div>
                )}
        </div>
    )

}