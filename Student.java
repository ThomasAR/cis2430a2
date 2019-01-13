// Include necessary libraries
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Comparator;
import univ.*;

@SuppressWarnings("serial")
public class Student implements Serializable
{
    // Declare necessary fields
    private String firstName, lastName;
    private int studentNumber;
    private CourseCatalog catalog;
    private ArrayList<Attempt> coursesTaken;
    private ArrayList<Course> coursesRemaining;
    private ArrayList<Course> planOfStudy;
    private ArrayList<Attempt> planOfStudyAttempt;
    private ArrayList<Degree> degrees;

    public Student()
    {
        firstName = "";
        lastName = "";
        studentNumber = 0;
        catalog = new CourseCatalog();
        coursesTaken = new ArrayList<Attempt>();
        coursesRemaining = new ArrayList<Course>();
        setPlanOfStudy();
        degrees = new ArrayList<Degree>();
        planOfStudyAttempt = new ArrayList<Attempt>();
    }

    public String getFullName()
    {
        // If first name, last name, or both are empty strings, then trailing and leading spaces will be removed, leaving only first name, last name, or empty string as value of full name 
        return String.format("%s %s", getFirstName(), getLastName()).trim();
    }
    
    public String getFirstName()
    {
        return firstName;
    }

    protected void setFirstName(String firstName)
    {
        if (firstName != null && !firstName.isEmpty())
        {
            this.firstName = firstName.trim();
        }
    }

    public String getLastName()
    {
        return lastName;
    }

    protected void setLastName(String lastName)
    {
        if (lastName != null && !lastName.isEmpty())
        {
            this.lastName = lastName.trim();
        }
    }

    public Integer getStudentNumber()
    {
         return studentNumber;
    }
    
    protected void setStudentNumber(Integer studentNumber)
    {
        if (studentNumber != 0)
        {
            this.studentNumber = studentNumber;
        }
    }

    /**
     * @return the catalog
     */
    public CourseCatalog getCatalog() 
    {
        return catalog;
    }

    /**
     * @param catalog the catalog to set
     */
    protected void setCatalog(CourseCatalog catalog)
    {
        if (catalog != null)
        {
            this.catalog = catalog;
        }
    }

    public ArrayList<Attempt> getCoursesTaken()
    {
        return coursesTaken;
    }

    protected void setCoursesTaken(ArrayList<Attempt> coursesTaken)
    {
        if (coursesTaken != null && !coursesTaken.isEmpty())
        {
            this.coursesTaken = coursesTaken;
            setPlanOfStudy();
        }
    }

    public ArrayList<Course> getCoursesRemaining()
    {
        return coursesRemaining;
    }

    protected void setCoursesRemaining(ArrayList<Course> coursesRemaining)
    {
        if (coursesRemaining != null && !coursesRemaining.isEmpty())
        {
            this.coursesRemaining = coursesRemaining;
            setPlanOfStudy();
        }
    }

    /**
     * @return the planOfStudy
     */
    public ArrayList<Course> getPlanOfStudy() 
    {
        return planOfStudy;
    }

    /**
     * @param planOfStudy the union of coursesTaken and coursesRemaining (loop through coursesRemaining first and add all courses, then loop through coursesTaken which has Attempt objects and add their courseAttempted Course object pointer to this ArrayList if not already in ArrayList)
     */
    public void setPlanOfStudy() 
    {
        ArrayList<Course> plan = new ArrayList<Course>();
        for (Attempt attempt: getCoursesTaken())
        {
            if (!plan.contains(attempt.getCourseAttempted()))
            {
                plan.add(attempt.getCourseAttempted());
            }
        }
        for (Course course: getCoursesRemaining())
        {
            if (!plan.contains(course))
            {
                plan.add(course);
            }
        }
        planOfStudy = plan;
    }

    public ArrayList<Attempt> getPlanOfStudyAttempt()
    {
        return planOfStudyAttempt;
    }
    public void setPlanOfStudyAttempt(String semester) 
    {
        ArrayList<Attempt> plan = new ArrayList<Attempt>();
        for (Attempt attempt: getCoursesTaken())
        {
            if (!plan.contains(attempt.getCourseAttempted()))
            {
                plan.add(attempt);
            }
        }
        for (Course course: getCoursesRemaining())
        {
            Attempt newAttempt = new Attempt();
            newAttempt.setCourseAttempted(course);
            if(!semester.equals("ignore"))
            {
                newAttempt.setSemesterTaken(semester);
            }
            plan.add(newAttempt);
        }
        planOfStudyAttempt = plan;
    }

    public void buildPlanOfStudy()
    {
        ArrayList<Attempt> plan = new ArrayList<Attempt>();
        for (Attempt attempt: getCoursesTaken())
        {
            plan.add(attempt);
        }
        for (Attempt attempt2 : planOfStudyAttempt)
        {
            if(!plan.contains(attempt2))
            {
                plan.add(attempt2);
            }
        }
        planOfStudyAttempt = plan;
    }
    public ArrayList<Degree> getDegrees()
	{
		return degrees;
	}

	protected void setDegrees(ArrayList<Degree> degrees)
	{
		if (degrees != null)
		{
			this.degrees = degrees;
		}
	}

    public String getGpaOverall()
    {
        return String.format("%.2f", getCoursesTaken().stream().mapToDouble(a -> Double.parseDouble(a.getAttemptGrade())).average().getAsDouble());
    }

    public double getCreditsCompleted()
    {
        int creditsCompleted = 0;
        for (Attempt attempt: getCoursesTaken())
        {
            if (attempt.getStatus().equals("Complete"))
            {
                creditsCompleted += attempt.getCourseCredit();
            }
        }
        return creditsCompleted;
    }

    public String getRemainingCredits()
    {
        Degree mostRecentDegree = getDegrees().get(getDegrees().size() - 1);
        return String.format("%.2f", mostRecentDegree.numberOfCreditsRemaining(getPlanOfStudy()));
    }

    public String getGpaCisCourses()
    {
        ArrayList<Attempt> cisCourses = getCoursesTaken().stream().filter(a -> a.getCourseAttempted().getCoursePrefix().equals("CIS")).collect(Collectors.toCollection(ArrayList::new));

        return String.format("%.2f", cisCourses.stream().mapToDouble(a -> Double.parseDouble(a.getAttemptGrade())).average().getAsDouble());
    }

    public String getGpaLast10Courses()
    {
        ArrayList<Attempt> coursesTakenCopy = new ArrayList<Attempt>(coursesTaken); 
        coursesTakenCopy.sort(Comparator.comparing(Attempt -> Attempt.getSemesterTaken()));

        if (coursesTakenCopy.size() > 10)
        {
            coursesTakenCopy = new ArrayList<Attempt>(coursesTakenCopy.subList(0, 10));
        }
        
        return String.format("%.2f", coursesTakenCopy.stream().mapToDouble(a -> Double.parseDouble(a.getAttemptGrade())).average().getAsDouble());
    }

    public ArrayList<Course> getRequiredCoursesNotInPlanOfStudy()
    {
        Degree mostRecentDegree = getDegrees().get(getDegrees().size() - 1);
        setPlanOfStudy();
        return mostRecentDegree.remainingRequiredCourses(getPlanOfStudy());
    }
    
    public ArrayList<Course> getPrerequisitesAllPlanOfStudyCourses()
    {
        setPlanOfStudy();
        return planOfStudy.stream().flatMap(c -> c.getPrerequisites().stream()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Course> remainingRequiredCoursesAttempt()
    {
        Degree mostRecentDegree = getDegrees().get(getDegrees().size() - 1);
        ;
        return mostRecentDegree.remainingRequiredCourses(Attempt.toCourseCollection(getCoursesTaken()));
    }

    @Override
    public String toString()
    {
        return String.format("{%d,%s,%s,%s}", getStudentNumber(), getFullName(), getDegrees().toString().replaceAll("[,]{1}", ":").replaceAll("[\\[\\]\\s]{1}", ""), getPlanOfStudy().toString());
    }

    @Override
    public boolean equals(Object other)
    {
        // Return false if other is null or of different type
		if (other == null || getClass() != other.getClass())
		{
			return false;
		}

		// Otherwise, cast other and perform field comparison
        Student toCompare = (Student) other;
        return this == toCompare || (firstName.equals(toCompare.firstName) && lastName.equals(toCompare.lastName) && studentNumber == toCompare.studentNumber);
    }
}
