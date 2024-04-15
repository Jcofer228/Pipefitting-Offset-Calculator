/*  Jeffrey Cofer 
    04/07/2024
	Program to calculate travel dimension for 
    simple piping offsets.    
*/
import javax.swing.JOptionPane;

public class CalculateTravel 
{	// Instantiates variables
    private double offset;
    private double run;
    private double travel;
    private double angleInDegrees;


    public static void main(String[] args) // Main class
	{
        String offsetStr = JOptionPane.showInputDialog("Enter offset dimension:");
        if (offsetStr == null || offsetStr.trim().isEmpty()) 
		{
            JOptionPane.showMessageDialog(null, "Invalid offset dimension!");
            return;
        }
        offsetStr = offsetStr.replace("\"", "");
        double offset = Double.parseDouble(offsetStr);

        String runStr = JOptionPane.showInputDialog("Enter run dimension:");
        if (runStr == null || runStr.trim().isEmpty()) 
		{
            JOptionPane.showMessageDialog(null, "Invalid run dimension!");
            return;
        }
        runStr = runStr.replace("\"", "");
        double run = Double.parseDouble(runStr);
		
		String angleInDegreesStr = JOptionPane.showInputDialog("Enter angle:");
		if (angleInDegreesStr == null || angleInDegreesStr.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Invalid angle!");
			return;
		}
		double angleInDegrees = Double.parseDouble(angleInDegreesStr);

        CalculateTravel newTravel = new CalculateTravel(offset, run, angleInDegrees);
        newTravel.display();
    }

    public CalculateTravel(double offset, double run, double angleInDegrees) // Constructor Class 
	{
		this.offset = offset;
		this.run = run;
		this.angleInDegrees = angleInDegrees;

		if (offset == 0 && run == 0 && angleInDegrees == 0)
        {
            JOptionPane.showMessageDialog(null, "Invalid inputs, please input values for two fields");
        }
		if (offset == 0) 
		{
            this.offset = calculateOffset(run, angleInDegrees);
            this.travel = calculateTravel(this.offset, run);
        }

        if (run == 0) 
		{
            this.run = calculateRun(offset, angleInDegrees);
            this.travel = calculateTravel(offset, this.run);
        }

        if (offset != 0 && run != 0) 
		{
            this.travel = calculateTravel(this.offset, this.run);
            this.angleInDegrees = calculateAngle(offset, run);
        }
	}
	
    private double calculateOffset(double run, double angleInDegrees) // Calculates Offset value based on Run and Angle values
	{
        double degreesInRadians = Math.toRadians(angleInDegrees);
        return run * Math.tan(degreesInRadians);
    }

    private double calculateRun(double offset, double angleInDegrees) // Calculates Run value based on Offset and Angle values 
	{
        double degreesInRadians = Math.toRadians(angleInDegrees);
        return offset / Math.tan(degreesInRadians);
    }

    private double calculateTravel(double offset, double run) // Calculates Travel value based on Offset and Run values 
	{
        double offsetSquared = offset * offset;
        double runSquared = run * run;
        return Math.sqrt(offsetSquared + runSquared);
    }

    public double calculateAngle(double offset, double run) // Calculates Angle value based on Offset and Run values 
	{
        double angleInRadians = Math.atan(offset / run);
        return Math.toDegrees(angleInRadians);
    }

    public void display() 
	{
        JOptionPane.showMessageDialog(null,
                "The offset dimension is " + String.format("%.2f", offset) + ".\n" +
                        "The run dimension is " + String.format("%.2f", run) + ".\n" +
                        "The offset angle is " + angleInDegrees + ".\n" +
                        "The travel dimension is " + String.format("%.2f", travel));
    }
}