import java.time.LocalDateTime;

public class Contract {

	private String type;
	private LocalDateTime date;
	private String desc;
	private String team;
	private String playerName;

	public Contract(String newType, LocalDateTime newDate, String newDesc, String newTeam, String newName) {
		type = newType;
		date = newDate;
		desc = newDesc;
		team = newTeam.substring(0, 1).toUpperCase() + newTeam.substring(1).toLowerCase();
		String[] name = newName.split("-");
		if (name.length == 2) {
			playerName = name[0].substring(0,1).toUpperCase() + name[0].substring(1).toLowerCase() + " " + name[1].substring(0,1).toUpperCase() + name[1].substring(1).toLowerCase();
		} else {
			playerName = newName;
		}
	}

	@Override
	public String toString() {
		return "Type: " + type + "\nDate: " + date + "\nDescription: " + desc + "\nTeam: " + team + "\nPlayer: " + playerName + "\n";
	}
}