import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom"

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    async function login(event) {
        event.preventDefault();

        try {
            await axios.post("http://localhost:8080/api/v1/employee/login", {
                email: email,
                password: password,
            }).then((res) => {
                console.log(res.data);

                if (res.data.message == "Wrong Email") {
                    alert("Wrong Email");
                } else if (res.data.message == "Login Success") {
                    navigate("/home");
                } else {
                    alert("Incorrect Email and Password");
                }
            }, fail => {
                console.error(fail)
            });
        } catch (err) {
            alert(err);
        }
    }

    return (
        <div className="container">
            <div className="row">
                <h2>Login</h2>
                <hr />
            </div>
    
            <div className="row">
                <div className="col-sm-6">
    
                    <form>
                        <div className="form-group">
                            <label>Email</label>
                            <input type="email" className="form-control" id="email" placeholder="Enter email"
    
                                value={email}
                                onChange={(event) => {
                                    setEmail(event.target.value);
                                }}
                            />
    
                        </div>
    
                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" id="password" placeholder="Enter password"
    
                                value={password}
                                onChange={(event) => {
                                    setPassword(event.target.value);
                                }}
                            />
    
                        </div>
    
                        <button type="submit" className="btn btn-primary" onClick={login}>Login</button>
                    </form>
                </div>
            </div>
    
        </div>
    );
}

export default Login;