// Package class
package univ;

// Import necessary libraries
import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class GeneralDegree extends Degree implements Serializable
{
    public GeneralDegree()
    {
        super();
        setMinCreditsTotal(15);
	}
}
