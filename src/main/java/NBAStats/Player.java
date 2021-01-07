public class Player {
	private int playerId;
	private int jerseyNum;
	private String name;
	private String birthdate;

	public Player() {
		playerId = -1;
		jerseyNum = -1;
		name = "";
	}

	public Player(int newId, int newNum, String newName, String newBirth) {
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

	public String getBirthdate() {
		return birthdate;
	}
}