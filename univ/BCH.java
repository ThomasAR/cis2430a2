// Package class
package univ;

// Import necessary libraries
import java.util.Arrays;
import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BCH extends HonoursDegree implements Serializable
{
    // Declare necessary fields
    private String major;
    private static final ArrayList<String> availableMajors = new ArrayList<String>(Arrays.asList("CS", "SENG"));

    public BCH()
    {
        super();
        setDegreeTitle("BCH");
        major = "";
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
}
