import React from "react";
export class LogOutComponent extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            info:JSON.parse(sessionStorage.getItem("project1_data"))
        }
    }

    logout = (e) =>{
        e.preventDefault();
        let c = document.cookie.split(";");
        let auth = "";
        for (let index = 0; index < c.length; index++) {
            let v = c[index].split("=");
            if(v[0] == "auth"){
                auth = v[1];
            }
        }

        fetch("http://localhost:8080/project1/logout",{
            headers:{
                "Content-Type":"application/x-www-form-urlencoded"
            },
            method:"delete",
            body:JSON.stringify({"auth":auth})
        })
        .then(response => {
            return response.json()
        }).then(response =>{
            sessionStorage.removeItem("project1_data");
            document.location.href="/index";
            document.cookie = null;
        })
    }


    render(){
        return(
            this.logout
        )
    }
}