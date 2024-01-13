import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class group {
    @Test
    public void groupExample() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);
    }

    enum CaloricLevel {DIET, NORMAL, FAT}

    ;

    @Test
    public void groupCaloricLevel() {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));
        System.out.println(dishesByCaloricLevel);
    }

    @Test
    public void predicateGroup() {
        Map<Dish.Type, List<Dish>> caloricDishedByType = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        System.out.println(caloricDishedByType);
    }

    @Test
    public void flatMappingExample() {
        Map<Dish.Type, Set<String>> groupDishTagsByType =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType,
                                flatMapping(dish -> Dish.dishTags.get(dish.getName()).stream(), toSet())));

        System.out.println(groupDishTagsByType);
    }

    @Test
    public void multipleGroupExample() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400)
                                        return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700)
                                        return CaloricLevel.NORMAL;
                                    else
                                        return CaloricLevel.FAT;
                                })
                        ));
        System.out.println(dishesByTypeCaloricLevel);
    }

    @Test
    public void maxByExample() {
        // 원래는 이랬는데
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(dishComparator));

        System.out.println("maxBy: " + mostCalorieDish);

        // 요래 됐슴당
        Map<Dish.Type, Optional<Dish>> mostCalorieDishGrouping =
                Dish.menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("groupingBy: " + mostCalorieDishGrouping);

        Map<Dish.Type, Dish> mostCaloricByType =
                Dish.menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println("collectingAndThen: " + mostCaloricByType);
    }

    @Test
    public void partionExample() {
        Map<Boolean, List<Dish>> partitionedMenu =
                Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(partitionedMenu);
        System.out.println(vegetarianDishes);
    }

    public boolean isPrime(int candidate) { // 소수인지 비소수인지 판단하는 함수
        int candidateRoot = (int) Math.sqrt((double) candidate); // 제곱근 이하의 수로 효율적으로 찾기
        return IntStream.range(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0); // candidate를 정수로 나눌 수 없으면 참을 반환
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        // 2부터 n까지의 숫자를 포함하는 스트림을 생성하여, 소수와 비소수 분류하기
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    @Test
    public void isPrimeTest() {
        System.out.println(partitionPrimes(10));
    }
}
