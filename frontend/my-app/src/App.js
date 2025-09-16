import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from "react-router-dom";
import Login from './components/Login';
import MainPage from '../src/components/MainPage'
import ProtectedRoute from './components/ProtectedRoute';
function App() {
  return (
     <Router basename=''>
       <Routes>
        <Route path="/" element={<MainPage/>} ></Route>
        <Route path="/login" element={<Login/>} ></Route>
        <Route path="/protected" element={<ProtectedRoute/>} ></Route>
       </Routes>
      </Router>
   
  );
}

export default App;
