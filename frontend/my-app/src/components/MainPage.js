
import Chat from "./Chat";
import { useEffect, useState } from "react";
import axios from "axios";
import Header from "./Header";
import '../styles/MainPage.css'
import BetslipDraft from "./BetslipDraft";
import MainMatchesHeader from "./MainMatchesHeader";
function MainPage() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [loggedin,setLoggedin] = useState(false)
  const [draft,setDraft] = useState(null)
  useEffect(() => {
     fetch("http://localhost:8084/api/auth/me", {
      method: "GET",
      credentials: "include", // Send cookies!
    })
          .then(res => res.json())
      .then(data => {
        console.log(data);
        setUser(data);
        setLoading(false);
        setLoggedin(true)
      })
      .catch(err => {
        setUser(null); 
        setLoading(false);
      });
  }, []);

  useEffect(()=>{
    fetch('http://localhost:8083/api/betslips/current',{
      method:'GET',
      credentials:'include'
    })
    .then(res => res.json())
    .then(data =>{
      setDraft(data)
      console.log(data)
    })
    .catch(err => {
     
        console.log("error loading betslip")
      });
  },[])

  if (loading) return <p>Loading...</p>;

return (
  <>
    <Header loggedIn={loggedin} />

    {loggedin ? (
      user ? (
        <div className="main-page-container">
          <div className="main-page-main">
            
            {/* Left part — Chat or anything */}
            <div className="main-page-left-part">
              <Chat/>
            </div>

            {/* Main part — Matches scrollable */}
            <div className="main-page-main-part">
              <MainMatchesHeader/>
              {/* List of matches */}
            </div>

            {/* Right part — Bet slip fixed */}
            <div className="main-page-right-part">
              <BetslipDraft />
            </div>

          </div>

          <h1>Welcome {user.username}</h1>
        </div>
      ) : (
        <div>
          <p>Please log in</p>
          <a href="/login">Go to Login</a>
        </div>
      )
    ) : (
      <div></div>
    )}
  </>
);
}

export default MainPage;
