// Package class
package univ;

// Import necessary libraries
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Degree implements Serializable
{
    // Declare necessary fields
	private String title;
	private double minCreditsTotal;
	private String major;
	private CourseCatalog catalog;
	private ArrayList<Course> requiredCourses;
	private static ArrayList<String> requiredCourseCodes;
    protected static final ArrayList<String> approvedScienceElectivePrefixes = new ArrayList<String>(Arrays.asList("AGR", "ANSC", "BIOC", "BIOL", "BIOM", "BOT", "CHEM", "CIS", "CROP", "ECON", "ENGG", "ENVB", "ENVS", "ENVS", "EQN", "FARE", "FOOD", "GEOG", "GEOL", "HORT", "HK", "IBIO", "IPS", "MATH", "MBG", "MCB", "MICR", "NANO", "NEUR", "NUTR", "OAGR", "PATH", "PBIO", "PHYS", "POPM", "PSYC", "SOIL", "STAT", "TOX", "UNIV", "XSEN", "ZOO"));
    protected static final ArrayList<String> approvedArtsOrSocialScienceElectivePrefixes = new ArrayList<String>(Arrays.asList("ACCT", "AGR", "ANSC", "ARAB", "ANTH", "ASCI", "ARTH", "BUS", "CHIN", "CIS", "CLAS", "ECON", "ENGL", "EDRD", "ENVS", "EURO", "FARE", "FREN", "FRHD", "GEOG", "GERM", "GREK", "HISP", "HIST", "HUMN", "HTM", "HROB", "IDEV", "ISS", "ITAL", "LARC", "LAT", "LING", "MCS", "MGMT", "MUSC", "NUTR", "OAGR", "PHIL", "POLS", "PORT", "PSYC", "REAL", "SART", "SOAN", "SOC", "SPAN", "THST", "UNIV", "WMST"));
    private static final ArrayList<String> availableTitles = new ArrayList<String>(Arrays.asList("BCG", "BCH", "BAH"));

	public Degree() 
	{
		title = "";
		minCreditsTotal = 0;
		major = "";
		catalog = new CourseCatalog();
		requiredCourses = new ArrayList<Course>();
		requiredCourseCodes = new ArrayList<String>();
	}

    public String getDegreeTitle()
    {
        return title;
    }

    protected void setDegreeTitle(String title)
    {
        if (title != null && !title.isEmpty() && availableTitles.contains(title))
        {
            this.title = title;
        }
	}
	
	public double getMinCreditsTotal()
	{
		return minCreditsTotal;
	}

	protected void setMinCreditsTotal(double minCreditsTotal)
	{
		if (minCreditsTotal != 0)
		{
			this.minCreditsTotal = minCreditsTotal;
		}
	}

	public abstract String getDegreeMajor();

	protected abstract void setDegreeMajor(String major);

    /**
     * @return The collection of courses available at the university.
     */
	public CourseCatalog getCatalog()
	{
		return catalog;
	}

	/**
     * Set the collection of available courses for this Degree object.
     * @param availableCourses The collection of available courses to be set.
     */
	protected void setCatalog(CourseCatalog catalog)
	{
		if (catalog != null)
		{
			this.catalog = catalog;
		}
	}
	
	public String getRequiredCoursesAsString()
	{
		// Convert ArrayList of required Course objects to ArrayList of required course code strings
        ArrayList<String> reqCourses = getRequiredCourses().stream().map(r -> r.getCourseCode()).collect(Collectors.toCollection(ArrayList::new));

        // Format ArrayList's toString method output into colon-separated required course codes string
        String reqCoursesAsString = reqCourses.toString().replaceAll("[,]{1}", ":").replaceAll("[\\[\\]\\s]{1}", "");

        // Return colon-separated required course codes string
        return reqCoursesAsString;
	}

    /**
     * @return The list of courses required to graduate with this degree.
     */
	public ArrayList<Course> getRequiredCourses()
	{
		return requiredCourses;
	}

	/**
	 * @param listOfRequiredCourses The collection of required courses to graduate with this degree.
     */
	protected void setRequiredCourses(ArrayList<Course> listOfRequiredCourses)
	{
		if (listOfRequiredCourses != null && !listOfRequiredCourses.isEmpty())
		{
			requiredCourses = listOfRequiredCourses;
		}
	}

	public abstract ArrayList<String> getRequiredCourseCodes();

	/**
	 * @param listOfRequiredCourseCodes The collection of required courses to graduate with this degree.
     */
	protected void setRequiredCourseCodes(ArrayList<String> listOfRequiredCourseCodes)
	{
		if (listOfRequiredCourseCodes != null && !listOfRequiredCourseCodes.isEmpty())
		{
			for (String requiredCourseCode: listOfRequiredCourseCodes)
			{
				Course copiedCourse = getCatalog().deepFindCourse(requiredCourseCode);
				// System.out.println(copiedCourse);
				// If course found, add it to Arraylist requiredCourses
				if (copiedCourse != null)
				{
					requiredCourses.add(copiedCourse);
				}
				// Otherwise, create new Course object with only course code and add it
				else
				{
					System.out.println(String.format("Unable to add required course %s for degree %s in %s, as it is not in university course catalog", requiredCourseCode, getDegreeTitle(), getDegreeMajor()));
				}
			}
		}
    }

    /**
	 * @param allTheCoursesPlannedAndTaken The collection of courses representing student's progress.
     * @return Number of credits remaining for student to graduate.
     */
	public double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken)
	{
		double totalCredits = 0;
		for (Course course: allTheCoursesPlannedAndTaken)
		{
			totalCredits += course.getCourseCredit();
		}

		// Return 0 if requirements met, else number of credits remaining
		if (meetsRequirements(allTheCoursesPlannedAndTaken))
		{
			return 0;
		}
		else
		{
			return minCreditsTotal - totalCredits;
		}
	}

    /**
	 * @param allTheCoursesPlannedAndTaken The collection of courses representing student's progress.
     * @return The list of required courses remaining for student to graduate. 
     */
	public ArrayList<Course> remainingRequiredCourses(ArrayList<Course> allTheCoursesPlannedAndTaken)
	{
		ArrayList<Course> remainingRequiredCourses = new ArrayList<Course>(getRequiredCourses());

		for (Course course: allTheCoursesPlannedAndTaken)
		{
			if (remainingRequiredCourses.contains(course))
			{
				remainingRequiredCourses.remove(course);
			}
		}
		
		return remainingRequiredCourses;
	}

	public abstract boolean meetsRequirements(ArrayList<Course> allTheCoursesPlannedAndTaken);

	/**
     * @return The string literal representation of current Degree object.
     */
	@Override
	public String toString()
	{
		return String.format("%s:%s,%s,%s", getDegreeTitle(), getDegreeMajor(), Double.toString(getMinCreditsTotal()), getRequiredCoursesAsString());
	}

    @Override
    public abstract boolean equals(Object other);
}
