import React from "react";
import { FaTrashAlt } from 'react-icons/fa';
import {FiSave} from "react-icons/fi";

export class AdminHelperUsersComponent extends React.PureComponent{

    deleteUser  = (event) => {
        event.preventDefault();

    }

    editUser  = (event) => {
        event.preventDefault();
        console.log(event.target.id);

    }
    
    render(){
        let name = this.props.users.firstName + " " + this.props.users.lastName;
        return(
            <>
                <tr defaultValue={this.props.users.userID} >
                    <td><input type="text" className="form-control" step='1' min="1" defaultValue={this.props.users.userID} /> </td>
                    <td><input type="text" className="form-control" step='1' min="1" defaultValue={name}/></td>
                    <td><input type="text" className="form-control" step='1' min="1" defaultValue={this.props.users.username}/></td>
                    <td><input type="text" className="form-control" step='1' min="1" defaultValue={this.props.users.email}/></td>
                    <td><input type="text" className="form-control" step='1' min="1" defaultValue={this.props.users.userRole}/></td>
                    <td><button id={this.props.users.userID} onClick={this.deleteUser}  className="delete_user_button"> <FaTrashAlt/> </button></td>
                    <td><button id={this.props.users.userID} onClick={this.editUser} className="edit_user_button"><FiSave/></button></td>
                </tr>
            </>
        )
    }
}