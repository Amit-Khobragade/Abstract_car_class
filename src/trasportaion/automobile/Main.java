package trasportaion.automobile;

public class Main {
	public static void print( Swift s1 ) {
		System.out.println();
		System.out.println( "........................................................" );
		System.out.println( "velocity:" + s1.getCurrentSpeed() );
		System.out.println( "direction(360):" + s1.getCurrentDirection() );
		System.out.println( "fuel left:" + s1.getfuelLeft() );
		System.out.println( "gear:" + s1.getGear() );
		System.out.println( "current passengers:" + s1.getCurrentPassengers() );
		System.out.println();
		System.out.println( "........................................................" );
	}
	public static void main(String[] args) {
		Swift car = new Swift( 1, 50 );
		System.out.println( "car::" + car.getName() );
		System.out.println( "fuel type:" + car.getFuelType() );
		System.out.println( "max number of passengers::" + car.getmaxNumOfPassenger() );
		System.out.println( "........................................................" );
		car.accelerate(10);
		print( car );
		car.ignition();
		print( car );
		car.accelerate(40);
		print(car);
		car.stop();
		print( car );
		car.turnOff();
		print(car);
		car.reduceFuel(100);
		print(car);
	}

}
