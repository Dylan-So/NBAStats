import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import okhttp3.*;
import org.json.*;

public class NbaParser {

	private String apiKey;
	private ArrayList<Team> teams = new ArrayList<>();
	private ArrayList<Contract> contracts = new ArrayList<>();

	public NbaParser(String key) {
		apiKey = key;
	}

	public String postJSON(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = new FormBody.Builder()
					.add("api_key", apiKey)
					.build();

		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected Code " + response);
			} else {
				String data = response.body().string();
				response.body().close();
				return data;
			}
		}
	}

	public String postJSON(String url, String firstName, String lastName) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = new FormBody.Builder()
					.add("api_key", apiKey)
					.add("first_name", firstName)
					.add("last_name", lastName)
					.build();

		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected Code " + response);
			} else {
				String data = response.body().string();
				response.body().close();
				return data;
			}
		}
	}

	public String getJSON(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(url)
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected Code " + response);
			} else {
				String data = response.body().string();
				response.body().close();
				return data;
			}
		}
	}

	public void parseTeams(String data) {
		if (teams.size() == 0) {
			JSONArray teamList = new JSONArray(data);
			for (Object team : teamList) {
				JSONObject tempTeam = (JSONObject) team;
				int id = tempTeam.getInt("team_id");
				String city = tempTeam.getString("city");
				String name = tempTeam.getString("team_name");
				String label = tempTeam.getString("abbreviation");
				Team newTeam = new Team(id, city, name, label);
				teams.add(newTeam);
			}
		}
	}

	public void displayTeams() {
		for (Team team : teams) {
			System.out.println(team);
		}
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void parseContracts(String data, String startDate, String endDate) {
		JSONObject list = new JSONObject(data);
		JSONObject listRow = (JSONObject) list.get("NBA_Player_Movement");
		JSONArray contractList = (JSONArray) listRow.get("rows");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		DateTimeFormatter user = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime start =  LocalDate.parse(startDate, user).atStartOfDay();
		LocalDateTime end = LocalDate.parse(endDate, user).atStartOfDay();
		for (Object tempCon : contractList) {
			JSONObject contract = (JSONObject) tempCon;
			LocalDateTime date = LocalDateTime.parse(contract.getString("TRANSACTION_DATE"), formatter);
			if ((end.isAfter(date) && start.isBefore(date) || end.isEqual(date) || start.isEqual(date))) {
				String type = contract.getString("Transaction_Type");
				String desc = contract.getString("TRANSACTION_DESCRIPTION");
				String team = contract.getString("TEAM_SLUG");
				String name = contract.getString("PLAYER_SLUG");
				Contract newContract = new Contract(type, date, desc, team, name);
				contracts.add(newContract);
			} else if (date.isBefore(start)) {
				break;
			}
		}
		System.out.println(contracts.size());
	}

	public void displayContracts() {
		for (Contract contract : contracts) {
			System.out.println(contract);
		}
	}

	public ArrayList<Contract> getContracts() {
		return contracts;
	}
}