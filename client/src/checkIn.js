import Typography from "@material-ui/core/Typography";
import React, {useEffect} from "react";
import { makeStyles } from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Paper from "@material-ui/core/Paper";
import Stepper from "@material-ui/core/Stepper";
import Step from "@material-ui/core/Step";
import StepLabel from "@material-ui/core/StepLabel";
import Button from "@material-ui/core/Button";
import Link from "@material-ui/core/Link";
import Review from "./Review";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControl from "@material-ui/core/FormControl";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import TextareaAutosize from "@material-ui/core/TextareaAutosize";
import axios from "axios";
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

toast.configure({
  autoClose: 4000,
  draggable: false,
  //etc you get the idea
});
export default function CheckInComponent() {

  const useStyles = makeStyles(theme => ({
    appBar: {
      position: "relative"
    },
    layout: {
      width: "auto",
      marginLeft: theme.spacing(2),
      marginRight: theme.spacing(2),
      [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
        width: 600,
        marginLeft: "auto",
        marginRight: "auto"
      }
    },
    paper: {
      marginTop: theme.spacing(3),
      marginBottom: theme.spacing(3),
      padding: theme.spacing(2),
      [theme.breakpoints.up(600 + theme.spacing(3) * 2)]: {
        marginTop: theme.spacing(6),
        marginBottom: theme.spacing(6),
        padding: theme.spacing(3)
      }
    },
    stepper: {
      padding: theme.spacing(3, 0, 5)
    },
    buttons: {
      display: "flex",
      justifyContent: "flex-end"
    },
    button: {
      marginTop: theme.spacing(3),
      marginLeft: theme.spacing(56)
    },
    formControl: {
      margin: theme.spacing(1),
      minWidth: 300,
      marginTop: "-45px",
      marginLeft: "230px"
    },
    right: {
      float: "right",
      marginRight: "5px",
      marginTop: "-11px"
    },
    top: {
      marginTop: "20px"
    },
    rightExtra: {
      float: "right",
      marginTop: "-32px"
    },
    someCss: {
      marginTop: "30px",
      marginBottom: "10px",
      marginLeft: "23px"
    }
  }));
  const classes = useStyles();

  const [firstName, setFirstName] = React.useState("");
  const [lastName, setLastName] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [event, setevent] = React.useState("");
  const [eventList, setEventList] = React.useState([]);

  useEffect(() => {
    axios.get('http://54.172.164.131:8080/events').then(
    response => {
      const eventIds = [];
     if(response.data){
       console.log(response.data);
       response.data.events.map(element => {
        eventIds.push({'eventId': element.eventId,
                       'name': element.name});
       });
       console.log(eventIds);
       setEventList(eventIds);
     }
    }
    ,error => {
      console.log('error');
    }
  )},[]);
  const notify = () => {toast("Succesfully checked in!"); console.log("succes")};
  const handleNext = () => {
    axios.post("http://54.172.164.131:8080/checkIn", {
        'eventId': event,
        'email': email
      })
      .then(
        response => {
          notify();
          console.log("success");
        },
        error => {
          console.log(error);
        }
      );
  };
  return (
    <React.Fragment>
      <main className={classes.layout}>
        <Paper className={classes.paper}>
          <Typography variant="h6" gutterBottom>
            Volunteer Checkin
          </Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6}>
              <TextField
                required
                id="firstName"
                name="firstName"
                label="First name"
                fullWidth
                autoComplete="fname"
                value={firstName}
                onChange={event => {
                  event.preventDefault();
                  setFirstName(event.target.value);
                }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                required
                id="lastName"
                name="lastName"
                label="Last name"
                fullWidth
                autoComplete="lname"
                value={lastName}
                onChange={event => {
                  event.preventDefault();
                  setLastName(event.target.value);
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                id="email1"
                name="email1"
                label="Email Address"
                fullWidth
                autoComplete="email1"
                value={email}
                onChange={event => {
                  event.preventDefault();
                  setEmail(event.target.value);
                }}
              />
            </Grid>
            <div className={classes.someCss}>
              <Typography variant="h7" gutterBottom>
                Select Appropriate Event
              </Typography>
              <FormControl className={classes.formControl}>
                <InputLabel htmlFor="event">Event </InputLabel>
                <Select
                  value={event}
                  onChange={event => {
                    event.preventDefault();
                    setevent(event.target.value);
                  }}
                >
                  {
                    eventList.map(element => (
                    <MenuItem value={element.eventId}>{element.name}</MenuItem>
                  ))
                  }
                </Select>
              </FormControl>
            </div>
          </Grid>
          <Button
            variant="contained"
            color="primary"
            onClick={handleNext}
            className={classes.button}
          >
            Submit
          </Button>
        </Paper>
      </main>
    </React.Fragment>
  );
}
