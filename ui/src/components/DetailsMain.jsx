import { useEffect, useState } from 'react'
import axios from "axios";
import Attendance from './Attendance';

const baseURL = "http://localhost:8080/attendances/";
const loginURL = "http://localhost:8080/attendances/save/login";
const logoutURL = "http://localhost:8080/attendances/save/logout";

const itemsPerPage = 10; // Number of items to display per page


const DetailsMain = () => {

  const[data, setData] = useState([]);
  const[loginTime, setLoginTime] = useState(new Date());
  const[logoutTime, setLogoutTime] = useState(new Date());
  const [isLoginButtonDisabled, setLoginButtonDisabled] = useState(false);
  const [isLogoutButtonDisabled, setLogoutButtonDisabled] = useState(true);
  
  const [totalPages, setTotalPages] = useState(1);
  
  const [currentPage, setCurrentPage] = useState(0);

  // Calculate the index of the first and last item to display on the current page
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  // Calculate the total number of pages
  //const totalPages = 

  //const currentItems = data.slice(indexOfFirstItem, indexOfLastItem);


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
  

  // Go to previous page
  const goToPreviousPage = () => {
    setCurrentPage(prevPage => Math.max(prevPage - 1, 0));
        console.log(currentPage)

    // updateData();   

  };

  // Go to next page
  const goToNextPage = () => {
    //const next = currentPage - 1;
    setCurrentPage(prevPage => Math.min(prevPage + 1, totalPages));
    console.log(currentPage)
    
    //updateData()
    
  };
 


  useEffect(() => {
  
    axios.get(baseURL + `?pageNumber=${currentPage}&pageSize=${itemsPerPage}`).then((response) => {
        console.log(response)

        setData(response.data.attendances)
        setTotalPages(Math.floor(response.data.total/ response.data.pageSize) )
      });
  

  }, [currentPage])
  
  return (
    <div className='details-main'>
        <div className='new-button'>
            <button id='login-btn' disabled={isLoginButtonDisabled} onClick={handleLogin}> Login </button>

            <button id='logout-btn' disabled={isLogoutButtonDisabled} onClick={handleLogout}>  Logout</button>
        </div>

        <div className='expenses-container'>
        {
          data?.map((item, index) => <Attendance key={index} 
          date={new Date(item.loginDate).toString().substring(0, 15)} 
          inTime={new Date(item.loginTime).toString().substring(16, 21)} 
          outTime={new Date(item.logoutTime).toString().substring(16, 21)} 
          hours={item.workingHours}/>)
        }
        </div>
        

      {/* Pagination buttons */}
      <div>
        <button style={{fontSize:"1.5rem", width:"4rem"}} onClick={goToPreviousPage} disabled={currentPage === 0}>
          Prev
        </button>
        <button style={{fontSize:"1.5rem", width:"4rem"}}  onClick={goToNextPage} disabled={currentPage === totalPages}>
          Next
        </button>
      </div>

    </div>
  )
}

export default DetailsMain
