package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public class ArrayProvider {

    @Getter
    @Setter
    private Configuration configuration;

    @AllArgsConstructor
    @Builder
    public static class Configuration {
        @Builder.Default
        private int count = 1000;
        @Builder.Default
        private int lowerValue = 1;
        @Builder.Default
        private int upperValue = 300;
    }

    public int[] array(Configuration configuration) {
        return array(configuration.count, configuration.lowerValue, configuration.upperValue);
    }

    public int[] array(int count, int lowerValue, int upperValue) {
        if (count > 1000) {
            throw new RuntimeException("count '" + count + "' can not be more than 1000");
        }
        final Random random = new Random(new Random().nextLong());
        return random.ints(lowerValue, upperValue + 1)
                .limit(random.nextInt(1, count + 1))
                .sorted()
                .toArray();
    }

    public int[] array(int count) {
        return array(count, 1, 300);
    }

    public int[] array() {
        return array(1000);
    }
}