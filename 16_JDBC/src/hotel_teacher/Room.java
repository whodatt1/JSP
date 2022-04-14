package hotel_teacher;

public class Room {
	private String roomNum;
	private User2 user;
	private boolean empty;
	private String checkInTime;
	private String checkOutTime;
	
	public Room() {}

	public Room(String roomNum, User2 user, boolean empty) {
		this.roomNum = roomNum;
		this.user = user;
		this.empty = empty;
	}
	

	public Room(String roomNum, User2 user, boolean empty, String checkInTime, String checkOutTime) {
		this(roomNum, user, empty);
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public User2 getUser() {
		return user;
	}

	public void setUser(User2 user) {
		this.user = user;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", user=" + user + ", empty=" + empty + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + "]";
	}
}
