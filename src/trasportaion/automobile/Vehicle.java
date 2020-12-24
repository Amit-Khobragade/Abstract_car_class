package trasportaion.automobile;

public class Vehicle {
	//fields
	private String name;
	private int maxNumOfPassenger;
	private int currentSpeed;
	private int currentDirection;		//in 360 degrees
	
	//constructors
	public Vehicle(){
		this( "Default", 0, 0, 0 );
	}
	public Vehicle( String name ){
		this( name, 0, 0, 0 );
	}
	public Vehicle( String name, int numOfPassengers, int speed, int direction ){
		this.currentDirection = direction % 360;
		this.currentSpeed = speed;
		this.name = name;
		this.maxNumOfPassenger = numOfPassengers;
	}
	public Vehicle( String name, int numOfPassengers ){
		this( name, numOfPassengers, 0, 0 );
	}
	
	//getters
	public int getCurrentSpeed() {
		return currentSpeed;
	}
	public int getCurrentDirection() {
		return currentDirection;
	}
	public String getName() {
		return name;
	}
	public int getmaxNumOfPassenger() {
		return maxNumOfPassenger;
	}
	
	//setters
	public void setCurrentSpeed( int currentSpeed ) {
		this.currentSpeed = currentSpeed;
	}
	public void setCurrentDirection( int currentDirection ) {
		this.currentDirection = currentDirection;
	}
	
	//methods 
	public void steer( int degrees ) {					//can be positive or negative 
		this.currentDirection += degrees;
		if ( this.currentDirection > 360 || this.currentDirection < -360 )
			this.currentDirection %= 360;
		if ( this.currentDirection < 0 )
			this.currentDirection += 360;
	}
	public void accelerate( int increaseInVelocity ) {	//can be positive or negative
		if( this.currentSpeed > increaseInVelocity || increaseInVelocity > 0 )
			this.currentSpeed += increaseInVelocity;
		else
			this.currentSpeed = 0;
	}
	public void stop() {
		System.out.println( name + " is being stopped" );
		this.currentSpeed = 0;
	}
}
