# 그룹화

자바 8 함수형을 이용하면 데이터 집합의 그룹화를 구현할 때 가독성 있게 구현이 가능하다.

```java
    @Test
    void groupExample() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
    }
```
```
{FISH=[prawns, salmon], MEAT=[pork, beef, chicken], OTHER=[french fries, rice, season fruit, pizza]}
```

`Collectors.groupingBy`를 이용해서 그룹화를 할 수 있다. 분류 함수라고 부른다.

1. 스트림의 각 요소 접근
2. 그룹화 연산의 결과로 그룹화 함수가 반환하는 키를 가져옴
3. 각 키에 대응하는 스트림의 모든 항목 리스트를 값으로 갖는 맵이 반환

```java
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
```
```
{DIET=[chicken, rice, season fruit, prawns], FAT=[pork], NORMAL=[beef, french fries, pizza, salmon]}
```

분류 기준이 더 복잡한 경우에는 메서드  참조를 분류 함수(`groupingBy`)로 사용할 수 없기 때문에, 메서드 참조 대신 람다 표현식으로 로직을 구현할 수 있다.


## 그룹화된 요소 조작

요소를 그룹화하면 결과 그룹의 요소를 조작하는 연산을 할 수도 있다.

```java
    @Test
    public void predicateGroup() {
        Map<Dish.Type, List<Dish>> caloricDishedByType = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        System.out.println(caloricDishedByType);
    }
```
```
{OTHER=[french fries, pizza], MEAT=[pork, beef]}
```

그룹화 하기 전에 Predicate 함수로 필터를 적용할 수 있다

근데 위와 같이 하면, 요소가 없는 그룹은 결과 맵에서 키 자체가 사라진다 ㅠㅠ

```java
 Map<Dish.Type, List<Dish>> caloricDishedByType2 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
```
```
{OTHER=[french fries, pizza], MEAT=[pork, beef], FISH=[]}
```

Collectors 클래스는 분류 함수에 Collector 형식의 두 번째 인수를 갖도록 groupingBy 팩토리 메서드를 오버로드해서 사용해서 해결할 수 있다.

filtering 메서드는 Collectors 클래스의 또 다른 정적 팩토리 메서드로 Predicate를 인수로 받는다.

### flatMapping 메서드
flatMap은 함수를 적용한 결과로 생성된 여러 스트림을 단일 스트림으로 평면화(flattening)하는 연산입니다. Java의 Stream API에서 flatMap 메서드는 이러한 작업을 수행합니다.

Collectors.flatMapping은 Collectors.groupingBy와 함께 주로 사용되는데, 그룹화된 결과에서 평면화를 수행하는 데 사용된다.
즉, 각 그룹에 대해 매핑 함수를 적용하고 그 결과를 단일 리스트로 평면화하여 최종 결과를 얻을 수 있당.

```java
    public static final Map<String, List<String>> dishTags = new HashMap<>();

    static {
        dishTags.put("pork", Arrays.asList("greasy", "salty"));
        dishTags.put("beef", Arrays.asList("salty", "roasted"));
        dishTags.put("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put("french fries", Arrays.asList("greasy", "fried"));
        dishTags.put("rice", Arrays.asList("light", "natural"));
        dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
        dishTags.put("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put("salmon", Arrays.asList("delicious", "fresh"));
    }

    @Test
    public void flatMappingExample() {
        Map<Dish.Type, Set<String>> groupDishTagsByType =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType,
                                flatMapping(dish -> Dish.dishTags.get(dish.getName()).stream(), toSet())));

        System.out.println(groupDishTagsByType);
    }
```
```
{MEAT=[salty, greasy, roasted, fried, crisp], FISH=[roasted, tasty, fresh, delicious], OTHER=[salty, greasy, natural, light, tasty, fresh, fried]}
```

## 다수준 그룹화
Collectors.groupingBy를 사용해서 다수준으로 그룹화할 수도 있다.

분류 함수와 컬렉터를 인수로 받는다.

groupingBy 메서드를 두 번 사용하여 스트림의 항목을 그룹화할 수 있다.

```java
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
```
```
{OTHER={DIET=[rice, season fruit], NORMAL=[french fries, pizza]}, MEAT={DIET=[chicken], NORMAL=[beef], FAT=[pork]}, FISH={NORMAL=[salmon], DIET=[prawns]}}
```

다수준 그룹화 연산으로 n 수준 그룹화 결과를 만들 수 있다.

n 수준 그룹화 결과는 n 수준 트리구조의 맵이 된다.


## 서브 그룹으로 데이터 수집

`groupingBy` 인수로 넘겨주는 컬렉터의 형식에는 제한이 없다.

왜냐. groupingBy의 시그니처는 다양한 형태의 컬렉터를 허용한다.

```java
public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(
    Function<? super T, ? extends K> classifier
)
```

`classfier`는 각 요소를 그룹화할 기준을 제공하는 함수이다.

얘는 오버로드 되어있어서, 다양한 형태의 groupingBy를 사용할 수 있는 것이었음..

```java
public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(
    Function<? super T, ? extends K> classifier,
    Collector<? super T, A, D> downstream
)
```

`downstream`은 각 그룹에 추가적입 작업을 수행해주는데, 컬렉터에 제한이 없도록 유연성을 제공해준다.

그룹화를 사용하면 다양한 수준의 그룹화가 가능하기 때문에 아래와 같이 사용할 수도 있다.

```java
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
    }
```
```
maxBy: Optional[pork]
groupingBy: {FISH=Optional[salmon], MEAT=Optional[pork], OTHER=Optional[pizza]}
```

가장 높은 칼로리의 음식이 뭔지 찾는 방법에서 음식 종류별로 가장 높은 칼로리 음식을 찾을 수 있다.

### collectingAndThen 메서드

위 예제에서 `Map<Dish.Type, Optional<Dish>>`에서 Optional을 삭제할 수도 있다.

```java
        Map<Dish.Type, Dish> mostCaloricByType =
                Dish.menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));
```
```
{FISH=salmon, MEAT=pork, OTHER=pizza}
```

아래와 같이 진행된다.

1. groupingBy로 Dish의 Type에 따라 menu 스트림을 서브 스트림으로 그룹화
2. groupingBy 컬렉터가 collectingAndThen 컬렉터를 감싼다
3. collectingAndThen 컬렉터가 maxBy(리듀싱 컬렉터 중 하나)를 감싼다
4. 리듀싱 컬렉터인 maxBy가 서브스트림 연산 수행한다
5. 서브스트림 연산 결과에 collectingAndThen의 Optional::get 변환 함수가 적용된다
6. groupingBy 컬렉터가 반환하는 맵의 키에 대응하는 값들에 가장 높은 칼로리가 맵핑된다

