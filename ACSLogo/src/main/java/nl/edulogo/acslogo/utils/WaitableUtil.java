package nl.edulogo.acslogo.utils;

import nl.edulogo.acslogo.handlers.mouse.Waitable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class WaitableUtil {
    public static <T> T waitFor(Waitable<T> waitable) {
        waitable.start();
        while (!waitable.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
        return waitable.reset();
    }

    public static Waitable waitFor(Waitable... waitables) {
        List<Waitable> waitableList = new ArrayList<>(Arrays.asList(waitables));
        waitableList.forEach(Waitable::start);

        Stream<Waitable> check = waitableList.stream()
                .filter(Waitable::isDone);

        Optional<Waitable> waitable = check.findAny();
        while (!waitable.isPresent()) {
            try {
                Thread.sleep(10);
                waitable = waitableList.stream()
                        .filter(Waitable::isDone)
                        .findAny();
            } catch (InterruptedException ignored) {
            }
        }

        Waitable r = waitable.get();

        waitableList.remove(r);
        waitableList.forEach(Waitable::stop);

        return r;
    }
}
