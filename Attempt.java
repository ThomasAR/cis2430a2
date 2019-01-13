// Include necessary libraries
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import univ.*;

@SuppressWarnings("serial")
public class Attempt implements Serializable
{
    // Declare necessary fields
    private Course courseAttempted;
    private String status;
    private String attemptGrade;
    private String semesterTaken;

    public Attempt()
    {
        courseAttempted = new Course();
        status = "";
        attemptGrade = ""; 
        semesterTaken = "";
    }

    public Course getCourseAttempted()
    {
        return courseAttempted;
    }

    protected void setCourseAttempted(Course theCourse)
    {
        if (theCourse != null)
        {
            courseAttempted = theCourse;
        }
    }

    public String getStatus()
    {
        return status;
    }

    protected void setStatus(String status)
    {
        if (status != null && !status.isEmpty())
        {
            this.status = status;
        }
    }

    public String getAttemptGrade()
    {
        return attemptGrade;
    }

    // a number, P, F, INC or MNR
    protected void setAttemptGrade(String grade)
    {
        if (grade != null && !grade.isEmpty())
        {
            if (grade.matches("^([0-9]{1,3}|[0-9]{1,3}.[0-9]{1,2}|P|F|INC|MNR)$"))
            {
                attemptGrade = grade;
            }
            else
            {
                System.out.println(String.format("Unable to set grade for course %s due to invalid value", getCourseAttempted().getCourseCode()));
            }
        }
        else
        {
            System.out.println(String.format("Unable to set grade for course %s due to missing value", getCourseAttempted().getCourseCode()));
        }
    }

    public String getSemesterTaken()
    {
        return semesterTaken;
    }

    protected void setSemesterTaken(String semesterTaken)
    {
        if (semesterTaken != null && !semesterTaken.isEmpty())
        {
            if (semesterTaken.matches("[FWB]{1}[0-9]{2}"))
            {
                this.semesterTaken = semesterTaken;
            }
            else
            {
                System.out.println(String.format("Unable to set semester taken for course %s due to invalid value (only F, W, and B semesters are currently open for enrollment)", getCourseAttempted().getCourseCode()));
            }
        }
        else
        {
            System.out.println(String.format("Unable to set semester taken for course %s due to missing value", getCourseAttempted().getCourseCode()));
        }
    }

    public static ArrayList<Course> toCourseCollection(ArrayList<Attempt> attemptCollection)
    {
        return attemptCollection.stream().map(a -> a.getCourseAttempted()).collect(Collectors.toCollection(ArrayList::new));
    }

    public String getCourseCode()
	{
		return getCourseAttempted().getCourseCode();
    }
    
    public Double getCourseCredit()
	{
		return getCourseAttempted().getCourseCredit();
	}

	public String getCourseTitle()
	{
		return getCourseAttempted().getCourseTitle();
    }
    
    public String getSemesterOffered()
	{
		return getCourseAttempted().getSemesterOffered();
    }

    public ArrayList<Course> getPrerequisites()
	{
		return getCourseAttempted().getPrerequisites();
    }

    public String getPrerequisitesAsString()
	{
		return getCourseAttempted().getPrerequisitesAsString();
    }

    @Override
    public String toString()
    {
        return String.format("%s,%s,%s,%s,%s,%s,%s", getCourseCode(), Double.toString(getCourseCredit()), getCourseTitle(), getSemesterOffered(), getSemesterTaken(), getAttemptGrade(), getPrerequisitesAsString());
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
        Attempt toCompare = (Attempt) other;
        return this == toCompare || (courseAttempted.equals(toCompare.courseAttempted) && attemptGrade.equals(toCompare.attemptGrade) && semesterTaken == toCompare.semesterTaken);
    }
}
