public class NBody {
	public static double readRadius(String path) {
		In in = new In(path);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet [] readPlanets(String path) {
		In in = new In(path);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet [] allplanets = new Planet[N];
		for(int i = 0; i < N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			allplanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allplanets;
	}
}