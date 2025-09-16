import '../styles/MainMatchesHeader.css'
import React from "react";
import "../styles/MainMatchesHeader.css";

const MainMatchesHeader = () => {
  return (
    <div className="main-matches-header">
      <div className="main-matches-header-button-container"> 
        <button className="main-matches-header-sports-button"> Futbal </button>
        <button className="main-matches-header-sports-button"> Hokej </button>
        <button className="main-matches-header-sports-button"> Tennis </button>
        <button className="main-matches-header-sports-button"> E-sports </button>
        <button className="main-matches-header-sports-button"> MMA </button>
        <button className="main-matches-header-sports-button"> Darts </button>
        </div>
      {/* Add filters, date selectors, or tabs here if needed */}
    </div>
  );
};

export default MainMatchesHeader;
