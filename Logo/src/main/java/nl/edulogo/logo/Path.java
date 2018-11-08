package nl.edulogo.logo;

import nl.edulogo.core.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Under_Koen on 24/09/2018.
 */
public class Path {
    private List<Position> path;

    public Path(Position... points) {
        path = new ArrayList<>(Arrays.asList(points));
    }

    public void addPoint(Position point) {
        path.add(point);
    }

    public void addPoints(Position... points) {
        path.addAll(Arrays.asList(points));
    }

    public void clear() {
        path.clear();
    }

    public Position getPoint(int index) {
        return path.get(index);
    }

    public Position[] getPoints() {
        return path.toArray(new Position[0]);
    }

    public Position[] getPoints(int from, int to) {
        if (from > to) throw new IllegalArgumentException("'from' should not be bigger than 'to'");
        int size = to - from + 1;
        Position[] points = new Position[size];
        for (int i = 0; i < path.size(); i++) {
            if (i >= from && i <= to) points[i] = path.get(i);
        }
        return points;
    }

    @Override
    public String toString() {
        return "Path{" +
                "path=" + path +
                '}';
    }
}
