public class Body {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    /**
     * 
     * @param b
     * @return
     */
    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r2 = Math.pow(dx, 2) + Math.pow(dy, 2);
        double r = Math.sqrt(r2);
        return r;
    }

    public double calcForceExertedBy(Body b){
        double r = this.calcDistance(b);
        double F = (G * this.mass * b.mass) / Math.pow(r, 2);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        double Fx = F * (b.xxPos - this.xxPos)/r;
        return Fx;
    }
    public double calcForceExertedByY(Body b) {
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        double Fy = F * (b.yyPos - this.yyPos)/r;
        return Fy;
    }

}
