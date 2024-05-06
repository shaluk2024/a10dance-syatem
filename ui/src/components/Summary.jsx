import axios from "axios";
import { useEffect, useState } from "react"

const baseURL = "http://localhost:8080/attendances/summary";


const Summary = () => {

  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get(baseURL).then((response) => {
      console.log(response)
       
        setData(response.data.attendanceSummaries)
      });
  

  }, [])

  return (
    <div className='summary-container'>
      {
        data.map((item, index) => <div key={index} className='summary-item'>
        <span className='date-span'>{item.monthYear}</span>
        <span style={{textAlign:"right", paddingRight:"1rem"}} className="amount-span">{item.workingHours}h</span>
    </div> )
      }
        

        {/* <div className='month-two'>
            <span className='date-span'>Date</span>
            <span>Amount</span>
        </div>

        <div className='month-three'>
            <span className='date-span'>Date</span>
            <span>Amount</span>
        </div> */}

    </div>
  )
}

export default Summary