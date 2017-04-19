package domain;

public enum Role {
	ADMIN("admin"), MEMBER("member");

	private String description;

	Role(String description) {
		this.description = description;
	}
	
	Role() {
		
	}

	public String getDescription() {
		return description;
	}
}
