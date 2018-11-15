import React from "react";
import {IoIosPersonAdd} from "react-icons/io";
import {IoIosLogOut} from "react-icons/io";
import { AdminHelperUsersComponent } from "./admin_helper_users.components";


export class AdminComponent extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            info: JSON.parse(sessionStorage.getItem("project1_data")),
            data:[],
        }
        if (this.state.info == null ){
            document.location.href = "/index";
        }
        this.auth = document.cookie.split("=")[1]
    }

    componentDidMount(){
        fetch(`http://localhost:8080/project1/users/getAll?auth=${this.auth}`).then(response => response.json()).then( response =>{
            this.setState({
                ...this.state,
                data:response.results
            });
        });
    }

    removeUser = (id) => (event) =>{

    }


    capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    userLogOut = (event)=>{
        event.preventDefault();
        fetch(`http://localhost:8080/project1/logout`,{
            headers:{
                "Content-Type":"application/x-www-form-urlencoded"
            },
            method:"post",
            body:JSON.stringify({"auth":this.auth})
        })
        this.setState({
            ...this.state,
            data:null
        });
        sessionStorage.removeItem("project1_data");
        document.location.href="/index";

    }

    buttonClicked  = (event) => {
        event.preventDefault();
        console.log(event.target.value);
        console.log(event.target.id);

    }


    render(){
        console.log(this.auth);
        // let results = this.state.data;
        return(
            <>
                <div>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                        </button>
                        <a className="navbar-brand">Hello {this.capitalizeFirstLetter(this.state.info.firstName)}</a>
                    
                        <div className="collapse navbar-collapse" id="navbarTogglerDemo03">
                        <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li className="nav-item active">
                                <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#" title="Add User" ><IoIosPersonAdd/></a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link disabled" href="#">Remove User</a>
                            </li>
                            <li className="nav-item">
                                <button  onClick={this.userLogOut} title="Log Out"><IoIosLogOut/></button>
                            </li>
                        </ul>
                        </div>
                    </nav>
                    <div>
                    <table className="table table-dark table-bordered table-hover">
                        <tbody>
                            <tr>
                                <th>User ID</th>
                                <th>Name</th>
                                <th>User Name</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Edit</th>
                            </tr>
                            {
                                this.state.data.map(users =>
                                    <AdminHelperUsersComponent testFunc={this.buttonClicked} key={users.userID} users={users}/>
                                )
                            }
                        </tbody>
                    </table>
                    <button value="adsfadf" onClick={this.buttonClicked}> Test</button>
                    </div>
                </div>
            </>
        )
    }
}