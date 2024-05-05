import { useEffect, useState } from 'react'
import axios from "axios";
import Attendance from './Attendance';

const baseURL = "http://localhost:8080/attendances/";
const loginURL = "http://localhost:8080/attendances/save/login";
const logoutURL = "http://localhost:8080/attendances/save/logout";


const DetailsMain = () => {

  const[data, setData] = useState([]);
  const[loginTime, setLoginTime] = useState(new Date());
  const[logoutTime, setLogoutTime] = useState(new Date());
  const [isLoginButtonDisabled, setLoginButtonDisabled] = useState(false);
  const [isLogoutButtonDisabled, setLogoutButtonDisabled] = useState(true);

  const handleLogin = async() => {
    setLoginTime(new Date().toUTCString())
    setLoginButtonDisabled(true)
    setLogoutButtonDisabled(false)

   
    const data = {
      loginTime: new Date().toUTCString()
    };

    try {
      const response = await axios.post(loginURL, data);
      console.log("Data saved:", response.data);

    } catch (error) {
      console.error("Error saving data:", error);
    }

  }

  const handleLogout = async() => {
    setLogoutTime(new Date().toUTCString())
    setLogoutButtonDisabled(true)
    setLoginButtonDisabled(false)
   
    const data = {
      loginTime: loginTime,
      logoutTime: new Date().toUTCString()
    };

    try {
      const response = await axios.post(logoutURL, data);
      console.log("Data saved:", response.data);

    } catch (error) {
      console.error("Error saving data:", error);
    }

    window.location.reload();

  }



  useEffect(() => {
    axios.get(baseURL).then((response) => {
        //console.log(response)
        //console.log(response.data)
        //console.log(response.data.expenses)
        setData(response.data.attendances)
      });
  

  }, [])
  
  return (
    <div className='details-main'>
        <div className='new-button'>
            <button id='login-btn' disabled={isLoginButtonDisabled} onClick={handleLogin}> Login </button>

            <button id='logout-btn' disabled={isLogoutButtonDisabled} onClick={handleLogout}>  Logout</button>
        </div>

        <div className='expenses-container'>
        {
          data.map((item, index) => <Attendance key={index} 
          date={new Date(item.loginDate).toString().substring(0, 15)} 
          inTime={new Date(item.loginTime).toString().substring(16, 21)} 
          outTime={new Date(item.logoutTime).toString().substring(16, 21)} 
          hours={item.workingHours}/>)
        }
        </div>

    </div>
  )
}

export default DetailsMain
