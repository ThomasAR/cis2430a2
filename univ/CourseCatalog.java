// Package class
package univ;

// Import necessary libraries
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("serial")
public class CourseCatalog implements Serializable
{
    // Declare necessary fields
    private HashMap<String,Course> availableCourses;

    public CourseCatalog()
    {
        availableCourses = new HashMap<String,Course>();
    }

    public HashMap<String,Course> getAvailableCourses()
    {
        return availableCourses;
    }

    protected void setAvailableCourses(HashMap<String,Course> availableCourses)
    {
        if (availableCourses != null)
        {
            this.availableCourses = availableCourses;
        }
    }
    
    public Course findCourse(String courseCode)
	{	
        return availableCourses.get(courseCode);
    }
    
    public Course deepFindCourse(String courseCode)
	{	
        // If course is in availableCourses, return deep copy of Course object
        if (availableCourses.containsKey(courseCode))
        {
            return new Course(availableCourses.get(courseCode));
        }
        // Otherwise, return null
        else
        {
            return null;
        }
    }

    public void loadNewThingsFromFile(String filename)
	{
        // Clear HashMap availableCourses
        availableCourses.clear();

        // Use initializeCatalog to load new data into availableCourses
        initializeCatalogFromFile(filename);	
    }

    public void loadNewThings(ArrayList<String> commaSeparatedCourseStrings)
	{
        // Clear HashMap availableCourses
        availableCourses.clear();

        // Use initializeCatalog to load new data into availableCourses
        initializeCatalog(commaSeparatedCourseStrings);	
    }

    public void initializeCatalogFromFile(String filename)
    {
        try {
            // Open file
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            // Create ArrayList to store available course codes
            ArrayList<String> commaSeparatedCourseStrings = new ArrayList<String>();
            
            // Read first line of file
            String currentLine = reader.readLine();
            do
            {
                // Add current line string to ArrayList
                commaSeparatedCourseStrings.add(currentLine);

                // Read next line of file
                currentLine = reader.readLine();
            } while (currentLine != null);
            
            // Close file
            reader.close();

            // Initialize catalog by parsing ArrayList of comma-separated course strings
            initializeCatalog(commaSeparatedCourseStrings);
        } 
        catch(IOException e) 
        {
            System.out.println(String.format("Unable to read catalog of available courses from file %s", filename));
        }
    }

    public void initializeCatalog(ArrayList<String> commaSeparatedCourseStrings)
    {
        // Create ArrayList to store available courses
        ArrayList<String[]> courses = new ArrayList<String[]>();

        for (int i = 0; i < commaSeparatedCourseStrings.size(); i++)
        {
            // If current string is empty, move to next element
            if (commaSeparatedCourseStrings.get(i).isEmpty())
            {
                continue;
            }

            // Otherwise, create string array from current string's data, maintaining comma-separated entries with empty strings (hence the -1 argument); also, split() method never returns null elements in its array, which means checking for null values is unnecessary
            String[] courseSplit = commaSeparatedCourseStrings.get(i).split(",", -1);
            // System.out.println(Arrays.asList(courseSplit));

            // Format course string
            for (int j = 0; j < courseSplit.length; j++)
            {
                // Remove trailing and leading spaces
                courseSplit[j] = courseSplit[j].trim();

                // Format course code, semster offered, and prerequisites
                if (j == 0 || j == 3 || j == 4)
                {
                    // Capitalize course code
                    courseSplit[j] = courseSplit[j].toUpperCase();
                }

                // Format course title
                else if (j == 2 && !courseSplit[j].isEmpty())
                {
                    // Capitalize first letter of every word in course title
                    String[] titleSplit = courseSplit[j].split(" ");
                    String newTitle = "";
                    for (int k = 0; k < titleSplit.length; k++)
                    {
                        // Capitalize first letter of current word
                        newTitle += titleSplit[k].substring(0, 1).toUpperCase() + titleSplit[k].substring(1);

                        // Add space after current word if not already at end of course title
                        if (k != titleSplit.length - 1)
                        {
                            newTitle += " ";
                        }
                    }
                    courseSplit[j] = newTitle;
                }
            }

            // Add string array representing current course to ArrayList courses
            courses.add(courseSplit);
        }

        // String toPrint = "{";
        // for (String[] strArr: courses)
        // {
        //     toPrint += Arrays.toString(strArr);
        //     if (courses.indexOf(strArr) != courses.size() - 1)
        //     {
        //         toPrint += ", ";
        //     }
        // }
        // toPrint += "}";
        // System.out.println(toPrint);

        // Loop through Arraylist courses and create course objects to add to availableCourses
        for (String[] course: courses)
        {
            // If course code is valid, proceed with adding current course to availableCourses
            if (!course[0].isEmpty() && course[0].indexOf("*") != -1)
            {
                // If course is not already in availableCourses, create and add new Course object
                if (!availableCourses.containsKey(course[0])) {

                    // Create new Course object
                    Course toAdd = new Course();
                    // System.out.println(toAdd);

                    // Set course code
                    toAdd.setCourseCode(course[0]);
                    // System.out.println(toAdd);

                    // Set all other fields
                    setMissingFields(toAdd, course);
                    
                    // Add course to availableCourses after all possible data has been set
                    addCourse(toAdd);
                }

                // If course is already in availableCourses, set all fields other than course code
                else
                {
                    setMissingFields(availableCourses.get(course[0]), course);
                }
            }

            // Otherwise, print error message and skip to next course
            else
            {
                System.out.println(String.format("Line %d in CSV file skipped due to invalid or missing course code", courses.indexOf(course) + 1));
            }
        }           

    }

    private void setMissingFields(Course existingCourse, String[] course)
    {
        // System.out.println(Arrays.asList(course));
        if (existingCourse != null && course != null)
        {
            // Set course credit
            if (!course[1].isEmpty())
            {
                // Verify that string is a number
                if (course[1].matches("[0-9]{1}\\.?[0-9]{1,2}"))
                {
                    existingCourse.setCourseCredit(Double.parseDouble(course[1]));
                }
                else 
                {
                    System.out.println(String.format("%s course credit not set due to invalid value", course[0]));
                }
            }
            else
            {
                System.out.println(String.format("%s course credit not set due to invalid value", course[0]));
            }

            // Set course title
            if (!course[2].isEmpty())
            {
                existingCourse.setCourseTitle(course[2]);
            }
            else
            {
                System.out.println(String.format("%s course title not set due to invalid value", course[0]));
            }

            // Set course semester offered
            if (!course[3].isEmpty())
            {
                if (course[3].matches("[FWBSU]{1}"))
                {
                    existingCourse.setSemesterOffered(course[3]);
                }
                else
                {
                    System.out.println(String.format("%s semester taken not set due to invalid value", course[0]));
                }
            }

            // Set course prerequisites
            if (!course[4].isEmpty())
            {
                // Create new ArrayList to store Course objects for all prerequisites
                ArrayList<Course> prereqs = new ArrayList<Course>();

                // Split prerequisites into string array
                String[] prerequisitesSplit = course[4].split(":");

                // Loop through string array of prerequisites
                for (String courseCode: prerequisitesSplit)
                {
                    // If course is in availableCourses, add its pointer to prerequisites
                    if (availableCourses.containsKey(courseCode))
                    {
                        prereqs.add(findCourse(courseCode));
                    }
                    
                    // Otherwise, create new Course object, add it to availableCourses, and add its pointer to prerequisites
                    else
                    {   
                        // System.out.println(courseCode);
                        Course notFound = new Course();
                        notFound.setCourseCode(courseCode);
                        // System.out.println(notFound);
                        addCourse(notFound);
                        prereqs.add(availableCourses.get(courseCode));
                    }
                }

                // Set course prerequisites
                existingCourse.setPrerequisites(prereqs);
            }
            // else
            // {
            //     System.out.println(String.format("%s course prerequisites not set due to invalid value", course[0]));
            // }
        }
    }

    public void addCourse(Course toAdd)
    {
        if (toAdd != null)
        {
            availableCourses.put(toAdd.getCourseCode(), toAdd);
        }
    }

    public void removeCourse(Course toRemove)
    {
        if (toRemove != null)
        {
            availableCourses.remove(toRemove.getCourseCode(), toRemove);
        }
    }

    public void importData(String filename)
    {
        try
		{
            // Open file
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filename));
                
            // Cast imported data from serialized file into CourseCatalog object
            CourseCatalog importedCourseCatalog = (CourseCatalog) reader.readObject();

            // Set current CourseCatalog object's values to those of imported data
            setAvailableCourses(importedCourseCatalog.getAvailableCourses());

            // Close file
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println(String.format("Unable to read course catalog data from file %s", filename));
        }
    }

    public void saveCatalog()
    {
        try
		{
            // Open file
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("CourseCatalog.sav"));

            // Write to file
            writer.writeObject(this);
            
            // Close file
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Unable to write course catalog data to file CourseCatalog.sav");
		}
    }

    @Override
    public String toString()
    {
        return String.format("%s", availableCourses.toString());
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
        CourseCatalog toCompare = (CourseCatalog) other;
        return this == toCompare || availableCourses.equals(toCompare.availableCourses);
    }
}
