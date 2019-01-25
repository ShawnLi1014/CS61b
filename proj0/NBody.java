public class NBody {
	/** Read in Radius of the scope */
	public static double readRadius(String path) {
		In in = new In(path);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/** Read in all the planets */
	public static Planet [] readPlanets(String path) {
		In in = new In(path);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet [] planets = new Planet[N];
		for(int i = 0; i < N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return planets;
	}

	public static void main(String [] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet [] planets = readPlanets(filename);

		/* Draw the starfield background */
		StdDraw.setScale(radius * (-1), radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.show();

		for(Planet p : planets){
			p.draw();
		}

		StdDraw.enableDoubleBuffering();

		for(int time = 0; time <= T; time += dt){
			double [] xForces = new double[planets.length];
			double [] yForces = new double[planets.length];
			
			for(int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for(Planet p : planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}


}