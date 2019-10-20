import React, { useState } from 'react';
import { makeStyles } from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import Paper from "@material-ui/core/Paper";
import Button from "@material-ui/core/Button";
import Link from "@material-ui/core/Link";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Checkbox from '@material-ui/core/Checkbox';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import axios from 'axios';

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright © "}
            <Link color="inherit" href="https://material-ui.com/">
                Your Website
      </Link>{" "}
            {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

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
        marginLeft: theme.spacing(1)
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 150
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
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    }
}));

const steps = ["Enter Event Details"];

export default function CreateEvent() {
    const classes = useStyles();
    const [activeStep, setActiveStep] = React.useState(0);
    const [name, setName] = React.useState('');
    const [date, setDate] = useState(new Date());
    const [duration, setDuration] = React.useState('');
    const [location, setLocation] = React.useState('');
    const [sendEmails, setSendEmails] = React.useState({
        volunteers: false,
        donors: false
    });

    const handleSubmit = () => {
        axios.post('http://54.172.164.131:8080/event', {
            'location': location,
            'name': name,
            'date': date,
            'duration': duration
        })
            .then((response) => {
                console.log(response);
            }, (error) => {
                console.log(error);
            });
    }

    const handleChange = name => event => {
        setSendEmails({ ...sendEmails, [name]: event.target.checked });
    };


    const getStepContent = () => {
        return (
            <React.Fragment>
                
                    <Grid container spacing={3}>
                        <Grid item xs={12} md={6}>
                            <TextField required id="name" label="Event name" fullWidth
                                value={name} onChange={event => {
                                    event.preventDefault();
                                    setName(event.target.value);
                                  }} />
                        </Grid>
                        <Grid item xs={12} md={6}>
                            <TextField required id="location" label="Event Location" fullWidth
                                value={location} 
                                onChange={event => {
                                    event.preventDefault();
                                    setLocation(event.target.value);
                                  }} />
                        </Grid>
                        <Grid item xs={6} md={6}>
                            <TextField
                                id="date"
                                label="Event Date"
                                type="date"
                                defaultValue="2019-10-19"
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                value={date} onChange={event => {
                                    event.preventDefault();
                                    setDate(event.target.value);
                                  }}
                            />
                            </Grid> 
                        <Grid item xs={12} md={6}>
                            <TextField required id="duration" label="Event duration" fullWidth
                                value={duration} onChange={event => {
                                    event.preventDefault();
                                    setDuration(event.target.value);
                                  }} />
                        </Grid>
                        <Grid item xs={12} md={6}>
                            <Typography variant="h6" gutterBottom>
                                Send Emails to:
                             </Typography>
         <FormGroup row>
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        checked={sendEmails.volunteers}
                                        onChange={handleChange('volunteers')}
                                        value="volunteers"
                                        inputProps={{
                                            'aria-label': 'primary checkbox',
                                        }}
                                    />
                                }
                                label="Volunteers"
                            />

                            <FormControlLabel
                                control={
                                    <Checkbox
                                        lable="Donors"
                                        checked={sendEmails.donors}
                                        onChange={handleChange('donors')}
                                        value="donors"
                                        color="primary"
                                        inputProps={{
                                            'aria-label': 'secondary checkbox',
                                        }}
                                    />
                                }
                                label="Donors"
                            />
</FormGroup>
                        </Grid>

                    </Grid>
                
            </React.Fragment>
        );
    }

    return (
        <React.Fragment>
            <CssBaseline />
            <main className={classes.layout}>
                <Paper className={classes.paper}>
                    <Typography component="h1" variant="h4" align="center">
                        Create Event @ Zuri's
          </Typography>

                    <React.Fragment>
                        {activeStep === steps.length ? (
                            <React.Fragment>
                                <Typography variant="h5" gutterBottom>
                                    Looks Good. Your event creation was successfull!
                </Typography>
                                <Typography variant="subname1">
                                    Your event has been created. We have emailed your event creation
                                    confirmation.
                </Typography>
                            </React.Fragment>
                        ) : (
                                <React.Fragment>
                                    {getStepContent()}
                                    <div className={classes.buttons}>
                                        <Button
                                            variant="contained"
                                            color="primary"
                                            onClick={handleSubmit}
                                            className={classes.button}
                                        >
                                            Submit
                                    </Button>
                                    </div>
                                </React.Fragment>
                            )}
                    </React.Fragment>
                </Paper>
                <Copyright />
            </main>
        </React.Fragment>
    );
}
