public class Planet {
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

    /** First constructor */
	public Planet (double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
    
    /** Second constructor */
	public Planet (Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculate the distance between two planets*/
	public double calcDistance(Planet p) {
		double r;
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	/** Calculate the total force exerted by another planet */
	public double calcForceExertedBy(Planet p) {
		double dist = this.calcDistance(p);
		double f = 6.67E-11 * this.mass * p.mass / (dist * dist);
		return f;
	}

	/** Calculate the x force exerted by another planet */
	public double calcForceExertedByX(Planet p) {
		double totalF = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double fx = totalF * (p.xxPos - this.xxPos) / r;
		return fx;
	}

	/** Calculate the y force exerted by another planet */
	public double calcForceExertedByY(Planet p) {
		double totalF = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double fy = totalF * (p.yyPos - this.yyPos) / r;
		return fy;
	}

	/** Calculate x net force */
	public double calcNetForceExertedByX(Planet [] allPlanets){
		double netFx = 0;
		for(Planet p: allPlanets) {
			if(this.equals(p)){
				continue;
			}
			netFx = netFx + this.calcForceExertedByX(p);
		}
		return netFx;
	}

	/** Calculate y net force */
	public double calcNetForceExertedByY(Planet [] allPlanets){
		double netFy = 0;
		for(Planet p: allPlanets) {
			if(this.equals(p)){
				continue;
			}
			netFy = netFy + this.calcForceExertedByY(p);
		}
		return netFy;
	}

	/** Update the status of the planet */
	public void update(double dt, double fx, double fy){
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.xxVel = this.xxVel + ax * dt;
		this.yyVel = this.yyVel + ay * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}