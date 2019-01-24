public class TestPlanet {
	public static void main(String [] args){
		checkPlanets();
	}

	public static void checkPlanets(){
		Planet a = new Planet(0, 0, 10, 10, 100, "blah");
		Planet b = new Planet(10, 10, 10, 10, 100, "hh");
		double pairforce = a.calcForceExertedBy(b);
		System.out.println(pairforce);
	}
}