package trasportaion.automobile;

public class Car extends Vehicle {
	//fields
	private int fuelLeft;																	//in percent 
	private int currentGear;
	private TypeOfFuel fuel;
	private boolean isOn;
	private int currentPassengers;
	
	
	//constructors
	public Car() {
		this( "Default" );
	}
	public Car( String name, int maxNumOfPassengers,int currentPassengers, int fuelLeft, TypeOfFuel fuel ) {
		super( name, maxNumOfPassengers );
		this.fuel = fuel;
		this.setCurrentPassengers(currentPassengers);;
		this.setFuelLeft( fuelLeft );
		this.currentGear = 1;
		isOn = false;
	}
	public Car( String name ) {
		this( name, 4, 0, 0, TypeOfFuel.noType );
	}
	
	//getters
	public int getGear() {
		return this.currentGear;
	}
	public int getfuelLeft() {
		return this.fuelLeft;
	}
	public int getCurrentPassengers() {
		return this.currentPassengers;
	}
	public TypeOfFuel getFuelType() {
		return this.fuel;
	}
	
	//setters
	public void setFuelLeft( int fuelLeft ) {
		this.fuelLeft = ( fuelLeft <= 0 )? 0: ( fuelLeft >= 100 )? 100: fuelLeft;
		if( this.fuelLeft <= 0 ) {
			System.out.println( "Sorry no fuel..." );
			this.turnOff();
		}
	}
	
	public void setCurrentPassengers( int currentPassengers) {
		if( super.getmaxNumOfPassenger() > currentPassengers && currentPassengers >= 1) {
			this.currentPassengers = currentPassengers;
		}
		else if( currentPassengers < 1) {
			System.out.println( "error no Passengers turning the car off" );
			this.turnOff();			
		}
		else {
			System.out.println( "error greater than max limit... keeping some passengers out " );
			this.currentPassengers = super.getmaxNumOfPassenger();
		}
	}
	
	//methods 
	@Override
	public void accelerate( int increaseInVelocity ) {										//can be positive or negative
		//checks if the car is on or not and 
		//then change the gear if necessary
		
		if( !isOn ) {
			System.out.println( "please start the car first..." );
			return;
		}
		super.accelerate( increaseInVelocity );
		int speed = super.getCurrentSpeed();
		
		//changes the gear according to the speed
		if( speed < 20)
			this.currentGear = 1;
		else if( speed < 30 )
			this.currentGear = 2;
		else if( speed < 40 )
			this.currentGear = 3;
		else if( this.currentGear < 4 )
			this.currentGear = 4;		
	}
	
	
	public void changeGear( int numberOfGear ) {											// +1/-1
		//accepts +1 or -1  as an argument to increase
		//or decrease the current gear respectively and 
		//if any errors are met the car is generally turned 
		//off
		
		if( !isOn || numberOfGear == 0 ) {
			System.out.println( ( isOn )? "": "please start the car first..." );
			return;
		}
		boolean errorStatus = false;
		
		//checks whether you are changing more than one
		//and whether you exceeding the number of gears
		//in the vehicle and sets error status to true
		//if any such errors are matched
		if( ( numberOfGear == 1 && this.currentGear < 7 ) || ( numberOfGear == -1 && this.currentGear > 0 ) ) {
				this.currentGear += numberOfGear;
		}
		else if ( numberOfGear == 1 || numberOfGear == -1 )  {
				System.out.println( "error:: gear" + (( numberOfGear == 1 )?" > 6": " < 0" ));
				errorStatus = true;
		}
		else {
			System.out.println( "changed more than 1 gear at once car shutting.....");
			errorStatus = true;
			this.turnOff();
		}
		
		//error message is already printed in the above
		//if else and error status is set to true if there
		//is an error
		if( errorStatus )
			return;
		
		//these statements can be only reached if and only 
		//if no errors are encountered before in the function
		//the below function also uses the same variable error 
		//status which is false and checks if the speed is too less
		//or too much for the gear and acts accordingly
		if( this.currentGear == 0 ) {
			super.setCurrentSpeed(5);
			System.out.println( "reversing....");
			errorStatus = true;
		}
		else if( this.currentGear < 4 && ( ( this.currentGear + 1 ) * 10) > super.getCurrentSpeed() ) {
			System.out.println( "speed too lesss.....");
			this.turnOff();
			errorStatus = true;
		}
		else if( this.currentGear < 4 && ( ( this.currentGear - 1 ) * 10) < super.getCurrentSpeed() ) {			
			super.setCurrentSpeed( ( this.currentGear ) * 10 );
		}
		
		//this is only displayed if no errors are met and if
		//the car is not reversing
		if( !errorStatus )
			System.out.println( "current gear::" + this.currentGear );
		
	}
	
	public void ignition() {
		//turns the car on
		
		if( this.currentPassengers < 1 ) {
			System.out.println( "nobody in the car to start the car");
			return;
		}
		if( this.fuelLeft < 0 ) {
			System.out.println( "ran out of fuel... unable to start..." 
												+ "\ntry after refueling...");
			return;
		}
		System.out.println( "car is starting...." + "\ncar had started....");
		this.isOn = true;
		this.currentGear = 1;
	}
	
	public void turnOff() {
		//turns the car off
		
		if( !this.isOn ) {
			System.out.println( "car is already off....");
			return;
		}
		
		super.setCurrentSpeed( 0 );
		this.isOn = false;
		System.out.println( "car had turned off....");
		this.currentGear = 1;
	}
	
	@Override
	public void stop() {
		//stops the car immediately
		
		if( !isOn ) {
			System.out.println( "the car was already at rest" );
			return;
		}
		super.setCurrentSpeed( 0 );
		this.currentGear = 1;		
	}
	
	public void reduceFuel( int percentage ) {
		//decreases the amount of fuel in the car by 
		// percentage
		if( percentage < 0 || percentage > 100 ) {
			System.out.println( "Error: please enter a valid value for fueling..." );
			return;
		}
		this.fuelLeft -= percentage;
		if( this.fuelLeft < 0 ) {
			if( isOn ) {
				this.turnOff();
				System.out.println("car shutting down...");
			}
			System.out.println( "ohh noo:: no fuel left... \nplease refuel before continuing");
			this.fuelLeft = 0;
		}
	}
	
	public void refuel( int percentage ) {
		if( percentage < 0 || percentage > 100) {
			System.out.println( "Error: please enter a valid percentage" );
		}
		this.fuelLeft += percentage;
		if( this.fuelLeft > 100 ) {
			System.out.println( "ohh noo:: fuel overflow it is hazardous be carefull while filling");
			this.fuelLeft = 100;
		}
	}
	
}
