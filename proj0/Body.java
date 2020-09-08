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

    public Body(Body b) {
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
    public double calcDistance(Body b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r2 = Math.pow(dx, 2) + Math.pow(dy, 2);
        double r = Math.sqrt(r2);
        return r;
    }

    public double calcForceExertedBy(Body b) {
        double r = this.calcDistance(b);
        double F = (G * this.mass * b.mass) / Math.pow(r, 2);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        double Fx = F * (b.xxPos - this.xxPos) / r;
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double F = this.calcForceExertedBy(b);
        double r = this.calcDistance(b);
        double Fy = F * (b.yyPos - this.yyPos) / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodies) {
        double force = 0;
        for (Body body : allBodies) {
            if (body.equals(this)) {
                continue;
            }
            force += this.calcForceExertedByX(body);
        }
        return force;
    }

    public double calcNetForceExertedByY(Body[] allBodies) {
        double force = 0;
        for (Body body : allBodies) {
            if (body.equals(this)) {
                continue;
            }
            force += this.calcForceExertedByY(body);
        }
        return force;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        double vnewX = this.xxVel + dt * aX;
        double vnewY = this.yyVel + dt * aY;
        double pnewX = this.xxPos + dt * vnewX;
        double pnewY = this.yyPos + dt * vnewY;
        this.xxVel = vnewX;
        this.yyVel = vnewY;
        this.xxPos = pnewX;
        this.yyPos = pnewY;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
