# 커스텀 컬렉터를 구현해서 성능 개선하기

```java
    public boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, (int) Math.sqrt((double) candidate ))
                .noneMatch(i -> candidate % i == 0);
    }
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate->isPrime(candidate)));
    }

    @Test
    void partitionExample() {
        System.out.println(partitionPrimes(10));
    }
```

n까지의 자연수를 소수와 비소수로 분할 하는 예제의 성능을 개선해보자


## 1. 소수로만 나누기

대상 숫자의 제곱근보다 작은 소수만 사용하도록 최적화가 필요하다

다음 소수가 대상의 루트보다 크면 소수로 나누는 검사를 더 할 필요가 없다

`filter` 함수는 전체 스트림을 처리한 다음에 결과를 반환해서, 숫자 범위나 리스트가 크게 되면 성능 문제가 발생할 수 있다

이를 `takeWhile` 메서드를 사용해서 해결할 수 있다

### takeWhile 메서드

Java의 takeWhile 메서드는 스트림을 나타내는 Stream 인터페이스에서 제공되는 메서드 중 하나다

이 메서드는 주어진 조건을 만족하는 요소만 포함된 새로운 스트림을 생성한다

스트림의 요소들 중에서 조건을 만족하는 동안만 요소를 취하고, 조건을 만족하지 않는 첫 번째 요소를 만나면 더 이상의 요소를 취하지 않는다

```java
    public static <A> List<A> takeWhile(List<A> list, Lamda.Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if(!p.test(item)) { // 리스트의 현재 항목이 Predicate를 만족하는지 확인
                return list.subList(0, i); // 만족하지 않는다면 현재 검사한 항목의 이전 항목 하위 리스트 반환
            }
            i++;
        }
        return list; // 리스트의 모든 항목
    }

    public boolean isPrime(List<Integer> primes, int candidate) {
        return takeWhile(primes, i -> i <= (int) Math.sqrt((double) candidate))
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }
```

## 2. Collector 클래스 시그니처 정의

`public interface Collector<T,A,R>` 인터페이스 정의를 참고해서 클래스 시그니처를 만들 수 있다

```java
public class PrimeNumbersCollector implements Collector<Integer, // 스트림 요소의 형식 
        Map<Boolean, List<Integer>>, // 누적자 형식
        Map<Boolean, List<Integer>>> { // 수집 연산의 결과 형식
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return null;
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
```

Collector 인터페이스를 오버라이딩하려면 5개의 인터페이스를 구현해야한다.

## 3. 리듀싱 연산 구현

Supplier 메서드는 누적자를 만드는 함수를 반환한다

누적자로 사용할 맵을 만들면서 true, false 키 값과 빈 리스트로 초기화 할 수 있다

빈 리스트에 소수와 비소수 리스트가 들어가게 된다

```java
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }};
    }
```

accumulator는 최적화의 핵심으로 스트림의 요소를 결정하게 된다

누적된 맵(중간 결과)에 원하는 키 값으로 접근할 수 있다

```java
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)) // 누적 맵에서 소수 리스트를 가져온다
                    .add(candidate);
        };
    }
```

- BiConsumer<Map<Boolean, List<Integer>>, Integer>: 이 메서드는 두 개의 인자를 받는 BiConsumer를 반환함. 첫 번째 인자는 누적 맵(Map<Boolean, List<Integer>>)이고, 두 번째 인자는 현재 스트림의 요소인 정수(Integer)이다

- acc.get(isPrime(acc.get(true), candidate)): 현재 요소(candidate)가 소수인지를 판별한 후, 해당하는 그룹의 리스트를 가져오기 위해 누적 맵에서 isPrime 메서드를 사용합니다. isPrime 메서드는 현재까지의 소수 리스트와 새로운 요소를 받아서 해당 요소가 소수인지를 판별하여 true 또는 false를 반환합니다.

- .add(candidate): 현재 요소를 판별된 그룹에 추가합니다. 이때 isPrime 메서드를 통해 얻은 true 또는 false에 따라 소수 그룹이나 비소수 그룹 중 하나에 추가됩니다.


## 4. 병렬 실행할 수 있는 컬렉터 만들기

누적자들을 합칠 수 있는 메서드가 필요하당

```java
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }
```

- BinaryOperator<Map<Boolean, List<Integer>>>: 이 메서드는 두 개의 인자를 받아서 하나의 결과를 반환하는 BinaryOperator를 반환합니다. 여기서 인자는 Map<Boolean, List<Integer>> 타입의 맵입니다.

- map1.get(true).addAll(map2.get(true)): 첫 번째 맵(map1)의 소수 그룹에 두 번째 맵(map2)의 소수 그룹을 추가합니다. 이때 addAll은 리스트에 모든 요소를 추가하는 메서드입니다.

- map1.get(false).addAll(map2.get(false)): 첫 번째 맵(map1)의 비소수 그룹에 두 번째 맵(map2)의 비소수 그룹을 추가합니다.

- return map1;: 결과로 합쳐진 맵을 반환합니다. 첫 번째 맵(map1)이 병합된 결과를 나타냅니다.

combiner 메서드는 병렬 스트림에서 서로 다른 서브스트림에서 나온 결과를 합치는 역할을 한다

이 코드에서는 소수 그룹과 비소수 그룹을 각각 병합하여 최종 결과를 만들 수 있다


## 5. finisher 메서드와 컬렉터의 characteristics 메서드

```java
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
```

## 성능 계산해보기

```java
    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    @Test
    void partitionExample() {
        long fastest = Long.MAX_VALUE;
        for(int i=0; i< 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println(fastest + " msecs");
    }
```

```java
 @Test
    void partitionExample() {
        long fastest = Long.MAX_VALUE;
        for(int i=0; i< 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println(fastest + " msecs");

        fastest = Long.MAX_VALUE;
        for(int i=0; i< 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println(fastest + " msecs");
    }
```