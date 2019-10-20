import React from "react";
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
import Typography from "@material-ui/core/Typography";
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
  }
}));

const steps = ["Enter personal Details", "Review your details"];

export default function Checkout() {
  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);
  const [firstName, setFirstName] = React.useState("");
  const [lastName, setLastName] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [address, setAddress] = React.useState("");
  const [city, setCity] = React.useState("");
  const [state, setState] = React.useState("");
  const [zip, setZip] = React.useState("");
  const [country, setCountry] = React.useState("");
  const [userType, setUserType] = React.useState("Volunteer");
  const [sex, setSex] = React.useState("");
  const [assistanceType, setAssistanceType] = React.useState("");
  const [prefferedMode, setPrefferedMode] = React.useState("");
  const [isMarried, setMarried] = React.useState("");
  const [isChildren, setChildren] = React.useState("");
  const [reason, setReason] = React.useState("");
  const [request, setRequest] = React.useState("");
  const [isalreadyRequested, setAlreadyRequested] = React.useState("");
  const [
    alreadyRequestedLocation,
    setAlreadyRequestedLocation
  ] = React.useState("");
  const [phoneNumber, setPhoneNumber] = React.useState("");
  const getForm = () => {
    function conditionAlreadyRequested() {
      if (alreadyRequestedLocation) {
        return (
          <div>
            <TextareaAutosize
              aria-label="minimum height"
              rows={3}
              placeholder="Minimum 3 rows"
            />
          </div>
        );
      }
    }
    function conditionalRender() {
      if (userType != "Volunteer" && userType != "Donor")
        return (
          <div>
            <br />
            <div>
              Married?
              <RadioGroup
                className={classes.right}
                aria-label="married"
                name="married"
                value={isMarried}
                onChange={event => {
                  event.preventDefault();
                  setMarried(event.target.value);
                }}
                row
              >
                <FormControlLabel
                  value="Yes"
                  control={<Radio color="primary" />}
                  label="Yes"
                  labelPlacement="end"
                />
                <FormControlLabel
                  value="No"
                  control={<Radio color="primary" />}
                  label="No"
                  labelPlacement="end"
                />
              </RadioGroup>
            </div>
            <div className={classes.top}>
              Children?
              <RadioGroup
                className={classes.right}
                aria-label="children"
                name="children"
                value={isChildren}
                onChange={event => {
                  event.preventDefault();
                  setChildren(event.target.value);
                }}
                row
              >
                <FormControlLabel
                  value="Yes"
                  control={<Radio color="primary" />}
                  label="Yes"
                  labelPlacement="end"
                />
                <FormControlLabel
                  value="No"
                  control={<Radio color="primary" />}
                  label="No"
                  labelPlacement="end"
                />
              </RadioGroup>
            </div>
            <FormControl className={classes.formControl}>
              <InputLabel htmlFor="reason">Reason </InputLabel>
              <Select
                value={reason}
                onChange={event => {
                  event.preventDefault();
                  setReason(event.target.value);
                }}
              >
                <MenuItem value={"Previously Homeless"}>
                  Previously Homeless
                </MenuItem>
                <MenuItem value={"Domestic violance relocation"}>
                  Domestic violance relocation
                </MenuItem>
                <MenuItem value={"Family assistance"}>
                  Family assistance
                </MenuItem>
              </Select>
            </FormControl>
            <FormControl className={classes.formControl}>
              <InputLabel htmlFor="Request">Request </InputLabel>
              <Select
                value={request}
                onChange={event => {
                  event.preventDefault();
                  setRequest(event.target.value);
                }}
              >
                <MenuItem value={"Food Box"}>Food Box</MenuItem>
                <MenuItem value={"Clothing Essentials"}>
                  Clothing Essentials
                </MenuItem>
                <MenuItem value={"Household Goods  (limited to availability)"}>
                  Household Goods (limited to availability)
                </MenuItem>
                <MenuItem value={"Furniture (limited to availability)"}>
                  Furniture (limited to availability)
                </MenuItem>
              </Select>
            </FormControl>
            <div>Have you contacted any other resources?</div>
            <RadioGroup
              aria-label="alreadyRequested"
              name="alreadyRequested"
              value={isalreadyRequested}
              onChange={event => {
                event.preventDefault();
                setAlreadyRequested(event.target.value);
              }}
              row
            >
              <FormControlLabel
                value="Yes"
                control={<Radio color="primary" />}
                label="Yes"
                labelPlacement="end"
              />
              <FormControlLabel
                value="No"
                control={<Radio color="primary" />}
                label="No"
                labelPlacement="end"
              />
            </RadioGroup>
          </div>
        );
    }
    function phonenumber() {
      if (prefferedMode == "Phone") {
        return (
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="Phone"
              name="Phone"
              label="Phone"
              value={phoneNumber}
              fullWidth
              autoComplete="billing country"
              onChange={event => {
                event.preventDefault();
                setPhoneNumber(event.target.value);
              }}
            />
          </Grid>
        );
      }
    }
    return (
      <React.Fragment>
        <Typography variant="h6" gutterBottom>
          Enter Personal Details
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
          <Grid item xs={12}>
            <TextField
              required
              id="password"
              name="password"
              label="Password"
              type="Password"
              fullWidth
              autoComplete="password"
              value={password}
              onChange={event => {
                event.preventDefault();
                setPassword(event.target.value);
              }}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="address1"
              name="address1"
              label="Address"
              fullWidth
              autoComplete="billing address-line1"
              value={address}
              onChange={event => {
                event.preventDefault();
                setAddress(event.target.value);
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="city"
              name="city"
              label="City"
              value={city}
              fullWidth
              autoComplete="billing address-level2"
              onChange={event => {
                event.preventDefault();
                setCity(event.target.value);
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="state"
              name="state"
              label="State/Province/Region"
              fullWidth
              value={state}
              onChange={event => {
                event.preventDefault();
                setState(event.target.value);
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="zip"
              name="zip"
              label="Zip / Postal code"
              value={zip}
              fullWidth
              autoComplete="billing postal-code"
              onChange={event => {
                event.preventDefault();
                setZip(event.target.value);
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="country"
              name="country"
              label="Country"
              value={country}
              fullWidth
              autoComplete="billing country"
              onChange={event => {
                event.preventDefault();
                setCountry(event.target.value);
              }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <div>Preffered Contact Method</div>
            <RadioGroup
              aria-label="category"
              name="category"
              value={prefferedMode}
              onChange={event => {
                event.preventDefault();
                setPrefferedMode(event.target.value);
              }}
              row
            >
              <FormControlLabel
                value="Email"
                control={<Radio color="primary" />}
                label="Email"
                labelPlacement="end"
              />
              <FormControlLabel
                value="Phone"
                control={<Radio color="primary" />}
                label="Phone"
                labelPlacement="end"
              />
            </RadioGroup>
          </Grid>
          {phonenumber()}
          <Grid item xs={12}>
            <div row>
              <div>Select User Type</div>
              <RadioGroup
                className={classes.rightExtra}
                aria-label="category"
                name="category"
                value={userType}
                onChange={event => {
                  event.preventDefault();
                  setUserType(event.target.value);
                }}
                row
              >
                <FormControlLabel
                  value="Volunteer"
                  control={<Radio color="primary" />}
                  label="Volunteer"
                  labelPlacement="end"
                />
                <FormControlLabel
                  value="Donor"
                  control={<Radio color="primary" />}
                  label="Donor"
                  labelPlacement="end"
                />
                <FormControlLabel
                  value="Need Help"
                  control={<Radio color="primary" />}
                  label="Need Help"
                  labelPlacement="end"
                />
              </RadioGroup>
              {conditionalRender()}
            </div>
          </Grid>
        </Grid>
      </React.Fragment>
    );
  };

  const getStepContent = step => {
    switch (step) {
      case 0:
        return getForm();
      case 1:
        return (
          <Review
            data={{
              firstName: firstName,
              lastName: lastName,
              email: email,
              address: address,
              city: city,
              state: state,
              zip: zip,
              country: country,
              userType: userType,
              sex: sex,
              assistanceType: assistanceType,
              prefferedMode: prefferedMode,
              isMarried: setMarried,
              isChildren: setChildren,
              reason: setReason,
              request: setRequest
            }}
          />
        );
      default:
        throw new Error("Unknown step");
    }
  };

  const handleNext = () => {
    setActiveStep(activeStep + 1);
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
  };

  return (
    <React.Fragment>
      <CssBaseline />
      <main className={classes.layout}>
        <Paper className={classes.paper}>
          <Typography component="h1" variant="h4" align="center">
            Register @ Zuri's
          </Typography>
          <Stepper activeStep={activeStep} className={classes.stepper}>
            {steps.map(label => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          <React.Fragment>
            {activeStep === steps.length ? (
              <React.Fragment>
                <Typography variant="h5" gutterBottom>
                  Thank you for your order.
                </Typography>
                <Typography variant="subtitle1">
                  Your order number is #2001539. We have emailed your order
                  confirmation, and will send you an update when your order has
                  shipped.
                </Typography>
              </React.Fragment>
            ) : (
              <React.Fragment>
                {getStepContent(activeStep)}
                <div className={classes.buttons}>
                  {activeStep !== 0 && (
                    <Button onClick={handleBack} className={classes.button}>
                      Back
                    </Button>
                  )}
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={handleNext}
                    className={classes.button}
                  >
                    {activeStep === steps.length - 1 ? "Submit" : "Next"}
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
