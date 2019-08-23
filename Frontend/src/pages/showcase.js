import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './showcase.css';

export default function Showcase({ match }) {

    const [politicians, setPoliticians] = useState({ picture: "" });

    useEffect(() => {
        async function getData() {
            const response = await axios.post("http://localhost:3000/getPolitician", null, {
                headers: {
                    politician: match.params.key
                }
            });
            console.log(response.data);
            setPoliticians(response.data);
        }
        getData();
    }, [match.params.key])

    return (
        <div className="global-container">
            {politicians.length > 0 ? (
                <div className="main-container">
                    <img src={politicians[0].picture} className="politician-image" alt="politician_image" />
                    <div className="description">

                    </div>
                    <div className="to-do">

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