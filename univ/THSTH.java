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
public class THSTH extends BAH implements Serializable
{
	// Declare necessary fields
	private static final ArrayList<String> requiredCourseCodes = new ArrayList<String>(Arrays.asList("THST*1040", "THST*1150", "THST*2010", "THST*2230", "THST*3550", "THST*3850", "THST*4280"));
	private static final ArrayList<String> specialStudiesCourses = new ArrayList<String>(Arrays.asList("THST*3410", "THST*3420", "THST*3600", "DRMA*3610"));
	private static final ArrayList<String> approvedSetsCourses = new ArrayList<String>(Arrays.asList("ENGL*1080", "ENGL*2080", "ENGL*2120", "ENGL*2130", "ENGL*2200", "ENGL*2260", "ENGL*2740", "ENGL*2920", "ENGL*3090", "ENGL*3300", "ENGL*3340", "ENGL*3570", "ENGL*3870", "ENGL*3960", "ENGL*4240", "ENGL*4410", "ENGL*4720", "ENGL*6209", "ENGL*6811", "THST*1040", "THST*2050", "THST*4270", "ENGL*1200", "ENGL*2090", "ENGL*3070", "ENGL*3080", "ENGL*3880", "ENGL*3940", "ENGL*4250", "ENGL*4280", "ENGL*4310", "ENGL*4890", "ENGL*6451", "ENGL*6611", "ENGL*6691", "THST*1270", "THST*2270", "THST*3000", "THST*3500", "THST*4280", "THST*4290", "THST*4500", "THST*6210", "ENGL*2550", "ENGL*3280", "ENGL*3560", "ENGL*3750", "ENGL*4320", "ENGL*4500", "ENGL*6412", "THST*2500", "ENGL*1030", "ENGL*4810", "ENGL*4910", "ENGL*6801", "ENGL*6802", "ENGL*6803", "THST*3260", "THST*3340", "THST*3600", "THST*6280", "THST*6500", "THST*6801", "ENGL*6201", "ENGL*3060", "ENGL*3120", "ENGL*3320", "ENGL*3860", "ENGL*4270", "ENGL*4400", "THST*1200", "THST*2120", "THST*2190", "THST*3170", "THST*3190", "THST*4240", "THST*6150", "THST*6220", "THST*6230", "ENGL*2230", "ENGL*2940", "ENGL*3170", "ENGL*3550", "ENGL*4420", "THST*2240", "THST*3220", "THST*3650", "THST*3700", "ENGL*2640", "ENGL*3020", "ENGL*3040", "ENGL*3670", "THST*2010", "THST*2080", "THST*2230", "THST*3080", "THST*3110", "THST*3120", "THST*3230", "THST*3300", "THST*3530", "THST*3550", "THST*3630", "THST*3850"));
	private static final ArrayList<String> maxOnePerSemesterCourses = new ArrayList<String>(Arrays.asList("THST*2230", "THST*3110", "THST*3120", "THST*3220", "THST*3230", "THST*3410", "THST*3420", "THST*4090", "THST*4250", "THST*4280"));
	private static final ArrayList<String> minTwoCourses = new ArrayList<String>(Arrays.asList("THST*2080", "THST*2120", "THST*2240"));
	private static final ArrayList<String> minOneCourses = new ArrayList<String>(Arrays.asList("ENGL*3420", "THST*3650", "THST*3660", "THST*4320"));
	private static final double minCreditsRequired = 4.0;
	private static final double minCreditsMinOneCourses = 0.5;
	private static final double minCreditsMinTwoCourses = 1;
	private static final double minCreditsThstElectives = 2.5;
	private static final double maxCreditsDirectedReadingsOrSpecialStudies = 2.0;
	// private static final double maxCreditsPerSemesterDirectedReadingsOrSpecialStudies = 0.5;
	
	/**
     * Zero parameter constructor for objects of class THSTH.
     */
	public THSTH()
    {
		System.out.println("Unable to initialize degree due to no course catalog being provided");
	}

	public THSTH(CourseCatalog catalog)
	{
		super();
		setDegreeMajor("THSTH");
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
		double creditsTotal = 0, creditsRequired = 0, creditsMinOneCourses = 0, creditsMinTwoCourses = 0, creditsThstElectives = 0, creditsDirectedReadingsOrSpecialStudies = 0;
	
		// Count all other required credits as per degree requirements (course status is assumed to be Complete in order to allow ArrayList of Course rather than Attempt objects to be inputted)
		for (Course course: allTheCoursesPlannedAndTaken)
		{
			if (getRequiredCourses().contains(course))
			{
				creditsRequired += course.getCourseCredit();
			}

			if (course.getCourseCode().equals("THST*3600") || specialStudiesCourses.contains(course.getCourseCode()))
			{
				creditsDirectedReadingsOrSpecialStudies += course.getCourseCredit();
			}

			if (minTwoCourses.contains(course.getCourseCode()))
			{
				creditsMinTwoCourses += course.getCourseCredit();
			}

			if (minOneCourses.contains(course.getCourseCode()) || course.getCourseCode().equals("THST*4330"))
			{
				creditsMinOneCourses += course.getCourseCredit();
			}

			if (
				(
					course.getCourseCode().equals("THST") || approvedSetsCourses.contains(course.getCourseCode())
				) 
				&& !getRequiredCourses().contains(course)
			)
			{
				creditsThstElectives += course.getCourseCredit();
			}

			if (creditsDirectedReadingsOrSpecialStudies <= maxCreditsDirectedReadingsOrSpecialStudies)
			{
				creditsTotal += course.getCourseCredit();
			}
		}
		
		return (
			creditsTotal >= getMinCreditsTotal() && 
			creditsRequired >= minCreditsRequired &&
			creditsMinTwoCourses >= minCreditsMinTwoCourses &&
			creditsMinOneCourses >= minCreditsMinOneCourses &&
			creditsThstElectives >= minCreditsThstElectives
		);
	}

	/**
     * @return Whether this THSTH object is same as other THSTH object.
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
        THSTH toCompare = (THSTH) other;
		return (
			this == toCompare || 
			(getDegreeTitle().equals(toCompare.getDegreeTitle()) && getDegreeMajor().equals(toCompare.getDegreeMajor()) && getCatalog().equals(toCompare.getCatalog()) && getRequiredCourses().equals(toCompare.getRequiredCourses()))
		);
	}
}
