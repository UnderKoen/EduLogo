package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.commandos.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class ListPiece implements Piece {
    private Value value;

    public ListPiece(Piece list) throws ParsingException {
        if (list.getType() != PieceType.LIST) throw new IllegalArgumentException();
        String inside = list.getPiece().replaceAll("^\\[(.*)\\]$", "$1");
        List<String> l = new ArrayList<>();
        StringBuffer s = new StringBuffer();
        int index = 0;

        for (char c : inside.toCharArray()) {
            if (c == '[') {
                index++;
            } else if (c == ']') {
                index--;
                s.append(c);
                c = ' ';
            }

            if (c == ' ' && index == 0) {
                l.add(s.toString());
                s = new StringBuffer();
            } else {
                s.append(c);
            }
        }

        String last = s.toString();
        if (!last.isEmpty()) {
            l.add(last);
        }

        value = new Value(new ListObject(l));
    }

    @Override
    public PieceType getType() {
        return PieceType.LIST;
    }

    @Override
    public Value getValue() {
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
            return String.format("[%s]", String.join(" ", list));
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