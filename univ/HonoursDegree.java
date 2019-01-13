// Package class
package univ;

// Import necessary libraries
import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class HonoursDegree extends Degree implements Serializable
{
    public HonoursDegree()
    {
        super();
        setMinCreditsTotal(20);
    }
}
