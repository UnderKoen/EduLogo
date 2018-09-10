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

    @Override
    public String toString() {
        return "Polygon{" +
                "positions=" + positions +
                '}';
    }
}
