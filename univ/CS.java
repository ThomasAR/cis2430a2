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
public class CS extends BCH implements Serializable
{
	// Declare necessary fields
	private static final ArrayList<String> requiredCourseCodes = new ArrayList<String>(Arrays.asList("CIS*1250", "MATH*1200", "CIS*1910", "CIS*2500", "CIS*2030", "CIS*2430", "CIS*2520", "CIS*2910", "CIS*2750", "CIS*3110", "CIS*3490", "CIS*3150", "CIS*3750", "CIS*3760", "CIS*4650"));
	private static final double minCreditsRequired = 9.75;
    private static final double minCreditsAreaOfApplicationOrElective = 8.25;
    private static final double minCreditsCis3000 = 1.5;
	private static final double minCreditsCis4000 = 1.5;
	
	/**
     * Zero parameter constructor for objects of class CS.
     */
	public CS()
    {
		System.out.println("Unable to initialize degree due to no course catalog being provided");
	}

	public CS(CourseCatalog catalog)
	{
		super();
		setDegreeMajor("CS");
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
		double creditsTotal = 0, creditsRequired = 0, creditsAreaOfApplicationOrElective = 0, creditsCis3000 = 0, creditsCis4000 = 0;
		
		// Count all other required credits as per degree requirements (course status is assumed to be Complete in order to allow ArrayList of Course rather than Attempt objects to be inputted)
		for (Course course: allTheCoursesPlannedAndTaken)
		{
			if (
				getRequiredCourses().contains(course) ||
				(
					course.getCourseCode().equals("CIS*2460") ||
					course.getCourseCode().equals("STAT*2040")				
				)
			)
			{
				creditsRequired += course.getCourseCredit();
			}

			if (
				!(
					course.getCoursePrefix().equals("CIS") && !course.getCourseCode().equals("CIS*2460") &&!getRequiredCourses().contains(course.getCourseCode())
				) && 
				(
					approvedScienceElectivePrefixes.contains(course.getCoursePrefix()) || approvedArtsOrSocialScienceElectivePrefixes.contains(course.getCoursePrefix())
				)
			)
			{
				creditsAreaOfApplicationOrElective += course.getCourseCredit();
			}

			if (course.getCoursePrefix().equals("CIS") && course.getCourseNumber() >= 3000 && course.getCourseNumber() < 4000) 
			{
				creditsCis3000 += course.getCourseCredit();
			}
			
			if (course.getCoursePrefix().equals("CIS") && course.getCourseNumber() >= 4000 && course.getCourseNumber() < 5000) 
			{
				creditsCis4000 += course.getCourseCredit();
			}
			
			creditsTotal += course.getCourseCredit();
		}
		
		return (
			creditsTotal >= getMinCreditsTotal() && 
			creditsRequired >= minCreditsRequired && 
			creditsAreaOfApplicationOrElective >= minCreditsAreaOfApplicationOrElective &&
			creditsCis3000 >= minCreditsCis3000 &&
			creditsCis4000 >= minCreditsCis4000
		);
	}

	/**
     * @return Whether this CS object is same as other CS object.
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
        CS toCompare = (CS) other;
		return (
			this == toCompare || 
			(getDegreeTitle().equals(toCompare.getDegreeTitle()) && getDegreeMajor().equals(toCompare.getDegreeMajor()) && getCatalog().equals(toCompare.getCatalog()) && getRequiredCourses().equals(toCompare.getRequiredCourses()))
		);
	}
}
