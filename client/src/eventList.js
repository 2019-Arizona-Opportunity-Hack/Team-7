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
const useStyles = makeStyles(theme => ({
  root: {
    width: "100%"
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
  }
}));

export default function DetailedExpansionPanel() {
  const classes = useStyles();
  const [data, setData] = useState([]);

  useEffect(() => {
    //   const result = await axios(
    //     'https://localhost:8080/eventlist',
    //   );
    const result = {
      events: [
        {
          title: "Event #1",
          count: 10,
          location: "Phoenix",
          time: "31st Oct, 2019, 16:00pm"
        },
        {
          title: "Event #2",
          count: 10,
          location: "Tempe",
          time: "31st Oct, 2019, 16:00pm"
        }
      ]
    };
    setData(result.events);
  }, []);
  return (
    <div className={classes.root}>
      <Typography variant="h6" gutterBottom>
        Event Details
      </Typography>
      {data.map((event, index) => (
        <ExpansionPanel defaultExpanded>
          <ExpansionPanelSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1c-content"
            id={"panel" + index}
          >
            <div className={classes.column}>
              <Typography className={classes.heading}>{event.title}</Typography>
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
                Event Time: {event.time}
              </Typography>
            </div>
            <div className={classes.column}>
              <Typography variant="caption">
                Volunteer Count: {event.count}
              </Typography>
            </div>
          </ExpansionPanelDetails>
          <Divider />
        </ExpansionPanel>
      ))}
      <Link to="/createEvent">
        <Button>Hello</Button>
      </Link>
    </div>
  );
}
