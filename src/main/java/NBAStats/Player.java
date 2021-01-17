public class Player {
	private int playerId;
	private int jerseyNum;
	private String name;
	private int birthdate;

	public Player() {
		playerId = -1;
		jerseyNum = -1;
		name = "";
		birthdate = -1;
	}

	public Player(int newId, int newNum, String newName, int newBirth) {
		playerId = newId;
		jerseyNum = newNum;
		name = newName;
	}

	public int getId() {
		return playerId;
	}

	public int getJersey() {
		return jerseyNum;
	}

	public String getName() {
		return name;
	}

	public int getBirthdate() {
		return birthdate;
	}

	public void setId(int id) {
		playerId = id;
	}

	public void setJersey(int num) {
		jerseyNum = num;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setBirthdate(int birth) {
		birthdate = birth;
	}

	@Override
	public String toString() {
		return "ID: " + playerId + "\nJersey: " + jerseyNum + "\nName: " + name + "\nBirthdate: " + birthdate + "\n";
	}
}