import React from "react";
import {IoIosPersonAdd} from "react-icons/io";
import {IoIosLogOut} from "react-icons/io";
import { EmployeeRender } from "../Renders/employee.renderer";


export class EmployeeComponent extends React.Component{
    constructor(props){
        super(props);
        this.state ={
            info:JSON.parse(sessionStorage.getItem("project1_data")),
            data:JSON.parse(sessionStorage.getItem("userData")),
            buttonName: "New"
        }
        if (this.state.info == null ){
            document.location.href = "/index";
        }
        this.auth = document.cookie.split("=")[1]

    }

    componentDidMount(){
        this.state ={
            info:JSON.parse(sessionStorage.getItem("project1_data")),
            data:JSON.parse(sessionStorage.getItem("userData")),
            buttonName: "New"
        }        
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

    render(){
        let thing = null;
        if(this.state.data != null) {
            thing = this.state.data.map(tickets=>(
                <EmployeeRender key={tickets.reimbursementID} ticket={tickets}/>
            ))
        }
        return (
            <div className="outer_div">
                <div className="inner_div">
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

                            <li className="nav-item active">
                                <a className="nav-link" href="/newticket">New Invoice</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" onClick={this.userLogOut} title="Log Out">Log Out</a>
                            </li>
                        </ul>
                        </div>
                    </nav>
                    <div className="nav_div">
                        <table className="table table-dark table-bordered table-hover col-9">
                            <tbody>
                                <tr>
                                    <th>Reimbursement ID</th>
                                    <th>Description</th>
                                    <th>Amount</th>
                                    <th>Type</th>
                                    <th>Status</th>
                                    <th>Submitted</th>
                                    <th>Resolved By</th>
                                </tr>
                            </tbody>
                            {
                                thing

                            }
                        </table>
                    </div>
                </div>
            </div>
        )
    }
}