import React from "react";
import {FaBed} from "react-icons/fa";
import {TiPlane} from "react-icons/ti";
import {MdLocalPizza} from "react-icons/md";
import { IoIosCheckmarkCircleOutline } from "react-icons/io";
import { FaStopCircle } from "react-icons/fa";
import { FiPlay } from "react-icons/fi";



export class FinanceRenderComponent extends React.Component{
    constructor(props){
        super(props);
        this.state={
            status:this.props.ticket.ticketStatus
        }
        console.log(this.props);
    }

    changeInvoiceStatus = (event) =>{
        let statuses = event.target.id.split(",");
        let data = JSON.stringify({
            "auth":document.cookie.split("=")[1],
            "newStatus": parseInt(statuses[1]),
            "reimb_id": event.target.value,
            "resolvedBy":this.props.manager
        })
        fetch("http://localhost:8080/project1/status",{
            method:"PUT",
            body: data
        }).then(response=>{
            console.log(response.status);
        })
        console.log()
        this.props.ticket.ticketStatus = statuses[0];
        this.props.ticket.resolvedByFirstName = this.props.first_name;
        this.props.ticket.resolvedByLastName = this.props.last_name;

        this.setState({
            ...this.state,
            status:statuses[0]
        });
        this.props.caller(this.props.ticket);

    }


    getIcon(t){
        if(t == "lodging"){
            return <FaBed/>
        }
        if(t == "travel"){
            return <TiPlane/>
        }
        if (t == "food"){
            return <MdLocalPizza/>
        }
    }

    getStatusIcon(t){

        if(t == "pending"){
            return <FiPlay/>
        }
        if(t == "approved"){
            return <IoIosCheckmarkCircleOutline/>
        }
        if (t == "denied"){
            return <FaStopCircle/>
        }
    }

    render(){
        console.log(this.props);
        let resolver = `${this.props.ticket.resolvedByFirstName} ${this.props.ticket.resolvedByLastName}`
        console.log(resolver);
        return(
            <>
            <tr className={this.state.status} >
                <td>{this.props.ticket.reimbursementID}</td>
                <td  >{this.props.ticket.description}</td>
                <td>${this.props.ticket.reimbursementAmount}</td>
                <td>{this.getIcon(this.props.ticket.reimbursementType)}</td>
                <td>{this.getStatusIcon(this.state.status)}</td>
                <td>{new Date(this.props.ticket.submitted).toLocaleDateString()}</td>
                <td>{(resolver != `${null} ${null}` ? resolver : "Not Resolved Yet")}</td>
                <td><button className="btn btn-success" id={["approved",2]}  value={this.props.ticket.reimbursementID} onClick={this.changeInvoiceStatus}>Accept</button></td>
                <td><button className="btn btn-danger" id={["denied",3]}  value={this.props.ticket.reimbursementID} onClick={this.changeInvoiceStatus} >Reject</button></td>
            </tr>
            </>
        )
    }
}