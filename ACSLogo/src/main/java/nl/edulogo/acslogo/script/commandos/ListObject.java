package nl.edulogo.acslogo.script.commandos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListObject {
    List<Value> list;

    public ListObject(Object... list) {
        this(Arrays.stream(list).map(Value::new).collect(Collectors.toList()));
    }

    public ListObject(Value... list) {
        this(Arrays.asList(list));
    }

    public ListObject(List<Value> list) {
        this.list = new ArrayList<>(list);
        this.list.removeIf(Objects::isNull);
        this.list.removeIf(v -> v.toString().isEmpty());
    }

    public List<Value> getList() {
        return new ArrayList<>(list);
    }

    public List<String> getStringList() {
        return list.stream().map(Value::toString).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("[%s]", getInner());
    }

    public String getInner() {
        return list.stream().map(Value::toString).collect(Collectors.joining(" "));
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
