package nl.edulogo.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class Polygon {
    private List<Position> positions;

    public Polygon(Position... points) {
        this.positions = new ArrayList<>(Arrays.asList(points));
    }

    public Position[] getPositions() {
        return positions.toArray(new Position[0]);
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public void addAllPositions(Collection<? extends Position> positions) {
        this.positions.addAll(positions);
    }

    public boolean contains(double x, double y) {
        return contains(new Position(x, y));
    }

    public boolean contains(Position test) {
        Position[] points = positions.toArray(new Position[0]);
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((points[i].getY() > test.getY()) != (points[j].getY() > test.getY()) &&
                    (test.getX() < (points[j].getX() - points[i].getX()) * (test.getY() - points[i].getY()) / (points[j].getY() - points[i].getY()) + points[i].getX())) {
                result = !result;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "positions=" + positions +
                '}';
    }
}
