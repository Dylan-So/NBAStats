public class Team {

	private int teamId;
	private String city;
	private String name;
	private String label;

	public Team() {
		teamId = -1;
		city = "";
		name = "";
		label = "";
	}

	public Team(int id, String newCity, String newName, String newLabel) {
		teamId = id;
		city = newCity;
		name = newName;
		label = newLabel;
	}

	public int getId() {
		return teamId;
	}

	public String getCity() {
		return city;
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public void setId(int id) {
		teamId = id;
	}

	public void setCity(String newCity) {
		city = newCity;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setLabel(String newLabel) {
		label = newLabel;
	}

	@Override
	public String toString() {
		return "ID: " + teamId + "\nCity: " + city + "\nName: " + name + "\nAbbreviation: " + label + "\n";
	}
}