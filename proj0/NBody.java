public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        in.readInt();
        in.readDouble();
        Body[] allbodies = new Body[5];
        for (int i = 0; i < 5; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allbodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return allbodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] allbodies = readBodies(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Body b : allbodies) {
            b.draw();
        }

        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time <= T) {
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int i = 0; i < 5; i++) {
                xForces[i] = allbodies[i].calcNetForceExertedByX(allbodies);
                yForces[i] = allbodies[i].calcNetForceExertedByY(allbodies);
            }
            for (int i = 0; i < 5; i++) {
                allbodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Body b : allbodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", allbodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allbodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", allbodies[i].xxPos, allbodies[i].yyPos,
                    allbodies[i].xxVel, allbodies[i].yyVel, allbodies[i].mass, allbodies[i].imgFileName);
        }
    }
}
