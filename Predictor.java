package project1;

import java.io.*;
import java.util.*;

public class Predictor implements PredictorADT {

	private ArrayList<Car> cars = new ArrayList<Car>();
	private String myFileName = "/project1/carTrain.DATA";
	private BufferedReader lineReader; 

	//default constructor
	public Predictor() {
		lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(myFileName)));
		initialize(lineReader);
	}

	//if you want to contruct with a different filename from default
	public Predictor(String filename) {
		myFileName = filename;
		lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(myFileName)));
		initialize(lineReader);
	}
	public Predictor(BufferedReader lr) {
		lineReader = lr;
		initialize(lineReader);
	}
	//if you wanted to check from another file after construction
	public void addFromFile(String filename) {
		myFileName = filename;
		lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(myFileName)));
		initialize(lineReader);
	}

	//reads from filename and tokenizes into Car objects that are saved into cars
	private void initialize(BufferedReader lineReader) {
		// adapted from from https://www.codejava.net/java-se/file-io/how-to-read-text-file-line-by-line-in-java
		try {
//			BufferedReader lineReader = new BufferedReader(new FileReader(myFileName));
			String lineText = null;

			while ((lineText = lineReader.readLine()) != null) {
				String[] tokens = lineText.split(",");
				String buying = tokens[0];
				String maint = tokens[1];
				// parseInt taught in class, try catch elaborated in class and used above
				int doors;
				int persons;
				try {
					doors = Integer.parseInt(tokens[2]);
				} catch (Exception e) {
					doors = 5;
				}
				try {
					persons = Integer.parseInt(tokens[3]);
				} catch (Exception e) {
					persons = 5;
				}
				String lug_boot = tokens[4];
				String safety = tokens[5];
				String rating = tokens[6];

				cars.add(new Car(buying, maint, doors, persons, lug_boot, safety, rating));

			}

			lineReader.close();

		} catch (IOException e) {
			System.err.println("Didn't open " + myFileName);
		}
	}

	//prints to console all the Car objects in cars
	public void print() {
		System.out.println("These are the contained Car objects.");
		for (Car c : cars) {
			System.out.println(c);
		}		
	}

	public ArrayList<Car> getClosest (CarADT car2) {
		ArrayList<Car> toReturn = new ArrayList<Car>();
		Car car = (Car) car2;
		toReturn.add(cars.get(0));
		toReturn.add(cars.get(1));
		toReturn.add(cars.get(2));
		int max = 0;
		for (Car c : cars) {
			int thisScore = 0;
			if (car.getDoors() == c.getDoors())
				thisScore++;
			if (car.getPersons() == c.getPersons())
				thisScore++;			
			if (car.getMaint().equals(c.getMaint()))
				thisScore++;	
			if (car.getBuying().equals(c.getBuying()))
				thisScore++;	
			if (car.getTrunk().equals(c.getTrunk()))
				thisScore++;	
			if (car.getSafety().equals(c.getSafety()))
				thisScore++;	
			if (thisScore == 6) {
				// found an exact match
				toReturn.clear();
				toReturn.add(c);
				return toReturn;
			}
			if (thisScore >= max) {
				max = thisScore;
				toReturn.remove(2);
				toReturn.add(0, c);
			}		
		} // end of processing all cars
		return toReturn;
	}
	//a toString method that returns a string of the values inside
	public String toString() {
		String returnString;
		double unacc = 0;
		double acc = 0;
		double good = 0;
		double vgood = 0;
		double errNum = 0;

		for (Car c : cars) {
			switch (c.getRating()) {
			case "unacc":
				unacc++;
				break;
			case "acc":
				acc++;
				break;
			case "good":
				good++;
				break;
			case "vgood":
				vgood++;
				break;
			default:
				errNum = 0;
			}
		}

		returnString = "The Car Objects contained are of these ratings.\n";
		returnString += "rating\tentities\tpercent\n";
		returnString += "unacc\t" + (int) unacc + "\t\t" + (unacc * 100 / cars.size()) + "%\n";
		returnString += "acc\t" + (int) acc + "\t\t" + (acc * 100 / cars.size()) + "%\n";
		returnString += "good\t" + (int) good + "\t\t" + (good * 100 / cars.size()) + "%\n";
		returnString += "vgood\t" + (int) vgood + "\t\t" + (vgood * 100 / cars.size()) + "%\n";
		returnString += "Errors\t" + (int) errNum + "\t\t" + (errNum * 100 / cars.size()) + "%";

		return returnString;
	}

	//runs through the cars arraylist, counts each accurate prediction, and divides the count by cars.size
	public double getTrainingAccuracy() {
		double correct = 0;

		for (Car c : cars) {
			if (c.getRating().equals(getPrediction(c))) {
				correct++;
			}
		}

		return (correct / cars.size());
	}

	//a fair decision tree based off of weka's j48 tree (minor edits and interpretations)
	//joke reference -> https://www.xkcd.com/221/
	public String getPrediction(CarADT instance) {
		//checks if instance is an instance of Car
		if (!(instance instanceof Car)) {
			System.out.println("Cannot predict. Not an instance of Car.");
			return null;
		}

		//so its not type casting instance throughout the tree when its called
		Car c = (Car) instance;

		//runs c through the decision tree
		if (c.getSafety().equals( "low")) {
			return "unacc";
		}

		else if (c.getSafety().equals( "med")) {
			if (c.getPersons()== 2)
				return "unacc";
			if (c.getPersons()== 4) {
				if (c.getTrunk().equals( "small")) {
					return "unacc";
				}
				if (c.getTrunk().equals( "med")) {
					if (c.getMaint().equals( "vhigh")) {
						return "unacc";
					}
					if (c.getMaint().equals( "med")) {
						if (c.getBuying().equals( "low")) {
							return "unacc";
						}
						else {
							return "acc";
						}
					}
					else {
						return "acc";
					}
				}
				if (c.getTrunk().equals("big")) {
					if (c.getMaint().equals( "vhigh")) {
						if (c.getBuying().equals( "low")) {
							return "acc";
						}
						if (c.getBuying().equals( "med")) {
							return "acc";
						}
						else {
							return "unacc";
						}
					}
					if (c.getMaint().equals( "med")) {
						if (c.getBuying().equals( "low")) {
							return "good";
						}
						else {
							return "acc";
						}
					}
					else {
						return "acc";
					}
				}
			}
			if (c.getPersons()== 5) {
				if (c.getBuying().equals( "vhigh")) {
					if (c.getTrunk().equals( "big")) {
						if (c.getMaint().equals( "low")) {
							return "acc";
						}
						if (c.getMaint().equals( "med")) {
							return "acc";
						}
						else {
							return "unacc";
						}
					}
					else {
						return "unacc";
					}
				}
				if (c.getBuying().equals( "high")) {
					if (c.getTrunk().equals( "big")) {
						if (c.getMaint().equals( "vhigh")) {
							return "unacc";
						}
						else {
							return "acc";
						}
					}
					else {
						return "unacc";
					}
				}
				if (c.getBuying().equals( "med")) {
					if (c.getTrunk().equals( "big")) {
						if (c.getMaint().equals( "low")) {
							return "good";
						}
						else {
							return "acc";
						}
					}
					if (c.getTrunk().equals( "med")) {
						return "acc";
					}
					else {
						return "unacc";
					}
				}
				if (c.getBuying().equals( "low")) {
					if (c.getTrunk().equals( "big")) {
						if (c.getMaint().equals( "low")) {
							return "good";
						}
						if (c.getMaint().equals( "med")) {
							return "good";
						}
						else {
							return "acc";
						}
					}
					if (c.getTrunk().equals( "med")) {
						if (c.getMaint().equals( "low")) {
							return "good";
						}
						else {
							return "acc";
						}
					}
					else {
						if (c.getMaint().equals( "vhigh")) {
							return "unacc";
						}
						else {
							return "acc";
						}
					}
				}
			}
		}


		else {
			if (c.getPersons()== 2)
				return "unacc";
			if (c.getPersons()== 4) {
				if (c.getMaint().equals("vhigh")) {
					if (c.getBuying().equals("vhigh")) {
						return "unacc";
					}
					if (c.getBuying().equals("high")) {
						return "unacc";
					}
					else {
						return "acc";
					}
				}
				if (c.getMaint().equals("high")) {
					if (c.getBuying().equals("vhigh")) {
						return "unacc";
					}else {
						return "acc";
					}
				}
				if (c.getMaint().equals("med")) {
					if (c.getBuying().equals("low")) {
						return "good";
					}
					else {
						return "acc";
					}
				}
				if (c.getMaint().equals("low")) {
					if (c.getBuying().equals("vhigh")) {
						return "acc";
					}
					if (c.getBuying().equals("high")) {
						return "acc";
					}
					else {
						return "good";
					}
				}
			}
			else {
				if (c.getBuying().equals("vhigh")) {
					if (c.getMaint().equals("vhigh")) {
						return "unacc";
					}
					if (c.getMaint().equals("high")) {
						return "unacc";
					}
					else {
						return "acc";
					}
				}
				if (c.getBuying().equals("high")) {
					if (c.getMaint().equals("vhigh")) {
						return "unacc";
					}
					else {
						return "acc";
					}
				}
				if (c.getBuying().equals("med")) {
					if (c.getMaint().equals("vhigh")) {
						return "acc";
					}
					if (c.getMaint().equals("high")) {
						return "acc";
					}
					if (c.getMaint().equals("med")) {
						if (c.getTrunk().equals("small")) {
							return "unacc";
						}
						else {
							return "vgood";
						}
					}
					else {
						return "good";
					}
				}
				else {
					if (c.getDoors() ==2) {
						return "good";
					}
					if (c.getDoors() ==2) {
						return "vgood";
					}
					else {
						return "acc";
					}
				}
			}
		}

		// if somehow get here, just return unacc
		return "unacc";
	}
}
