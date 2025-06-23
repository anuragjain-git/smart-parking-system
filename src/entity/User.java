package entity;

public class User {
    private int userId;
    private String username;
    private String email;
    private boolean isVip;

    public User(int userId, String username, String email, boolean isVip) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.isVip = isVip;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}
	
	@Override
	public String toString() {
	    return "User [id=" + userId + ", username=" + username + ", email=" + email + ", isVIP=" + isVip + "]";
	}

}
