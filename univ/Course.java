// Package class
package univ;

// Import necessary libraries
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Course implements Serializable
{
    // Declare necessary fields
    private String code, title;
    private double credit;
    private ArrayList<Course> prerequisites;
    private String semesterOffered, prefix;
    private int number;
    
    public Course()
    {
        code = "";
        title = "";
        credit = 0;
        prerequisites = new ArrayList<Course>();
        semesterOffered = "";
        prefix = "";
        number = 0;
    }

    // Deep copy constructor
    public Course(Course toCopy)
    {
        code = toCopy.getCourseCode(); 
        title = toCopy.getCourseTitle();
        credit = toCopy.getCourseCredit();
        prerequisites = new ArrayList<Course>(toCopy.prerequisites); 
        semesterOffered = toCopy.getSemesterOffered();
        prefix = toCopy.getCoursePrefix();
        number = toCopy.getCourseNumber();
    }

    
    public String getCoursePrefix()
    {
        return prefix;
    }

    protected void setCoursePrefix(String coursePrefix)
    {
        if (coursePrefix != null && !coursePrefix.isEmpty())
        {
            prefix = coursePrefix;
        }
    }

    public int getCourseNumber()
    {
        return number;
    }

    protected void setCourseNumber(int courseNumber)
    {
        if (courseNumber != 0)
        {
            number = courseNumber;            
        }
    }

    public String getCourseCode()
    {
        return code;
    }

    protected void setCourseCode(String courseCode)
    {
        if (courseCode != null && !courseCode.isEmpty())
        {
            // System.out.println(courseCode);
            // If course code is valid, set it
            if (!courseCode.isEmpty() && courseCode.indexOf("*") != -1)
            {
                String[] courseCodeSplit = courseCode.split("\\*");
                setCoursePrefix(courseCodeSplit[0]);
                setCourseNumber(Integer.parseInt(courseCodeSplit[1]));
                // System.out.println(getCoursePrefix());
                // System.out.println(getCourseNumber());
                code = courseCode;
            }
            // Otherwise, print error
            else
            {
                // throw new InvalidValueException();
                System.out.println(String.format("Course code %s not set due to empty value or missing asterisk", courseCode));
            }
        }
    }
    
    public String getCourseTitle()
    {
        return title;
    }
    
    protected void setCourseTitle(String courseTitle)
    {
        if (courseTitle != null && !courseTitle.isEmpty())
        {
            title = courseTitle;
        }
    }
    
    public double getCourseCredit()
    {
        return credit;
    }
    
    protected void setCourseCredit(double credit)
    {
        if (credit != 0)
        {
            this.credit = credit;
        }
    }
    
    public ArrayList<Course> getPrerequisites()
    {
        return prerequisites;
    }

    public String getPrerequisitesAsString()
    {
        // Convert ArrayList of prerequisite Course objects to ArrayList of prerequisite course code strings
        ArrayList<String> prereqs = prerequisites.stream().map(p -> p.getCourseCode()).collect(Collectors.toCollection(ArrayList::new));

        // Format ArrayList's toString method output into colon-separated prerequisite course codes string
        String prereqsAsString = prereqs.toString().replaceAll("[,]{1}", ":").replaceAll("[\\[\\]\\s]{1}", "");

        // Return colon-separated prerequisite course codes string
        return prereqsAsString;
    }
    
    protected void setPrerequisites(ArrayList<Course> prereqList)
    {
        // Not checking for null, as prerequisites are allowed to be set to null
        prerequisites = prereqList;
    }

    public String getSemesterOffered()
    {
        return semesterOffered;
    }

    protected void setSemesterOffered(String semesterOffered)
    {
        if (semesterOffered != null && !semesterOffered.isEmpty())
        {
            this.semesterOffered = semesterOffered;
        }
    }

    @Override
    public String toString()
    {
        return String.format("%s,%s,%s,%s,%s", getCourseCode(), Double.toString(getCourseCredit()), getCourseTitle(), getSemesterOffered(), getPrerequisitesAsString());
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
        Course toCompare = (Course) other;
        return this == toCompare || (code.equals(toCompare.code) && title.equals(toCompare.title) && credit == toCompare.credit && prerequisites.equals(toCompare.prerequisites) && semesterOffered == toCompare.semesterOffered && prefix.equals(toCompare.prefix) && number == toCompare.number);
    } 
}
