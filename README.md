# CIS2430: Object Oriented Programming Assignment 2 #

This project was made for the University of Guelph and achieved a grade of 96%

For CIS2430 in Fall 2018, we were given two assignments, the second of which built off of the first. The assignments were to build a degree planning system for a few select degrees at the University of Guelph including BComp. General, BComp Honours: Computer Science, BComp Honours: Software Engineering, BA Honours: Theatre Studies, and BA Honours: Geography.
Some requirements for this program included viewing a list of required courses for degrees, adding/removing/editing courses in a student's transcript, adding/removing/viewing unadded required courses in a student's plan of study, and viewing the student's progress.
Administration functions of the program included connecting to a database of all available courses and degrees, as well as adding new courses and degrees to the database.
The assignments were to be made with Java, and we chose to use JavaFX instead of Swing to create the GUI. 

![Catalog](https://i.imgur.com/9mTddFk.png)
The catalog involved connecting to a database and retrieving information about each course. My partner and I populated the database ourselves using a CSV file created by running a python script to scrape information about each course from the university website. We appended every possible combination of course codes to the link 'https://www.uoguelph.ca/registrar/calendars/undergraduate/2018-2019/courses/' and retrieved the necessary information (code, title, credit, preprequisites, semester(s) offered). The catalog contains all 1746 courses available at the University of Guelph.

![Degree Courses](https://i.imgur.com/ZdpbZM6.png)
![Course Prerequisites](https://i.imgur.com/HouIkTU.png)

The program provided the ability to choose a degree and major, and among other features, view the required courses to graduate with that degree. An additional feature was being able to view the prerequisite courses for any required course in your degree.

![Transcript](https://i.imgur.com/7r2psAT.png)

The program was able to add, remove, and edit courses from a student's transcript. Any courses added to the transcript would be automatically added to the plan of study.

![Plan of Study](https://i.imgur.com/MajI6Cb.png)

The plan of study included courses that the user planned to take in future semesters, as well as their previously completed courses. The animated pie graph displayed the number of credits completed, and the number of credits remaining to satisfy the degree requirements.

## To run the program (from the README) ##

On Windows systems, you can run the following command from a command prompt window opened in the root directory of the project:
java -cp .;database/mysql-connector-java-8.0.13.jar Planner

On Linux or UNIX systems, you can use the provided makefile in the root directory to build the project with the “make build” command and to run it with the “make run” command with a terminal window opened in the root directory of the project.

**The database will only function correctly if ran on the University of Guelph network or through the University of Guelph VPN**
