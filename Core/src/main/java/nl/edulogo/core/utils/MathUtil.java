package nl.edulogo.core.utils;

import nl.edulogo.core.Position;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class MathUtil {
    private static final double HALF_PI = Math.PI / 2;

    private MathUtil() {
    }

    public static Position getRelativePosition(double rotation, double distance) {
        if (rotation < 0)
            throw new IllegalArgumentException("Rotation can't be negative");
        double x = 0;
        double y = 0;
        rotation = rotation % 360;
        if (rotation % 90 == 0) {
            switch ((int) rotation) {
                case 0:
                    y = distance;
                    break;
                case 90:
                    x = distance;
                    break;
                case 180:
                    y = -distance;
                    break;
                case 270:
                    x = -distance;
                    break;
                default:
                    throw new IllegalArgumentException("This should not happen...");
            }
        } else {
            double radian = Math.toRadians(rotation);
            double radianOpposite = HALF_PI - radian;
            x = (Math.sin(radian) * distance);
            y = (Math.sin(radianOpposite) * distance);
        }

        return new Position(x, y);
    }

    public static double round(double number, double decimals) {
        if (decimals < 0)
            throw new IllegalArgumentException("Can't round on negative decimals");
        return (int) ((number + 5 / Math.pow(10, decimals + 1)) * Math.pow(10, decimals)) / Math.pow(10, decimals);
    }
}
