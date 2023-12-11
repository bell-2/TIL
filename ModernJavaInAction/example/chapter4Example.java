import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    @Test
    @DisplayName("스트림으로 바꿔보기")
    void streamsExample() {
        List<String> stringList = new ArrayList<>();
        for (String s : stringList) {
            if (s.length() > 10) {
                System.out.println("Streams.streamsExample");
            }
        }
    }

    List<Dish> menuList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        menuList.add(new Dish(100, "닭가슴살", false, Type.MEAT));
        menuList.add(new Dish(1500, "치킨", false, Type.MEAT));
        menuList.add(new Dish(500, "스프", false, Type.OTHER));
        menuList.add(new Dish(50, "야채볶음", true, Type.OTHER));
    }

    @Test
    @DisplayName("요리 클래스 정렬하기")
    void sortExample() {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish lowCaloricDish : menuList) {
            if (lowCaloricDish.getCalories() < 1000) {
                lowCaloricDishes.add(lowCaloricDish);
            }
        }

        Collections.sort(lowCaloricDishes, Comparator.comparingInt(Dish::getCalories));

        List<String> lowCalDishesName = new ArrayList<>();
        for (Dish lowCaloricDish : lowCaloricDishes) {
            lowCalDishesName.add(lowCaloricDish.getName());
        }
    }

    @Test
    @DisplayName("요리 클래스를 스트림으로 정렬하기")
    void sortStreamExample() {
        List<String> lowCalDishesName = menuList.stream()
                .filter(dish -> dish.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("요리 클래스를 parallel 스트림으로 정렬하기")
    void parallelStreamExample() {
        List<String> lowCalDishesName = menuList.parallelStream()
                .filter(dish -> dish.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }

    class Dish {
        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }

        private final int calories;
        private final String name;
        private final boolean vegetarian;
        private final Type type;

        Dish(int calories, String name, boolean vegetarian, Type type) {
            this.calories = calories;
            this.name = name;
            this.vegetarian = vegetarian;
            this.type = type;
        }
    }
}

