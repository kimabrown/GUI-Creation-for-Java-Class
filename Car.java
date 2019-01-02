package project1;

public class Car implements CarADT {

	private String buying;
	private String maint;
	private int doors;
	private int persons;
	private String lug_boot;
	private String safety;
	private String rating;

	//default constructor with no meaningful values
	public Car() {
		setBuying(null);
		setMaint(null);
		setDoors(0);
		setPersons(0);
		setTrunk(null);
		setSafety(null);
		setRating(null);
	}

	//constructor with all values
	public Car(String b, String m, int d, int p, String t, String s, String c) {
		setBuying(b);
		setMaint(m);
		setDoors(d);
		setPersons(p);
		setTrunk(t);
		setSafety(s);
		setRating(c);
	}

	//constructor for a Car without rating
	public Car(String b, String m, int d, int p, String t, String s) {
		setBuying(b);
		setMaint(m);
		setDoors(d);
		setPersons(p);
		setTrunk(t);
		setSafety(s);
		setRating(null);
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	public String getTrunk() {
		return lug_boot;
	}

	public void setTrunk(String lug_boot) {
		this.lug_boot = lug_boot;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public String getBuying() {
		return buying;
	}

	public void setBuying(String buying) {
		this.buying = buying;
	}

	//checks if this holds the same values as c except for rating
	public boolean equals(Car c) {
		if (!(getBuying().equals(c.getBuying())))
			return false;
		if (!(getMaint().equals(c.getMaint())))
			return false;
		if (!(getDoors() == c.getDoors()))
			return false;
		if (!(getPersons() == c.getPersons()))
			return false;
		if (!(getTrunk().equals(c.getTrunk())))
			return false;
		if (!(getSafety().equals(c.getSafety())))
			return false;

		return true;
	}

	//toString method that returns the private variables and some description for them
	public String toString() {
		return "price: " + getBuying() + ", maintance: " + getMaint()
		+ ", doors: " + getDoors() + ", people: " + getPersons()
		+ ", trunk size: " + getTrunk() + ", safety: " + getSafety() + ", rating: "
		+ getRating() + ".\n";
	}
}
