import React from 'react';
import "./init.css";
import video from '../assets/protests.mp4';
import logo from '../assets/logo.png';

export default function Init() {

    function handleSubmit() {
        console.log("teste")
    }

    return (
        <div className="content">
            <div className="search-box-container">
                <form className="search-form" onSubmit={handleSubmit}>
                    <img src={logo} alt="logo" />
                    <input
                        placeholder="Insira o nome de um parlamentar"
                    />
                    <button type="submit">
                        Enviar
                </button>
                </form>
            </div>
            <video autoPlay muted loop className="video-container">
                <source className="video" src={video} type="video/mp4" />
            </video>
        </div>
    )
}