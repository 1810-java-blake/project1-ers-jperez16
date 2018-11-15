import React from "react";
export class LogInComponent extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            username: "",
            password: ""
        }
        this.hash = require("hash.js");
    }

    usernameChange = (e) =>{
        this.setState({
            ...this.state,
            username:e.target.value
        })
    }

    passwordChange = (e) =>{
        this.setState({
            ...this.state,
            password: e.target.value
        })
    }


    submit = (e) =>{
        e.preventDefault();
        let data = JSON.stringify({"data":btoa(JSON.stringify(this.state))})
        fetch("http://localhost:8080/project1/login",{
            headers:{
                "Content-Type":"application/x-www-form-urlencoded"
            },
            method:"post",
            body:data
        })
        .then(response => {
            return response.json()
        }).then(response =>{
            document.cookie = `auth=${response["auth"]};`;
            
            if(response["results"]){
                let data = response["results"][0];
                sessionStorage.setItem("project1_data", JSON.stringify(data));
                if(data.roleID == 1){
                    document.location.href="/admin";
                }
                if(data.roleID == 2){
                    console.log("its a regular user");
                    sessionStorage.setItem("userData",JSON.stringify(response.resultsData));
                    console.log(data);
                    console.log(response);
                    document.location.href = "/home"

                }
                if(data.roleID == 3){
                    console.log("its the finance manager");
                    sessionStorage.setItem("userData",JSON.stringify(response.resultsData));
                    console.log(data);
                    console.log(response);
                    document.location.href = "/finance"
                }

            }
            else{
                document.getElementById("login_error").innerText = response["error"];
            }
        })
    }


    render(){
        return(
            <>
            <div className="login_class">
                <header>
                    <img src="https://3g4d13k75x47q7v53surz1gi-wpengine.netdna-ssl.com/wp-content/themes/revature/imgs/logo.png"></img>
                </header>
                <div className="login_div">
                    <h2 id="sign_h2">Sign in</h2>
                    <form id="login_form" onSubmit={this.submit}>
                        <label id="username_label">Email:</label> <input type="text" name="username" required autoComplete="off" value={this.state.username} onChange={this.usernameChange}/>
                        <br/>
                        <label id="password_label">Password:</label> <input type="password" name="password" required  autoComplete="off" value={this.password} onChange={this.passwordChange}/>
                        <br/>
                        <button id="submit_login">Log In</button>
                        <button id="submit_forget">Forgot Password</button>
                        <br/>
                        <label id="login_error"/>
                    </form>
                </div>
            </div>
            </>
        )
    }
}