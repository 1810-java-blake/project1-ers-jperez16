import React from "react";
import { FinanceRenderComponent } from "../Renders/finance.render";

export class FinancialManagerComponent extends React.Component{
    constructor(props){
        super(props);
        this.state={
            info:JSON.parse(sessionStorage.getItem("project1_data")),
            userdata : JSON.parse(sessionStorage.getItem("userData"))
        }
        this.originalState = this.state;
        this.auth = document.cookie.split("=")[1]
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

    sortTickets = (event) =>{
        this.setState({
            ...this.state,
            info: this.originalState.info,
            userdata:this.originalState.userdata
        })
        let newTickets = []
        this.originalState.userdata.map(tickets=>{
            if(event.target.id === "all"){
                newTickets.push(tickets)
            }
            else if(tickets.ticketStatus === event.target.id){
                newTickets.push(tickets)
            }
        })
        this.setState({
            ...this.state,
            userdata: newTickets
        })
    }

    ticketChanged = (event) =>{
        let newState = []
        this.state.userdata.forEach(t=>{
            if(t.reimbursementID == event.reimbursementID){
                newState.push(event);
            }
            else{
                newState.push(t);
            }
        })
        sessionStorage.setItem("userData",JSON.stringify(newState));
    }

    render(){
        return(
            <>
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
                                <a className="nav-link" onClick={this.sortTickets} id="all" title="All Invoices">All</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" onClick={this.sortTickets} id="pending"  title="Pending Invoices">Pending</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" onClick={this.sortTickets} id="approved"  title="Approved Invoices">Approved</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" onClick={this.sortTickets} id="denied"  title="Denied Invoices">Denied</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" onClick={this.userLogOut} title="Log Out">Log Out</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            <div className="outer_div">
                <div className="inner_div">
                    <div className="finance_div">
                        <table className="table table-80s table-sm table-dark table-bordered table-hover" width="100px">
                            <thead className="thead-dark">
                                    <tr>
                                        <th className="reimbursement_width">ID</th>
                                        <th>Description</th>
                                        <th>Amount</th>
                                        <th className="columnWidth" >Type</th>
                                        <th>Status</th>
                                        <th>Submitted</th>
                                        <th>Resolved By</th>
                                        <th>Accept</th>
                                        <th>Rejected</th>
                                    </tr>
                                </thead>
                            <tbody>
                                    {
                                    this.state.userdata.map(tickets=>(
                                        <FinanceRenderComponent first_name={this.state.info.firstName} last_name={this.state.info.lastName} key={tickets.reimbursementID} manager={this.state.info.userID} caller={this.ticketChanged}  ticket={tickets} color="green"/>
                                    ))
                                    }
                            </tbody>
                        </table>
                    </div>
                
                </div>
            </div>
            
            </>
        )
    }
    
}