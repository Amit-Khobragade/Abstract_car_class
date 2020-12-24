package trasportaion.automobile;

public class Swift extends Car {
	//constructor
	public Swift( int currentPassengers, int fuelLeft ){
		super( "Swift", 5, currentPassengers, fuelLeft, TypeOfFuel.diesel );
	}
	public Swift() {
		this( 0, 20 );
	}
}
