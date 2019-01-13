This assignment was done using pair programming, where both of us were actively coding the entire assignment together on the same computer at the same time.

LEARNING OUTCOMES



COURSE CODES MUST BE ENTERED WITH THE FORMAT [CODE]*[NUMBER], SUCH AS CIS*2500
USER STORIES IN THE STUDENT MENU
• select my degree (required choices: BCH (BComp Honours) , BCG (BComp General), optional: one other degree)
	Degree Menu -> Change Degree Option

• select a major from my degree if it offers a major
	Degree Menu -> Change Major Option

• maintain courses in my plan of study (add/remove)
	Plan of Study Menu -> Add & Remove courses by giving course code and semester

• maintain courses in my transcript, or list of courses I have taken (add/change grade/remove)
	Transcript Menu -> Add, Remove and Edit courses by giving course code and semester (or new grade)

• save my program (the plan and my transcript)
	File Menu -> Save the Student object into the Database

• view a list of *required* courses for my degree and major that are *not* represented in my plan of study nor in my transcript
	Plan of Study Menu -> View Unadded Required Courses option to show a list of courses that aren't represented

• view a list of *required* courses that are *not* represented in my transcript
	Transcript Menu -> View Unadded Required Courses option to show a list of courses that aren't represented

• view the number of credits I must add to my plan of study in order to have a plan that allows me to complete my degree.
	Plan of Study Menu -> View Unadded Required Courses option to show the number of credits

• view a list of the prerequisite courses for any required course for my degree and major
	Degree Menu -> View List of Required Courses option -> View Prerequisites of Course after inputting a course code of the course you want to see the prerequisites of

• view a list of prerequisite courses that I must take in order to take the courses currently in my plan of study.
	Plan of Study Menu -> View Prerequisites For Future Courses to see the list of prerequisites

• view the number of credits I have completed in my program
	Progress Menu -> Credits Completed Number
	OR
	Plan of Study Menu -> Number in Pie Graph for Completed Couress

• view the number of credits I have remaining to complete my program
	Progress Menu -> Credits to Complete Number

• determine if I have met the completion requirements of my chosen degree
	Progress Menu -> The text under the progress bar will show "Degree Complete"

• view my course plan, organized by the semester I have, or plan to, take the courses
	Plan of Study -> TableView at the top displaying all the courses in the plan of study -> Click on the semester column header to sort the table by Semester

• view my overall GPA
	Progress Menu -> Overall GPA Number

• view my GPA for CIS courses
	Progress Menu -> Only CIS Courses GPA Number

• view my GPA for my most recent 10 credits
	Progress Menu -> Last 10 Credits GPA



USER STORIES IN THE ADMINISTRATOR MENU
• connect to the database containing the list of courses and degrees

	Connect Menu -> Connect Button (username and password fields for demo purposes, input doesn't matter)


• maintain the list of degrees in the database (add/remove/change)

	Add Degree Menu -> Add New Degree option after inputting all the info
		Used for demo purposes only, not functional (as instructed in class)


• maintain the list of courses in the database(add/remove/change)

	Add Course Menu -> Add New Course option after inputting the info for a new course

