package project1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PredictorTester {
	public static void main(String args[]) {

		// build a predictor from Training Data
		Predictor pred = new Predictor();	
		System.out.println("Predictor information:\n"+pred+"\n");
		double accuracy = pred.getTrainingAccuracy();
		System.out.println("Training Accuracy: " + accuracy*100 + "%\n");
		
		// open the testing data and for each line create a Car 
		// object and compare its ranking with the predicted ranking
		String line = "";
		int totalTested = 0;
		int testedCorrect = 0;
		BufferedReader myInfile = null;
		try
		{
			FileReader fr = new FileReader("./carTest.DATA");
			myInfile = new BufferedReader(fr);
			while ((line = myInfile.readLine()) != null){
				String[] tokens = line.split(",");
				int doors = 0;
				if (tokens[2].equals("5more"))
					doors = 5;
				else
					doors = Integer.parseInt(tokens[2]);

				int peoplect = 0;
				if (tokens[3].equals("more"))
					peoplect = 5;
				else
					peoplect = Integer.parseInt(tokens[3]);
				String buying = tokens[0];
				String maint = tokens[1];
				String lugboot = tokens[4];
				String safety = tokens[5];
				String accept = tokens[6];

				Car testCar = new Car(buying, maint, doors, peoplect, lugboot, safety, "unknown");
				
				String predRating = pred.getPrediction(testCar);			
				totalTested++;
				if (accept.equals(predRating)) {
					testedCorrect++;
				}
			}
		}
		catch (Exception e) {
			System.err.println("Didn't open file" );
		}
		finally {
			if (myInfile!=null)
				try {
					myInfile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		System.out.println("Testing Accuracy = "+100.0*testedCorrect / totalTested+ "%\n");
		
		
		Car testCar = new Car("low", "high", 2, 2, "small", "low", "unknown");
		System.out.println("closest to "+testCar);		
		System.out.println(pred.getClosest(testCar));
		
		

	}
}
