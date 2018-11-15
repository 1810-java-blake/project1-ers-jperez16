import React, { Component } from 'react';
// import logo from './logo.svg';
import './App.css';
import { LogInComponent } from './Components/login.component';
import "./include/bootstrap";
import {BrowserRouter,Route,Switch} from 'react-router-dom';
import { AdminComponent } from './Components/admin/admin.component';
import { NewTicketComponent } from './Components/new.ticket.component';
import { FinancialManagerComponent } from './Components/finance/financial.mananger.component';
import { EmployeeComponent } from './Components/employees/employee.component';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <>
          <div className="App">
            <Switch>
              <Route path="/index" component={LogInComponent}/>
              <Route path="/admin" component={AdminComponent}/>
              <Route path="/home" component={EmployeeComponent}/>
              <Route path="/newticket" component={NewTicketComponent}/>
              <Route path="/finance" component={FinancialManagerComponent}/>

              <Route component={LogInComponent}/>
            </Switch>

          </div>
        </>
      </BrowserRouter>
    );
  }
}

export default App;
