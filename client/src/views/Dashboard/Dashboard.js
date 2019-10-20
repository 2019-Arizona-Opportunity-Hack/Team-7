import React, {useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/styles';
import { Grid } from '@material-ui/core';
import axios from 'axios';
import palette from 'theme/palette';

import {
  Budget,
  TotalUsers,
  TasksProgress,
  TotalProfit,
  LatestSales,
  UsersByDevice,
  LatestProducts,
  LatestOrders
} from './components';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(4)
  }
}));

const Dashboard = () => {

  const [totalEvents, setTotalEvents] = useState(0);
  const [upcomingEvents, setUpcomingEvents] = useState(0);
  const [totalVolunteer, setTotalVolunteer] = useState(0);
  const [totalRevenue, setTotalRevenue] = useState(0);
  const [top5Donors, setTop5Donors] = useState([]);
  const [latestDonations, setLatestDonations] = useState([]);


  useEffect(()=>{

    axios.all([
      axios.get('http://54.172.164.131:8080/events'),
      axios.get('http://54.172.164.131:8080/topResults'),
      axios.get('http://54.172.164.131:8080/revenue')
    ])
    .then(responseArr => {
      //this will be executed only when all requests are complete
      console.log('Date created: ', responseArr[0]);
      console.log('Date created: ', responseArr[1]);
      console.log('Date created: ', responseArr[2]);
      setTotalRevenue(responseArr[2].data.totalRevenue);
      setTotalVolunteer(responseArr[2].data.totalVolunteers);
      setTotalEvents(responseArr[0].data.totalEvents);
      setUpcomingEvents(responseArr[0].data.upcomingEvents);
      const topDonationList = [];
      if(responseArr[1].data.topDonations){
        responseArr[1].data.topDonations.map((element, index) => {
          console.log(element);
          if(index < 5)
          topDonationList[index] = {
            'amount': element.amount,
            // 'donationDate':element.date,
            'donationId':element.donationId
          };
        });
        setLatestDonations(topDonationList);
      }

      const topDonorUser = [];
      const topDonorAmount = [];
      if(responseArr[1].data.topDonors){
        responseArr[1].data.topDonors.map((element, index) => {
          console.log(element);
          if(index < 5)
          topDonorAmount.push(element.totalAmount);
          topDonorUser.push(element.user.firstName);
        });
        const k = {}
        k["labels"] = topDonorUser
        k["datasets"] = [];
        k["datasets"].push({
          'backgroundColor': palette.primary.main,
          'data': topDonorAmount
        });
        console.log(k);
        setTop5Donors(k);
      }
  

    });

  },[])

  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Grid
        container
        spacing={4}
      >
        <Grid
          item
          lg={3}
          sm={6}
          xl={3}
          xs={12}
        >
          <TotalProfit totalRevenue={totalRevenue}/>
        </Grid>
        <Grid
          item
          lg={3}
          sm={6}
          xl={3}
          xs={12}
        >
          <TotalUsers totalVolunteer={totalVolunteer}/>
        </Grid>
        <Grid
          item
          lg={3}
          sm={6}
          xl={3}
          xs={12}
        >
          <TasksProgress upcomingEvents={upcomingEvents}/>
        </Grid>
        <Grid
          item
          lg={3}
          sm={6}
          xl={3}
          xs={12}
        >
          <Budget totalEvents={totalEvents}/>
        </Grid>
        <Grid
          item
          lg={6}
          md={12}
          xl={9}
          xs={12}
        >
          <LatestSales top5Donors={top5Donors}/>
        </Grid>
        <Grid
          item
          lg={6}
          md={12}
          xl={9}
          xs={12}
        >
          <LatestOrders latestDonations={latestDonations}/>
        </Grid>
      </Grid>
    </div>
  );
};

export default Dashboard;
