package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class ListPiece implements Piece {
    private ListObject value;

    public ListPiece(Piece list) {
        if (list.getType() != PieceType.LIST) throw new IllegalArgumentException();
        String inside = list.getPiece().replaceAll("^\\[(.*)\\]$", "$1");
        value = new ListObject(inside.split(" "));
    }

    @Override
    public PieceType getType() {
        return PieceType.LIST;
    }

    @Override
    public ListObject getValue() {
        return value;
    }

    public static class ListObject {
        List<String> list;

        public ListObject(String... list) {
            this(Arrays.asList(list));
        }

        public ListObject(List<String> list) {
            this.list = new ArrayList<>(list);
            this.list.removeIf(Objects::isNull);
            this.list.removeIf(String::isEmpty);
        }

        public List<String> getList() {
            return new ArrayList<>(list);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if (i + 1 < list.size()) builder.append(" ");
            }
            builder.append("]");
            return builder.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ListObject that = (ListObject) o;

            return list.equals(that.list);
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }
    }
}