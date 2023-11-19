# 2장 동작 파라미터

### 2장의 목표

- 유지보수가 쉬워야하고 새로운 기능이 추가될 때 쉽게 구현이 되어야 한다.
- 변경되는 요구사항에 효과적으로 대응해야할 방법이 필요하다.

### 동작 파라미터화

- 아직 어떻게 실행 될지 결정되지 않은 코드 블록.
- 코드 블록에 따라 메서드 동작이 파라미터화 된다.

첫 번째 시도: 녹색 사과 필터링

```java
public List<Apple> filterGreenApples(List<Apple> inventory) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : inventory) {
                if(Color.GREEN.equals(apple.getColor())) {
                    result.add(apple);
                }
            }
            return result;
        }

        public class Apple {
            private final Color color;
            Apple(Color color) {
                this.color = color;
            }
            public Color getColor() { return this.color;}
        }
```

- 녹색 사과를 필터링 하고 싶다~
- 만약 빨간 사과를 필터링 하고 싶다면? 색상이 늘어난다면?
- 추상화를 해보자

두 번째 시도: 색을 파라미터화

```java
public static class FilterGreenApples {

        public enum Color {RED, GREEN}

        @Test
        @DisplayName("녹색 사과 필터링")
        void filterGreenApples() {
            List<Apple> inventory = new ArrayList<>();
            inventory.add(new Apple(Color.GREEN));
            inventory.add(new Apple(Color.GREEN));
            inventory.add(new Apple(Color.RED));
            inventory.add(new Apple(Color.RED));
            inventory.add(new Apple(Color.GREEN));
            inventory.add(new Apple(Color.GREEN));

            // 첫 번째 시도
            // assertEquals(4, filterGreenApples(inventory).size());
            
            // 두 번째 시도
            assertEquals(2, filterGreenApples(inventory, Color.RED).size());
        }

        public List<Apple> filterGreenApples(List<Apple> inventory, Color color) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : inventory) {
                if(color.equals(apple.getColor())) {
                    result.add(apple);
                }
            }
            return result;
        }

        public class Apple {
            private final Color color;
            Apple(Color color) {
                this.color = color;
            }
            public Color getColor() { return this.color;}
        }
    }
```

- 파라미터로 색상을 받도록 변경했다
- 만약 무게도 필터링을 하고 싶다면?

```java
public List<Apple> filterGreenApplesByWeight(List<Apple> inventory, int weight) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : inventory) {
                if(apple.getWeight() > weight) {
                    result.add(apple);
                }
            }
            return result;
        }
```

- 무게를 필터링하는 함수가 추가됐다
- 색상을 필터링하는 부분이 중복된다.
- DRY (dont repeat yourself) 원칙을 어긋난 코드잖슴~

세번째 시도: 가능한 모든 경우의 수로 필터링한다

```java
public List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : inventory) {
                if( (flag && apple.getColor().equals(color)) ||
                        (!flag && apple.getWeight() > weight)) {
                    result.add(apple);
                }
            }
            return result;
        }
```

- flag가 난데없이 등장
- 책에서도 형편 없는 코드라고 해서 웃겼다
- 어떤 기준으로 사과를 필터링할건지 효과적으로 전달하는 방법이 필요하다
- → 동작 파라미터화로 유연성을 얻자.

## 동작 파라미터화

```java
public interface ApplePredicate {
            boolean test (Apple apple);
        }
```

- 어떤 속성에 기초해서 참과 거짓을 반환하는 프레디케이트 인터페이스를 생성
- 인터페이스를 상속 받아서 기능을 분리해보자.

```java

        
        public class AppleHeavyWeightPredicate implements ApplePredicate {

            @Override
            public boolean test(Apple apple) {
                return false;
            }
        }
        public class AppleGreenColorPredicate implements ApplePredicate {

            @Override
            public boolean test(Apple apple) {
                return false;
            }
        }
```

- 전략 디자인 패턴
    - 캡슐화하는 알고리즘 패밀리를 정의하고 런타임 시 알고리즘이 선택된다
    - ApplePredicate: 알고리즘 패밀리
    - 상속 받은 애들: 전략
- filterApples() 에서 객체를 받아서 조건을 검사하도록 바꾸면 되잖슴~
- 메서드가 다양한 동작을 하게 된다 → 이게 동작 파라미터화잖슴~

네번째 시도: 추상적 조건으로 필터링

```java
public List<Apple> filterApples(List<Apple> inventory, ApplePredicate applePredicate) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : inventory) {
                if(applePredicate.test(apple))
                    result.add(apple);
            }
            return result;
        }
```

- Predicate 프레디케이트 객체로 사과 검사 조건을 캡슐화
- 전달한 ApplePredicate 객체에 의해 함수의 동작이 결정된다
- 메서드의 동작이 파라미터화 되었다는 것이다
- 근데 만약 빨간 무거운 사과를 필터링 하고 싶다면?

```java
public class AppleRedHeavyPredicate implements ApplePredicate {

            @Override
            public boolean test(Apple apple) {
                /**
                 * 로직
                 */
                return false;
            }
        }
```

- 이런 클래스들이 계속해서 추가가 될 것이다.
- 중복을 어떻게 줄이고 복잡하지 않게 만들수 있을까

## 복잡한 과정 간소화해보자

### 익명 클래스

- 코드의 양을 줄일 수 있다
- 자바의 지역 클래스와 비슷한 개념으로, 이름이 없는 익명인 클래스란 뜻이다
- 클래스 선언과 인스턴스화를 동시에 할 수 있다

다섯번째 시도: 익명 클래스

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/9b028348-f725-448b-8882-7c495c0995d6/ad3f17bf-4c8e-4561-81e0-2fc5531f1875/Untitled.png)

- 익명 클래스를 이용해서 다시 구현
- 메서드 동작을 익명 클래스로 만들어서 직접 파라미터화 했다.
- 람다를 추천해주는군

여섯번째 시도: 람다 표현식

```java
List<Apple> greenApples = filterApples(inventory, apple -> false);
```

- 매우 깔꼼..

일곱번째 시도: 리스트 형식으로 추상화

```java
void filterList() {
            List<Apple> inventory = new ArrayList<>();
            List<Apple> redApples = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
        }
        
        public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
            List<T> result = new ArrayList<>();
            for (T t : list) {
                if(predicate.test(t)) {
                    result.add(t);
                }
            }
            return result;
        }
```

- 형식 파라미터 T로 클래스를 생성
- 필터 메서드를 사용하여, 사과 뿐만 아니라 바나나, 오렌지 등등에 사용할 수 있다

## 실전 예제

- Comparator 로 정렬하기
- Runnable로 코드 블록 실행하기
- Callable을 결과로 반환하기
- GUI 이벤트 처리하기

Comparator로 정렬하기

- 컬렉션 정렬
- 자바 8은 List에 sort 메서드가 포함되어있다
- Collections.sort는 물론 있음
- java.util.Comparator 객체를 이용해서 sort 동작을 파라미터화 할 수 있다

```java
List<Apple> inventory = new ArrayList<>();
            List<Apple> redApples = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

            inventory.sort(new Comparator<Apple>() {
                @Override
                public int compare(Apple o1, Apple o2) {
                    return o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight();
                }
            });
```

- 농부의 요구사항이 바뀌면 새로운 Comparator를 만들어서 sort 메서드에 전달해주면 된다.
- 익명ㅇ 클래스를 사용했다

```java
inventory.sort((o1, o2) -> o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight());
```

- 람다로 휘리릭
- intellij는 참 편하다..

Runnable로 코드 블록 실행하기

- 자바 스레드를 이용하면 코드 블록을 병렬로 실행할 수 있다
- 여러 스레드가 각자 다른 코드를 실행할 수 잇음
- ~ 자바 8까지 : Thread 생성자에 객체만을 전달할 수 있으므로, 보통 결과를 반환 안하는 void run 같은 메소드를 포함하는 익명 클래스가 Runnable 인터페이스를 구현하도록 하는게 일반적.

```java
void threadTest() {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("FilterGreenApples.run");
                }
            });
        }
```

- 람다로 바꾸면

```java
void threadTest() {
            Thread thread = new Thread(() -> System.out.println("FilterGreenApples.run"));
        }
```

Callable를 결과로 반환하기

- ExecutorService 추상화 개념: 자바 5부터 지원.
    - 태스크 제출과 실행 과정의 연관성을 끊어줌. (???)
- 태스크를 스레드 풀로 보내고, 결과를 Future로 저장할 수 있음
    - Runnable 방식과 다른 점.

```java
void callableTest() {
            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return Thread.currentThread().getName();
                }
            });
        }
```

- 킹다

```java
void callableTest() {
            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<String> future = executorService.submit(() -> Thread.currentThread().getName());
        }
```

---

## 결론 
- 동작 파라미터화에서는 코드를 메서드 인수로 전달한다. 메서드 내부적으로 다양한 동작을 수행할 수 있도록 하기 위해서이다.
- 요구사항에 잘 대응 → 엔지니어링 비용 감소
- 자바 8로 오며, 익명 클래스와 람다 등을 이용해서 코드를 깔끔하게 바꿀 수 있다
- 정렬, 스레드, GUI 처리 등을 다양한 동작으로 파라미터화 할 수 있다.