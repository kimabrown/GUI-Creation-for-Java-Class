package pages;

import java.util.ArrayList;

import project1.Car;
import project1.Predictor;

public class PredictionAPI {

	private Predictor predictor;
	
	public PredictionAPI(String filename) {
		predictor = new Predictor();
		predictor.addFromFile(filename);
	}
	
	public double getTrainingAcc() {
		
		return predictor.getTrainingAccuracy();
	}

	public static ArrayList<Car> getCloset(Car car0) {
		// TODO Auto-generated method stub
		return null;
	}	
}