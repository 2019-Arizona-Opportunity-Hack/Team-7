import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Grid from "@material-ui/core/Grid";

const products = [
  {
    name: "Product 1",
    desc: "A nice thing",
    price: "$9.99"
  },
  {
    name: "Product 2",
    desc: "Another thing",
    price: "$3.45"
  },
  {
    name: "Product 3",
    desc: "Something else",
    price: "$6.51"
  },
  {
    name: "Product 4",
    desc: "Best thing of all",
    price: "$14.11"
  },
  {
    name: "Shipping",
    desc: "",
    price: "Free"
  }
];
const addresses = [
  "1 Material-UI Drive",
  "Reactville",
  "Anytown",
  "99999",
  "USA"
];
const payments = [
  {
    name: "Card type",
    detail: "Visa"
  },
  {
    name: "Card holder",
    detail: "Mr John Smith"
  },
  {
    name: "Card number",
    detail: "xxxx-xxxx-xxxx-1234"
  },
  {
    name: "Expiry date",
    detail: "04/2024"
  }
];

const useStyles = makeStyles(theme => ({
  listItem: {
    padding: theme.spacing(1, 0)
  },
  total: {
    fontWeight: "700"
  },
  title: {
    marginTop: theme.spacing(2)
  }
}));

export default function Review({ data }) {
  const classes = useStyles();
  console.log(data);
  const isPhone = () => {
    if (data.prefferedMode == "Phone") {
      return (
        <div>
          <Typography gutterBottom> Phone: {data.phone} </Typography>{" "}
        </div>
      );
    }
  };
  const isPresentData = () => {
    if (data.userType != "Volunteer" && data.userType != "Donor") {
      return (
        <div>
          <Typography gutterBottom> User Type: {data.userType} </Typography>{" "}
          <Typography gutterBottom> is Married: {data.isMarried} </Typography>{" "}
          <Typography gutterBottom>
            {" "}
            Has Childern: {data.isChildren}{" "}
          </Typography>{" "}
          <Typography gutterBottom> Reason: {data.reason} </Typography>{" "}
          <Typography gutterBottom> Request: {data.request} </Typography>{" "}
        </div>
      );
    }
  };
  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom>
        Please review the entered details.{" "}
      </Typography>{" "}
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Typography gutterBottom>
            {" "}
            Name: {data.firstName + " " + data.lastName}{" "}
          </Typography>{" "}
          <Typography gutterBottom> Email: {data.email} </Typography>{" "}
          <Typography gutterBottom> Address: {data.address} </Typography>{" "}
          <Typography gutterBottom> City: {data.city} </Typography>{" "}
          <Typography gutterBottom> City: {data.zip} </Typography>{" "}
          <Typography gutterBottom> State: {data.state} </Typography>{" "}
          <Typography gutterBottom> Country: {data.country} </Typography>{" "}
          <Typography gutterBottom>
            {" "}
            prefferedMode: {data.prefferedMode}{" "}
          </Typography>{" "}
          {isPhone()} {isPresentData()}{" "}
        </Grid>{" "}
      </Grid>{" "}
    </React.Fragment>
  );
}
