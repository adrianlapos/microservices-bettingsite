import '../styles/Header.css'
const Header = ({loggedIn}) => {
    return(loggedIn===true ? (
        <div className="header">
            <div className="header-body" style={{display:"flex",flexDirection:'row'}}>
                <button className="header-button">Kurzy</button>
                <button className="header-button">Tikety</button>
                <button className="header-button">Nastavenia</button>
                <button className="header-button">Odhlasenie</button>
            </div>

        </div>) : 
        <div className="header">
             <div className="header-body" style={{display:"flex",flexDirection:'row'}}>
                <button className="header-button">Kurzy</button>
                <button className="header-button">Prihlasenie</button>
                <button className="header-button">Registracia</button>
            </div>
        </div>
    )


}
export default Header;