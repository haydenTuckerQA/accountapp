 # accountapp
## Overview
This project is a very simple account management application. Those with an account are split into two groups 'USERS' and 'ADMINS'. 

A single USER and ADMIN are inserted into the database using an sql script with the usernames 'user' and 'admin' respectively and the password 'password'. These can be used to log in to the website.

### USER
User's are able to;
* Register an account with a unique username
* View the details of their own account
* Edit the details of their own account
* Delete their own account

### ADMIN
Admin's are able to;
* Register new admins
* Delete their own account and the accounts of other admins
* Edit the details of any user account
* Delete any user account
* View all accounts stored by the application

## Technologies used
Below is a list of the different technologies used in this application and a justifcation for each ones use.

1. JavaEE
    1. JavaEE allows us to build full web applications and work with each layer within enterprise applications in one easy to manage package using JAVA. 
    2. JAVA is my preffered language of choice.
2. JUnit
    1. JUnit tests allow us to implement TDD which is recommended to help build more reliable applications.
    2. Combined with Maven ensures we are less likely to deploy broken code as MAVEN will not build the prooject if a unit test were to suddenly fail.
    3. Allow us to isolate where potential errors are occuring in our code faster.
3. Maven
    1. Maven provides relatively simple dependency management for our application.
    2. Maven builds and packages the application in a way that we can easily deploy it to an application server (.war)
4. SQL Database
    1. Account information needed to be persisted throughout the applications lifecycle, for this a database made more sense compared to storing the Accounts in something like a list which would then need to be written to a file. Databases allow us to store data more securely and in more efficient ways (relational databases).
5. Wildfly
    1. The application needed to be deployed to the web and wildfly allows us to do this in a very small number of steps.
    2. Wildfly provides useful logging information that allows us to narrow down the cause of errors faster.
6. HTML/CSS/JS (BOOTSTRAP)
    1. HTML/CSS/JS are a standard choice for front-end development.
    2. BOOTSTRAP allows us to more quickly build the front end of our websites in a manner that scales across a multitude of devices (desktops/mobiles) rather than building different websites completely from scratch that often share key functional requirements (i.e. must work across multiple browsers, must scale appropriately across different sized devices). 
7. JQuery
    1. JQuery simplifies javascript allowing us to build functioning websites faster.
    2. Specifically for this application JQuery provides AJAX which allows us to easily send GET/POST/DELETE/PUT etc. requests to a specific uri. Given the JavaEE application is exposed via this sort of interface JQuery seemed like a logical choice.


## Future improvements
Below I have included a list of improvements I would have made to the application had I had more time;
1. Passwords are currently stored in plain text in the database as well as passed around between the front/backends without any form of encryption. This is a very obvious flaw and any/all passwords should never be viewable to anybody other than the application. Building off of this point logins have been implemented in a very primitive way (user enters username/password, these are sent and compared within the database, if a match is found the user is logged in). Had I had more time I would have implemented a more secure J2EE/JavaEE form-based login, utilising session beans for user logins and storing the passwords in a more secure way. The application was created in it's current state as I made the decision the key functionality was more important at this moment in time than the security which was outside the scope of the project, I did however want to implement this feature myself and simply ran out of time due to illness.
2. Better error handling. A number of places in the application have poor means of reflecting what went wrong back to the user. I.e. if an admin is created with an existing username then the resulting http 500 is displayed directly to the user, when in reality what should happen is a warning is displayed to the user that the username they enterred is not available and to try again.
