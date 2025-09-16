import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();
 
  // 👀 Check if already logged in
  useEffect(() => {
    fetch("http://localhost:8084/api/auth/me", {
      method: "GET",
      credentials: "include", // Send cookies!
    })
      .then((res) => {
        if (res.ok) navigate("/");
      })
      .catch(() => {});
  }, [navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      const response = await fetch("http://localhost:8084/api/auth/login", {
        method: "POST",
        credentials: "include", // 👈 Send and receive cookies
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          credential: username, // Use "credential" if your backend expects it
          password: password,
        }),
      });

      if (!response.ok) throw new Error("Login failed");

      // ✅ Login success
      navigate("/");
    } catch (err) {
      setError("Invalid username or password");
      console.error("Login failed:", err);
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "2rem auto" }}>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default LoginPage;
