package day7;

class  MovieApp{

	int total_seat = 10;

	synchronized void bookSeats(int seats) {

		if(total_seat >= seats) {

			System.out.println("Booked successfully" +seats);

			total_seat = total_seat - seats;

			System.out.println("Seats left" +total_seat);

		}

		else

		{

			System.out.println("Seats not booked");

			System.out.println("seats available" +total_seat);

		}

	}

}

public class MovieBooking extends Thread {

	static MovieApp m;

	 int seats;

	 public void run() {

		 m.bookSeats(seats);

	 }

	 public static void main(String[] args) {

		m = new MovieApp();

		MovieBooking user1 = new MovieBooking();

		user1.seats = 7;

		user1.start();

		MovieBooking user2 = new MovieBooking();

		user2.seats = 5;

		user2.start();

	}

}