import moment from "moment"
const Attendance = ({date, inTime, outTime, hours}) => {
  return (
    <div className='expense-box'>
       <div className='description-box'>
            {date}
       </div>
       <div className='amount-box'>
        <span>In: {moment(inTime, 'hh:mm a').format('hh:mm a')}</span>
        <span>Out: {moment(outTime, 'hh:mm a').format('hh:mm a')}</span>
        <span>{hours}h</span>

       </div>

    </div>
  )
}

export default Attendance