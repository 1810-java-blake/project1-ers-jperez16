import React from "react";
import {FaBed} from "react-icons/fa";
import {TiPlane} from "react-icons/ti";

import {MdLocalPizza} from "react-icons/md";


export class EmployeeRender extends React.Component{
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
    render(){
        let resolver = `${this.props.ticket.resolvedByFirstName} ${this.props.ticket.resolvedByLastName}`
        return(
            <>
            <tbody>
                <tr className={this.props.ticket.reimbursementStatus}>
                    <td>{this.props.ticket.reimbursementID}</td>
                    <td>{this.props.ticket.description}</td>
                    <td>${this.props.ticket.reimbursementAmount}</td>
                    <td>{this.getIcon(this.props.ticket.reimbursementType)}</td>
                    <td>{this.props.ticket.ticketStatus}</td>
                    <td>{new Date(this.props.ticket.submitted).toLocaleDateString()}</td>
                    <td>{(resolver != `${null} ${null}` ? resolver : "Not Resolved Yet")}</td>
                </tr>
            </tbody>
            </>
        )
    }
}