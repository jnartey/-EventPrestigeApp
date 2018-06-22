# Event Prestige

Event Prestige is an event container that can host all types events including Weddings, baby shower, etc. With event prestige a user will have a personal space or page to host or add events and galleries for each event.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Apache Tomcat server 9
* Eclipse IDE
* Java 8 SDK * JRE
* Oracle 12c Database
* JUnit 4

### Installing

1.	Import event_gallery.sql to Oracle Databse
2.	Edit db.properties file in the ep.event.resources package with your oracle database connection details


## Running the tests

All tests must be run with Junit 4. 
All test cases are located here
```
EventPrestigeApp/Java Resources/test for Eclipse IDE
EventPrestigeApp/test for system explorer
```

### JUnit 4 tests (Unit testing for each DAO methods)

All tests was done on only the DAOs, 
purpose of tests was to test if all methods in the DAOs perfomed basic CRUD

```
Run each test classes as JUnit test
```

## Deployment

Export EventPrestigeApp as WAR file using Eclipse IDE, Upload to Tomcat Server 9 with Oracle 12c Database

## Known Bugs

* Pages do not redirect to login page when session expires or does not exist 
* No Spring security

## Uncompleted

* Event Wishlist front end and backend
* User Profile front end (About us page) and backend
* Contact us front end

## Problems faced

* Image upload, resizing and cropping
* 

## Built With

* [Java](https://java.com/en/) - Programming language
* [Spring mvc](https://spring.io/) - Spring framework 5.0
* [JSP](http://www.oracle.com/technetwork/java/index-jsp-138231.html) - Java Server Pages
* JUNIT 4 - Test
* [Maven](https://maven.apache.org/) - Dependency Management
* [Foundation](https://foundation.zurb.com/) - The most advanced responsive front-end framework in the world.
* [Fancybox 3](https://fancyapps.com/fancybox/3/) - JavaScript lightbox library
* [DropzoneJS](http://www.dropzonejs.com/) - open source library that provides drag’n’drop file uploads with image previews.
* [Datatables](https://datatables.net/) - Advanced tables, instantly
* [JQuery Confirm](https://craftpip.github.io/jquery-confirm/) - alerts, confirms and dialogs in one.
* Layerslider 6 - Advanced image slider
* [Font Awesome](https://fontawesome.com/) - Free font icons
* [JavaXT Image Class](http://www.javaxt.com/documentation/?jar=javaxt-core&package=javaxt.io&class=Image) - Image resize & cropping plugin
* [Intimidatetime](http://trentrichardson.com) - JQuery datepicker
* [Datepicker](https://fengyuanchen.github.io/datepicker) - JQuery datepicker plugin


## User Stories

1.	As a user I can sign up
2.	As a user I can login with my email and password
3.	As a user I can create a user profile page and add pictures
4.	As a user I can update my user profile page
5.	As a user I can add events
6.	As a user I can update events
7.	As a user I can delete events
8.	As a user I can add galleries to events
9.	As a user I can upload images to galleries
10.	As an admin I can delete users (Not done next sprint)
11.	As an admin I can block users (Not done next sprint)
12.	As an admin I can view users pages (Not done next sprint)


## Authors

* **Jacob Nartey** - *Development* - (https://github.com/jnartey)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to all the lecturers who taught me at Perscholas Java Developer Training Course
* Thank you all
