package pl.paweln.agricola.engine.util;

import java.security.SecureRandom;
import java.util.*;

public class Randomizer <T> {
    private List<T> list = new ArrayList<>();

    Random random;

    public Randomizer(List<T> inputList) {
        list.addAll(inputList);
        try {
            this.random = SecureRandom.getInstanceStrong();
        } catch (Exception e) {
            this.random = new Random();
        }
    }

    public T selectRandomElementAndRemove() {
        int randomIdx = random.nextInt(this.list.size());
        T element = this.list.get(randomIdx);
        this.list.remove(element);
        return element;
    }
}
