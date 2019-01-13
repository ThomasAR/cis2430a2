// Import necessary libaries
import database.*;
import univ.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Administrator
{
    // Declare necessary fields
    MyConnection db;
    CourseCatalog catalog;

    public Administrator()
    {
        db = new MyConnection("rparnian", "0987909");
        catalog = new CourseCatalog();
        catalog.initializeCatalog(db.getAllCourses());
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
        this.catalog = catalog;
    }

    /**
     * @return the db
     */
    public MyConnection getDb() 
    {
        return db;
    }
    
    /**
     * @param db the db to set
     */
    protected void setDb(MyConnection db) 
    {
        if (db != null)
        {
            this.db = db;
        }
    }

    // public void addCourse(String code, String credit, String title, String semesterOffered, String prerequisites)
    // {

    // }

    public void freshStart(String filename)
    {
        // Empty database
        getDb().deleteAllCourses();

        // Populate course catalog from file
        getCatalog().initializeCatalogFromFile(filename);
        // System.out.println(getCatalog().getAvailableCourses());

        // Loop through catalog, adding each course to database
        for (Map.Entry<String,Course> keyValue: getCatalog().getAvailableCourses().entrySet())
		{
			// Store current course to temporary variable
            Course course = keyValue.getValue();
            getDb().addCourse(course.getCourseCode(), Double.toString(course.getCourseCredit()), course.getCourseTitle(), course.getSemesterOffered(), course.getPrerequisitesAsString());
        }
    }

    public static void main(String[] args) 
    {
        // Initialize new Administrator object
        Administrator admin = new Administrator();        
        // admin.freshStart("courseListA2.csv");
        // System.out.println(admin.getCatalog());
        Degree cs = new CS(admin.getCatalog());
        System.out.println(cs.getDegreeMajor());
    }
}
