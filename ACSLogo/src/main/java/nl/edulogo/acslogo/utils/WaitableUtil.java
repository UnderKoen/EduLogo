package nl.edulogo.acslogo.utils;

import nl.edulogo.acslogo.handlers.Waitable;
import nl.edulogo.acslogo.script.ExecutorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class WaitableUtil {
    public static <T> T waitFor(Waitable<T> waitable) throws ExecutorException {
        waitable.start();
        while (!waitable.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new ExecutorException("Stopped program");
            }
        }
        return waitable.reset();
    }

    public static Waitable waitFor(Waitable... waitables) throws ExecutorException {
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
            } catch (InterruptedException e) {
                throw new ExecutorException("Stopped program");
            }
        }

        Waitable r = waitable.get();

        waitableList.remove(r);
        waitableList.forEach(Waitable::stop);

        return r;
    }
}
