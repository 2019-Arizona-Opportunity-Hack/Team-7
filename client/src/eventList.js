import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import clsx from "clsx";
import ExpansionPanel from "@material-ui/core/ExpansionPanel";
import ExpansionPanelDetails from "@material-ui/core/ExpansionPanelDetails";
import ExpansionPanelSummary from "@material-ui/core/ExpansionPanelSummary";
import ExpansionPanelActions from "@material-ui/core/ExpansionPanelActions";
import Typography from "@material-ui/core/Typography";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import Chip from "@material-ui/core/Chip";
import Button from "@material-ui/core/Button";
import Divider from "@material-ui/core/Divider";
import { Route, Link, BrowserRouter as Router } from "react-router-dom";
import CreateEvent from "./createEvent";
import axios from 'axios';

const useStyles = makeStyles(theme => ({
  root: {
    width: "80%",
    marginLeft: "139px"
  },
  heading: {
    fontSize: theme.typography.pxToRem(15)
  },
  secondaryHeading: {
    fontSize: theme.typography.pxToRem(15),
    color: theme.palette.text.secondary
  },
  icon: {
    verticalAlign: "bottom",
    height: 20,
    width: 20
  },
  details: {
    alignItems: "center"
  },
  column: {
    flexBasis: "33.33%"
  },
  helper: {
    borderLeft: `2px solid ${theme.palette.divider}`,
    padding: theme.spacing(1, 2)
  },
  link: {
    color: theme.palette.primary.main,
    textDecoration: "none",
    "&:hover": {
      textDecoration: "underline"
    }
  },
  button: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(0.8),
    float: "right"
  }
}));

export default function DetailedExpansionPanel() {
  const classes = useStyles();
  const [data, setData] = useState([]);

  useEffect(() => {
      

      // axios('http://54.172.164.131:8080/events')
      // .then(responseArr => {
      //   //this will be executed only when all requests are complete
      //   console.log('Events: ', responseArr.events);
      //   setData(responseArr.events);
      // });
      axios.get('http://54.172.164.131:8080/events')
      .then(res => {
        console.log(res.data.events);
        setData(res.data.events);
      });
  }, []);
  return (
    <div className={classes.root}>
      <Typography variant="h6" gutterBottom>
        <b>Event Details</b>
      </Typography>
      {data && data.map((event, index) => (
        <ExpansionPanel>
          <ExpansionPanelSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1c-content"
            id={"panel" + index}
          >
            <div className={classes.column}>
              <Typography className={classes.heading}>{event.name}</Typography>
            </div>
            <div className={classes.column}>
              <Typography className={classes.secondaryHeading}>
                Volunteer Attending
              </Typography>
            </div>
          </ExpansionPanelSummary>
          <ExpansionPanelDetails className={classes.details}>
            <div className={classes.column}>
              <Typography variant="caption">
                Location: {event.location}
                <br />
                Event Time: {event.eventDate}
              </Typography>
            </div>
            <div className={classes.column}>
              <Typography variant="caption">
                Volunteer Count: {event.volunteers ? event.volunteers.length : 0}
                <br />
                Duration: {event.duration}
              </Typography>
            </div>
          </ExpansionPanelDetails>
          <Divider />
        </ExpansionPanel>
      ))}
      <Link to="/createEvent">
        <Button variant="contained" color="primary" className={classes.button}>
          Create Event
        </Button>
      </Link>
    </div>
  );
}
