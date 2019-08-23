import React, {useState} from 'react';
import "./init.css";
import video from '../assets/protests.mp4';
import logo from '../assets/logo.png';

export default function Init(props) {

    const history = props.history;

    const[politician, setPolitician] = useState('');

    async function handleSubmit(e) {
        e.preventDefault();
        if(politician && politician !== ""){
            history.push(`/politicians/${politician}`);
        }
    }

    return (
        <div className="content">
            <div className="search-box-container">
                <form className="search-form" onSubmit={handleSubmit}>
                    <img src={logo} alt="logo" />
                    <input
                        placeholder="Insira o nome de um parlamentar"
                        value = {politician}
                        onChange = {e => {setPolitician(e.target.value)}}
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