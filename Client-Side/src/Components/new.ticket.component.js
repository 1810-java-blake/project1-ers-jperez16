import React from "react";


export class NewTicketComponent extends React.PureComponent{
    constructor(props){
        super(props);
        this.state = {
            info:JSON.parse(sessionStorage.getItem("project1_data")),
            data:JSON.parse(sessionStorage.getItem("userData")),
            reimbType:"1",
            description:"",
            reimbAmount:"0"
        }
        this.auth = document.cookie.split("=")[1]

    }

    capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    createNewTicket =(event) => {
        event.preventDefault(); 
        fetch("http://localhost:8080/project1/newticket",{
            headers:{
                "Content-Type":"application/x-www-form-urlencoded"
            },
            method:"post",
            body:JSON.stringify({
                "userID": this.state.info.userID,
                "description":this.state.description,
                "reimbursementAmount":this.state.reimbAmount,
                "reimbursementType":this.state.reimbType,
                "email":this.state.info.email,
                "auth":this.auth
            })
        }).then(response=>response.json()).then(response=>{
            sessionStorage.setItem("userData",JSON.stringify(response.resultsData));
            document.location.href = "/home";
        })
    }

    updateValues = (event) =>{
        if(event.target.id === "reimbursementAmount"){
            this.setState({
                ...this.state,
                reimbAmount:event.target.value
            })
        }

        if(event.target.id === "description"){
            this.setState({
                ...this.state,
                description:event.target.value
            })
        }

        if(event.target.id === "reimbursementType"){
            this.setState({
                ...this.state,
                reimbType:event.target.value
            })
        }
    }



    render(){
        
        return(
            <>
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
                                        <a className="nav-link" href="/home">Home <span className="sr-only">(current)</span></a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                            <div className="ticket_form_image">
                                <div className="new_ticket_form">
                                    <form onSubmit={this.createNewTicket}>
                                        <div className="form-group" id="select_form_box">
                                            <label htmlFor="reimbursementType">Reimbursement Type</label>
                                            <select className="form-control" id="reimbursementType" onChange={this.updateValues}>
                                                <option value="1">Lodging</option>
                                                <option value="2">Travel</option>
                                                <option value="3">Food</option>
                                                <option value="4">Other</option>
                                            </select>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="description">Description</label>
                                            <textarea required maxLength="250" className="form-control" id="description" onChange={this.updateValues} rows="3"></textarea>
                                            <label htmlFor="reimbursementAmount">Amount</label>
                                            <textarea maxLength="6" type="number" required  className="form-control" id="reimbursementAmount" onChange={this.updateValues} rows="3"/>
                                        </div>
                                        <button>Submit</button>
                                    </form>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        )
    }
}