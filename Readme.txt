----------------Readme.txt--------------------
Steps to run the system: 
1.Donwload the solution. 
2.Go inside the terminal and cd to client directory.
3.Run,docker build -t demo-frontend:v1 .
& docker run -it -p 3000:80 demo-frontend:v1
4.Go to manager directory and run mvn clean install. 
5.Run ,docker build -t demo-backend:v1 .
6.Run,docker run -it -p 3000:80 demo-backend:v1
7.Solution is up and running



--------------Functionality-----------
1.The user lands on registeration form, where multiple categories of people can register.
2.This forms helps the company to understand the user base and their inputs/requirements.
3.There is also an admin part of the application, where the admins can create or see the events.
4.This application can also be  used to faciliatte volunteer checkin at the events.



----------------Donor App-----------------
Steps to get Adhoc IPA.
1.User must provide UDID which needs to be added to the developer account.
2.Then they can install the IPA through Xcode or ITunes.

Steps for distribution:
1.The app can be deployed to App store using developer account 
2.This can be further downloaded from there for usage.

----------------Database------------------
1.	The Backend system was designed to service registration(for Volunteers, Donor and people needing help.),
 check Ins for events, donations sourcing from donor via mobile application. Providing donors with a token of appreciation and a record of their donation(for tax benefits etc),
 managing Volunteers for events, easy RSVP for volunteers. This is a solution which is more than a MVP and can be used very soon by the NPO. It utilizes Spring Security for
 authentication.The system utilizes MongoDB as its backend database.