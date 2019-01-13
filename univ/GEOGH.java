// Package class
package univ;

// Import necessary variables
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

/**
 * A class that inherits from General, which inherits from Degree.
 * It contains the requirements and properties of a degree to be used 
 * by University, PlanOfStudy, and Planner to determine a student's
 * progress and ability to graduate.
 *
 * @author Reza Parnian
 * @version 2018.10.25
 */
@SuppressWarnings("serial")
public class GEOGH extends BAH implements Serializable
{
	// Declare necessary fields
    private static final ArrayList<String> requiredCourseCodes = new ArrayList<String>(Arrays.asList("GEOG*1200","GEOG*1220","GEOG*1300","GEOG*2000","GEOG*2110","GEOG*2210","GEOG*2230","GEOG*2260","GEOG*2460","GEOG*2480","GEOG*3480","GEOG*4880"));
	private static final ArrayList<String> approvedGeogElectives = new ArrayList<String>(Arrays.asList("ENVS*2030", "ENVS*2060", "ENVS*4220", "GEOL*2150", "MET*2030", "SOIL*2010"));
	private static final double minCreditsRequired = 6;
    private static final double minCreditsGeog = 9;
    private static final double minCreditsGeogElective3000 = 2;
    private static final double minCreditsGeogElective4000 = 1.5;
	
	/**
     * Zero parameter constructor for objects of class GEOGH.
     */
	public GEOGH()
    {
		System.out.println("Unable to initialize degree due to no course catalog being provided");
	}

	public GEOGH(CourseCatalog catalog)
	{
		super();
		setDegreeMajor("GEOGH");
		setCatalog(catalog);
		setRequiredCourseCodes(requiredCourseCodes);
	}

	public ArrayList<String> getRequiredCourseCodes()
	{
		return requiredCourseCodes;
	}

	/**
	 * @param thePlan The collection of courses representing student's progress.
     * @return Whether a student can graduate with current state of progress.
     */
	public boolean meetsRequirements(ArrayList<Course> allTheCoursesPlannedAndTaken)
	{
        // Declare counter variables
		double creditsTotal = 0, creditsRequired = 0, creditsGeog = 0, creditsGeogElective3000 = 0, creditsGeogElective4000 = 0;
	
		// Count all other required credits as per degree requirements (course status is assumed to be Complete in order to allow ArrayList of Course rather than Attempt objects to be inputted)
		for (Course course: allTheCoursesPlannedAndTaken)
		{
			if (getRequiredCourses().contains(course))
			{
				creditsRequired += course.getCourseCredit();
			}

            if (course.getCoursePrefix().equals("GEOG")) 
            {
				creditsGeog += course.getCourseCredit();

				if (!getRequiredCourses().contains(course))
				{
					if (approvedGeogElectives.contains(course))
					{
						if (course.getCourseNumber() == 3000)
						{
							creditsGeogElective3000 += course.getCourseCredit();
						}
						else if (course.getCourseNumber() == 4000)
						{
							creditsGeogElective4000 += course.getCourseCredit();
						}
					}
				}
			}

			creditsTotal += course.getCourseCredit();
		}
		
		return (
			creditsTotal >= getMinCreditsTotal() && 
			creditsRequired >= minCreditsRequired &&
			creditsGeog >= minCreditsGeog &&
			creditsGeogElective3000 >= minCreditsGeogElective3000 &&
			creditsGeogElective4000 >= minCreditsGeogElective4000
		);
	}

	/**
     * @return Whether this GEOGH object is same as other GEOGH object.
     */
	@Override
    public boolean equals(Object other)
    {
		// Return false if other is null or of different type
		if (other == null || getClass() != other.getClass())
		{
			return false;
		}

		// Otherwise, cast other and perform field comparison
        GEOGH toCompare = (GEOGH) other;
		return (
			this == toCompare || 
			(getDegreeTitle().equals(toCompare.getDegreeTitle()) && getDegreeMajor().equals(toCompare.getDegreeMajor()) && getCatalog().equals(toCompare.getCatalog()) && getRequiredCourses().equals(toCompare.getRequiredCourses()))
		);
	}
}
