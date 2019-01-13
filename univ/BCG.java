// Package class
package univ;

// Import necessary variables
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Comparator;
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
public class BCG extends GeneralDegree implements Serializable
{
	// Declare necessary fields
	private String major;
	private static final ArrayList<String> availableMajors = new ArrayList<String>(Arrays.asList("BCG"));
	private static final ArrayList<String> requiredCourseCodes = new ArrayList<String>(Arrays.asList("CIS*1500", "CIS*1910", "CIS*2430", "CIS*2500", "CIS*2520", "CIS*2750", "CIS*2910", "CIS*3530"));
	private static final double minCreditsRequired = 9.25;
    private static final double minCredits3000OrAbove = 4;
    private static final double minCreditsCisOrStat2000OrAbove = 0.5;
    private static final double minCreditsScience = 2;
	private static final double minCreditsArtsOrSocialScience = 2;
	private static final double maxCredits1000 = 6;
    private static final double maxCreditsSameCoursePrefix = 11;
	
	/**
     * Zero parameter constructor for objects of class BCG.
     */
	public BCG()
    {
		System.out.println("Unable to initialize degree due to no course catalog being provided");
	}

	public BCG(CourseCatalog catalog)
	{
		super();
		setDegreeTitle("BCG");
		setDegreeMajor("BCG");
		setCatalog(catalog);
		setRequiredCourseCodes(requiredCourseCodes);
	}

	public String getDegreeMajor()
    {
        return major;
    }

    protected void setDegreeMajor(String major)
    {
        if (major != null && !major.isEmpty() && availableMajors.contains(major))
        {
            this.major = major;
        }
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
		double creditsTotal = 0, creditsRequired = 0, credits3000OrAbove = 0, creditsCisOrStat2000OrAbove = 0, creditsScience = 0, creditsArtsOrSocialScience = 0, credits1000 = 0, creditsSameCoursePrefix = 0;

		// Find most occurring course prefix and store it to temporary variable
		ArrayList<String> coursePrefixes = allTheCoursesPlannedAndTaken.stream().map(c -> c.getCoursePrefix()).collect(Collectors.toCollection(ArrayList::new));
		String mostOccurringCoursePrefix = coursePrefixes.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting())).entrySet().stream().max(Comparator.comparing(e -> e.getValue())).get().getKey();

		// Count all other required credits as per degree requirements (course status is assumed to be Complete in order to allow ArrayList of Course rather than Attempt objects to be inputted)
		for (Course course: allTheCoursesPlannedAndTaken)
		{
			if (getRequiredCourses().contains(course))
			{
				creditsRequired += course.getCourseCredit();
			}

            if (course.getCourseNumber() >= 3000) 
			{
				credits3000OrAbove += course.getCourseCredit();
			}
			
			if (course.getCourseNumber() >= 1000 && course.getCourseNumber() < 2000) 
			{
				credits1000 += course.getCourseCredit();
			}
			
			if (course.getCoursePrefix().equals(mostOccurringCoursePrefix))
			{
				creditsSameCoursePrefix += course.getCourseCredit();
			}

			if ((course.getCoursePrefix().equals("CIS") || course.getCoursePrefix().equals("STAT")) && course.getCourseNumber() >= 2000) 
			{
				creditsCisOrStat2000OrAbove += course.getCourseCredit();
			}

			if (approvedScienceElectivePrefixes.contains(course.getCoursePrefix()))
			{
				creditsScience += course.getCourseCredit();
			}

			if (approvedArtsOrSocialScienceElectivePrefixes.contains(course.getCoursePrefix()))
			{
				creditsArtsOrSocialScience += course.getCourseCredit();
			}
			
			if (credits1000 <= maxCredits1000 && creditsSameCoursePrefix <= maxCreditsSameCoursePrefix) 
			{
				creditsTotal += course.getCourseCredit();
			}
		}
		
		return (
			creditsTotal >= getMinCreditsTotal() && 
			creditsRequired >= minCreditsRequired &&
			credits3000OrAbove >= minCredits3000OrAbove &&creditsCisOrStat2000OrAbove >= minCreditsCisOrStat2000OrAbove &&
			creditsScience >= minCreditsScience && 
			creditsArtsOrSocialScience >= minCreditsArtsOrSocialScience
		);
	}

	/**
     * @return Whether this BCG object is same as other BCG object.
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
        BCG toCompare = (BCG) other;
		return (
			this == toCompare || 
			(getDegreeTitle().equals(toCompare.getDegreeTitle()) && getDegreeMajor().equals(toCompare.getDegreeMajor()) && getCatalog().equals(toCompare.getCatalog()) && getRequiredCourses().equals(toCompare.getRequiredCourses()))
		);
	}
}
