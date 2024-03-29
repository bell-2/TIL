# 람다 표현식

람다표현식
익명클래스처럼 이름이 없는 함수. 메서드를 인수로 전달할 수 있다

람다 용어 자체는 미적분학 학계에서 유래됨.


## 1. 람다란 무엇인가?

람다 표현식: 메서드로 전달할 수 있는 익명 함수를 단순화한 것.<b>

### 람다 특징
- 익명: 메서드와 다르게 이름이 없음
- 함수: 특정 클래스에 종속되지 않음. 그래서 함수라고 불림. 하지만 메서드의 특징 또한 가짐.

cf) 메서드의 특징은? 파라미터 리스트, 바디, 반환 형식, 예외 리스트 포함.

- 전달: 람다 표현식을 메서드 인수로 전달하거나 변수로 저장 가능.
- 간결성: 익명 클래스처럼 중복되거나 클래스 생성을 위한 코드를 구현할 필요 없음. 판에 박힌 코드 ㅋㅋ



```

    @Test
    @DisplayName("Comparator 람다로 변환")
    void comparatorTest() {
        Comparator<Apple> appleComparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight()> o2.getWeight()?o1.getWeight():o2.getWeight();
            }
        };
        
        Comparator<Apple> lamdaComparator = (o1, o2) -> o1.getWeight()> o2.getWeight()?o1.getWeight():o2.getWeight();
    }
```



### 람다 너 뭔데 어떻게 쓰는건데.

람다 표현식 스타일 특징 1)

람다 표현식 구성: 파라미터, 화살표, 바디로 이뤄짐.

```
	(o1, o2) -> o1.getWeight()> o2.getWeight() ?o1.getWeight():o2.getWeight();
```


- (o1, o2) : 람다 파라미터. 파라미터 리스트. Comparator의 compare 메서드 파라미터
- -> : 화살표는 왼쪽좌는 파라미터 리스트, 오른좌는 바디를 구분함. 구분좌임.
- 오른쪽: 람다 바디. 반환 값에 해당하는 표현식.



return 구문이 사라져있다. 그렇다. 람다 표현식에서는 return을 사용하지 않아도 된다.


람다 표현식 스타일 특징 2)


```

    @Test
    @DisplayName("람다 표현식 예제")
    void lamdaExample() {
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("Lamda.compare. 여러 줄이 가능하다!");
                System.out.println("Lamda.compare. 여러 줄이 가능하다!");
                return 0;
            }
        };

        Comparator<Integer> integerComparator2 = (o1, o2) -> {
            System.out.println("Lamda.compare. 여러 줄이 가능하다!");
            System.out.println("Lamda.compare. 여러 줄이 가능하다!");
            return 0;
        };
    }

```

그렇다. 람다 표현식에서는 여러 줄 표현도 가능하다.


람다 표현식 스타일 특징 3)

``` java
(parameter) -> expression
(parameters) -> { statements; }
```

블록 스타일로 표현할 수 있다


cf) 흐름 제어문 return

```
(Integer i) -> return i; // 틀림!
(Integer i) -> { return i; } // 맞음!
```


### 2. 람다 사용법

람다 표현식은 함수형 인터페이스/함수 디스크립터로 사용할 수 있다


1) 함수형 인터페이스

```java
public interface Predicate<T> {
	boolean test (T t);
}
```

Predicate<T>는 오직 하나의 추상 메서드만 지정하는 함수형 인터페이스이다.

함수형 인터페이스?는 하나의 추상메서드를 지정하는 인터페이스다.
자바 API의 함수형 인터페이스는 Comparator, Runnale 등이 있다.

왜 함수 인터페이스 얘기가 나왔을까.

람다 표현식을 함수형 인터페이스의 인스턴스로 만들 수 있다.
정확히는 함수형 인터페이스를 구현한 클래스의 인스턴스.

익명 내부 클래스도 구현 가능하다.

```
public static void process(Runnable runnable) {
        runnable.run();
    }

    @Test
    @DisplayName("람다 표현식으로 함수형 인터페이스 전달")
    void functionalInterface() {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Lamda.run");
            }
        };
        Runnable runnable2 = () -> System.out.println("Lamda.functionalInterface");

        process(runnable1);
        process(runnable2);
        process(()-> System.out.println("Lamda.functionalInterface 직접 람다로 전달"));
    }
```

Runnable은 함수형 인터페이스기 때문에, 람다 표현식으로 구현이 가능하다.


2) 함수 디스크립터

함수 디스크립터가 무엇이냐.

람다 표현식의 시그니처를 서술하는 메서드이다.

람다 표현식의 시그니처는 뭐냐.

함수형 인터페이스의 추상 메서드 시그니처이다.

함수형 인터페이스의 추상 메서드 시그니처를 알면 그 인터페이스를 람다 표현식으로 표현할 때 필요한 매개변수 타입 및 반환 타입을 이해할 수 있다능.

시그니처는 즉, 함수의 타입이다.

람다 말고, 일반 메서드를 통해 시그니처를 봐보겠다.

```java
public int add(int x, int y) {
    // 메서드 본문
}
```

메서드의 시그니처는 add(int x, int y)이다.

이를 통해 메서드의 이름이 "add"이고 파라미터는 두 개의 정수를 받고, 반환 타입은 정수(int)임을 알 수 있다.

람다의 시그니처 예제도 봐보자.

```java
Runnable myRunnable = () -> {
    // 실행 코드
};

```
람다 표현식의 시그니처는 () -> void다.

이것은 파라미터를 받지 않고, 반환값이 없는 함수구나~~라는 것을 시그니처를 통해 알 수 있다.

즉, 자바에서 시그니처는 메서드나 함수의 이름, 매개변수의 타입, 그리고 반환 타입을 통해 정의되는데, 

시그니처를 통해 코드를 이해하고 유지보수하는 데 도움이 된당.


그래서 `() -> void` 표기는 파라미터 리스트가 없으며 void를 반환하는 함수를 의미함.

`(Apple, Apple) -> int`: 두 개의 Apple를 인수로 받아 int를 반환하는 함수란 뜻이잖슴~


=> 람다 표현식은 변수에 할당하거나 함수형 인터페이스를 인수로 받는 메서드로 전달할 수 있음.

이쯤되면, 궁금해진다. 왜 함수형 인터페이스를 인수로 받는 메서드에만 람다 표현식이 사용 가능한지.

이유는 언어 설계자들이 복잡하지 않고 더 익숙한 방법을 사용할 수 있도록 한다고 함.


헷갈렸던 거

- 함수 디스크립터는 람다 표현식의 타입을 설명하기 위한 개념
- 실제로 코드에서는 함수 디스크립터를 직접 사용하는 것은 아님!!
- 람다 표현식을 통해 컴파일러가 자동으로 함수 디스크립터를 유추하기 위한 것임.



3. 람다 활용법: 실행 어라운드 패턴

실행 어라운드 패턴

-> 실제 자원을 처리하는 코드를 설정과 정리 두 과정이 둘러싼 형태를 말함

실행 어라운드 패턴을 활용한 예제를 봐보자.


```java
public String processFile() throws Exception {
        try(
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))) {
            return bufferedReader.readLine();
        }
    }

```

이를 함수형 인터페이스와 람다를 이용해서 전달해보자.


```java

@FunctionalInterface
    public interface BufferReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException;
    }

@Test
    @DisplayName("실행 어라운드 패턴")
    void excuteAroundPattern() throws FileNotFoundException {
        // processFile() 함수의 동작을 파라미터화
        //String result = processFile(new BufferedReader(new FileReader("example.txt")));

        // 함수형 인터페이스로 전달
        String result2 = processFile((BufferedReader br) -> {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
```

함수형 인터페이스로 바꿔서 해봤다.

그러면 두 줄을 처리하는 코드도 작성 가능하다.

```java
        String twoLineResult = processFile((BufferedReader bufferReader) -> bufferReader.readLine() + bufferReader.readLine());


```

### 3. 함수형 인터페이스 사용
함수형 인터페이스 별도 글 참고!

중요한 점은, 함수형 인터페이스는 오직 하나의 추상 메서드를 지정한다.

그럼 이 추상 메서드는 뭐다? 람다 표현식의 시그니처를 묘사하게 된다.

이를 함수 디스크립터라고 한다.

공통의 함수 디스크립터를 기술하는 함수형 인터페이스 집합이 있어야 다양한 람다 표현식 사용이 가능하다.

자바 API의 Comparable, Runnable, Callable 등.

자바 8 라이브러리에는 java.util.function 패키지로 여러 가지 새로운 함수형 인터페이스를 제공한당.


1. Predicate

`java.util.function.Predicate<T>` 인터페이스는 test 라는 추상 메서드를 정의한다.

boolean 표현식이 필요할 때 Predicate 인터페이스를 사용할 수 있다.

```java
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if(predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    @Test
    @DisplayName("Predicate 예제")
    void predicateExample() {
        Predicate<String> emptyStringPredicate = (String s) -> s.isEmpty();
        List<String> stringList = new ArrayList<>();
        stringList.add("TEST");
        stringList.add("TEST2");
        List<String> emptyStringList = filter(stringList, emptyStringPredicate);
        assertEquals(emptyStringList.size(), 0);
    }
```

2. Consumer

`java.util.function.Consumeer<T>` 인터페이스는 void를 반환하는 accept 라는 추상 메서드를 정의함.

Consumer의 단어의 뜻은 소비자, 소비하다란 뜻이잖슴?

단어 뜻 그대로, 반환값도 없고 정말 추상 메서드로 넘긴 동작만 수행해줌.

매우 쿨한 녀석임.



```java

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public <T> void printMessage(T printMsg, Consumer<T> consumer) {
        consumer.accept(printMsg);
    }

    @Test
    @DisplayName("Consumer 예제")
    void consumerExample (){
        printMessage(12345, (Integer msg) -> System.out.println("Integer Lamda.consumerExample"));
        printMessage("소비해버릴테다", (String msg) -> System.out.println("String Lamda.consumerExample"));
    }
```

위 예제는, 간단하게 파라미터로 넘긴 메시지를 프린트 문으로 출력하는 예제당

파라미터의 타입과 무관하게, 람다로 넘긴 동작을 성실히 수행해준 것을 확인할 수 있다.



3. Function

`java.util.function.Function<T, R>` 인터페이스는 T를 인수로 받아서 R 객체를 반환해주는 추상 메서드 apply를 정의함.

입력을 출력으로 맵핑하는 람다를 정의할 때 활용할 수 있다고 한다.

```java

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public <T, R> R applyMsg(T msg, Function<T, R> function) {
        return function.apply(msg);
    }

    @Test
    @DisplayName("Function 예제")
    void functionExample() {
        Integer result = applyMsg("안녕하세요~", (String msg) -> msg.length());
        assertEquals(6, result);
    }
```


바로 요렇게.

위 예제는 파라미터로 넘긴 문자열의 길이를 결과로 받는 예제이다.



위에서 본 Predicate, Consumer, Function<T, R> 함수형 인터페이스는 기본형 인터페이스다.

특화된 형식의 함수형 인터페이스도 있다고 한다.

기본형은 뭐고 참조형은 뭐냐면,

기본형 -> int, double, byte, char 등

참조형 -> Object, Integer, List 등

이다.

제네릭(T랑 R) 파라미터에는 참조형!!만 사용이 가능하다. (내부 구현 때문에 그렇다고 함)

물론 자바에서는 기본형->참조형으로 변환하는 기능을 제공함.

```java
    @Test
    @DisplayName("참조형을 기본형으로 언박싱")
    void unboxTest() {
        Integer integer = 100;
        int intValue = integer;
        assertEquals(100, intValue);
    }

    @Test
    @DisplayName("기본형을 참조형으로 박싱")
    void boxTest() {
        int intValue = 100;
        Integer integer = intValue;
        assertEquals(100, integer);
    }

```

너무 편한 기능이다. 오토박싱이라고도 한다.

근데 프로그래머가 편한 것은 뭐다? 컴퓨터가 고생 중이다~ 고생 중은 뭐다? 메모리를 더 소비한다~

왜냐면, 박싱할 때 기본형을 감싸서 힙에 저장하는데(랩핑), 그래서 덩치가 커져서 메모리를 더 소비한다.

힙에 저장하게 되면, 다시 기본형을 가져올 때도 다시 한번 메모리를 탐색하는 과정이 필요함.

그래서, 이런 오토 박싱 동작을 피하기 위한 자바 8의 함수형 인터페이스가 있다.


```java

    @FunctionalInterface
    public interface IntPredicate {
        boolean test(int t);
    }

    @Test
    @DisplayName("IntPredicate로 오토박싱 안하기")
    void intPredicateExample() {
        IntPredicate intPredicate = (int i) -> i+100 > 0;
        Predicate<Integer> integerPredicate = (Integer integer) -> integer+100 > 0;

        assertEquals(true, intPredicate.test(100));
        assertEquals(true, integerPredicate.test(100));
    }
```

이렇게 사용할 수 있음.

제네릭 타입을 사용하지 않고 특정 형식을 받는 함수형 인터페이스인 것 ㄷㄷ

IntPredicate 말고도 DoublePrediacte, IntConsumer, IntFunction 등의 형식으로 사용 가능하다.

두 개의 제네릭을 사용하는 Function<T, R>은 ToIntFunction<T> 같은 형식으로도 쓴다고 함.

다른 함수형 인터페이스는 다시 공부해야겠당.

### 예외,람다, 함수형 인터페이스

함수형 인터페이스는 예외 동작은 처리하지 않는다. 

그러면 예외를 어떻게 처리하느냐?

```java

    @FunctionalInterface
    public interface bufferReader {
        String process(BufferedReader bufferedReader) throws IOException;
    }

    @Test
    @DisplayName("함수형 인터페이스 예외 처리 하기")
    void exceptionFunctionInterface () {
        Function<BufferedReader, String> function = (BufferedReader bufReader) -> {
            try {
                return bufReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
```

try-catch 블록으로 감싸서 해주면 됨.


## 4. 형식 추론


예제로 형식 추론을 해보자. 두둥

아래와 같은 Comparator 객체 만드는 코드가 있다.


```java
Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

```
이를 함수 디스크립터, 시그니처, 콘텍스트로 설명할 수 있츰.

## 1. 콘텍스트:

콘텍스트는 주변 환경에서 람다가 실행되는 문맥이다.

여기서의 람다 표현식은 `Comparator<Apple>`를 정의하고 있다.

바디에는 `compare` 메서드의 로직이 보인다.

이 람다 표현식은 `Apple` 객체의 무게를 기준으로 비교하는구나~ 라는 것을 알 수 있다.

콘텍스트로 람다의 파라미터 형식을 알 수 있음. 그러면 함수 디스크립터를 정의할 수 있음!!


### 2. 함수 디스크립터:


함수 디스크립터는 `(Apple, Apple) -> int`임. 

이는 `Comparator`의 `compare` 메서드겠구나~ 를 알 수 있음. 

`compare` 메서드는 두 개의 `Apple` 객체를 비교하고 정수를 반환할 것 또한 예상 가능함.

콘텍스트로 함수 디스크립터를 알 수 있고, 컴파일러는 함수 디스크립터로 시그니처 추론이 가능해짐.

그래서 문법 생략이 가능해짐.


### 3. 시그니처:


람다 표현식의 시그니처는 `(a1, a2)`로 시작하고, 오른쪽의 `a1.getWeight().compareTo(a2.getWeight())`는 메서드 본문이다.


여기서 `(a1, a2)`는 매개변수 목록임을 알 수 있다.

따라서 전체 람다 표현식의 시그니처는 `(a1, a2) ->`가 되시겠다.


### 깔쌈 요약

- 주어진 코드는 `Comparator<Apple>`를 정의한다.
- 함수 디스크립터는 `(Apple, Apple) -> int`
- 람다 표현식의 시그니처는 `(a1, a2) ->`
- 콘텍스트는 `compare`로 메서드의 로직을 제공하는 `Comparator` 인터페이스의 구현이다.


## 5. 지역 변수 사용

람다 표현식에서는 자유변수도 사용 가능하다.

단어가 좀 어려운데; 그냥 `int a=1`라고 쓰면 a가 자유 변수임. 

람다에서 자유 변수를 사용하면 람다 캡쳐링이라고 함.

```java

    @Test
    @DisplayName("람다 캡쳐링")
    void lamdaCapture () {
        int number = 12345;

        Runnable runnable = () -> System.out.println(number);
    }
```

근데, 람다에서 지역 변수를 사용할 때는 주의할 점이 있다.

지역 변수를 final로 선언하거나 final로 취급해야한다.

```java
    @Test
    @DisplayName("람다 지역변수는 final 처럼 취급 되어야 한다.")
    void localVarLamdaTest () {
        int a = 10;
        int b = 20;

        Runnable runnable = () -> System.out.println(a+b);

        System.out.println(a);
    }
```

java 8 이전에는 `final int a = 10;` 처럼 final로 선언을 해줘야했지만, java 8에서는 선언은 생략이 가능하다. (이를 effectively final 이라고 함)

위 예제는 a, b 지역 변수가 한번만 값이 변경되고 실질적으로 final로 동작하기 때문에 람다 문에서 오류가 발생하지 않는다.

하지만 값에 변경이 일어나면 사용할 수가 없습네다.

```java
 @Test
    @DisplayName("람다 지역변수는 final 처럼 취급 되어야 한다.")
    void localVarLamdaTest () {
        int a = 10;
        int b = 20;

        a = 30;

        Runnable runnable = () -> System.out.println(a+b); // Error!!

        System.out.println(a);
    }
```

에러가 발생한다.

왜 그럴까?

이유는, 람다 표현식이 클로저를 형성할 때 해당 변수의 값을 저장하기 때문이다.

(클로저: 코드 블록 안에서 자유롭게 접근할 수 있는 외부 범위의 변수. )

람다는 외부 변수의 값을 복사해서 사용한다. 값만 복사한다.

클로저는 자신이 정의된 범위(scope)에서 외부 변수에 접근할 수 있는데, 이를 위해 람다는 해당 변수의 값을 final 또는 effectively final로 만들어 복사한다.

복사를 통해 람다 표현식은 그 값을 고정된 상태로 유지하며 사용이 가능하람다~!

따라서 람다가 사용될 때의 변수 값과 람다가 실행될 때의 변수 값이 항상 같게 된다.

그럼 뭐가 좋으냐. 스레드 안전성이나 예측 가능한 동작이 보장됩니다.

다른 스레드에서 이 변수의 값을 변경 못하게 해서 예측 가능한 결과를 얻도록 보장하는 것임.

만약 람다가 변수의 참조가 아니라 값을 직접 변경한다면 문제가 발생할 수 있을 것임.. 

정리하자면, 람다 표현식이 스레드 안전성과 예측 가능한 동작을 보장하기 위해 이런 제약을 뒀고

final 또는 effectively final 변수를 사용함으로써 람다 표현식이 클로저로 동작할 때 변수의 값이 변하지 않아 예상치 못한 동작을 방지할 수 있다람다~


## 6. 메서드 참조

메서드 참조 방식으로 람다처럼 사용할 수 있다.

메서드 참조는 코드를 간결하게 만들고 가독성을 향상시키는 데 도움이 되는 Java 8의 기능 중 하나이다.

물론 이거도 하나의 메서드만 호출할 때 가능하다.

원래 우리가 아는 람다 표현식을 예시로 들어보면 다음과 같다.

```java
List<String> words = Arrays.asList("apple", "banana", "orange");

// 람다 표현식을 사용하여 문자열의 길이를 반환
List<Integer> lengths = words.stream()
                            .map(s -> s.length())
                            .collect(Collectors.toList());
```

이를 메서드 참조로 바꿔보면?

```java
List<String> words = Arrays.asList("apple", "banana", "orange");

// 메서드 참조를 사용하여 문자열의 길이를 반환
List<Integer> lengths = words.stream()
                            .map(String::length)
                            .collect(Collectors.toList());
```

짠-! `String::length` 코드가 바뀌었다.

나는 람다도 처음 사용할 때는 참 많이도 줄여뒀네. 라고 생각했는데, 메서드 참조를 보니, 자바 설계자들이 더 줄이고 싶어했구나... 싶었다.

메서드 참조가 중요한 이유는 크게 4가지다.

1. **가독성 향상:** 메서드 참조는 코드를 간결하게 만들어 가독성을 향상시킨다. 특히 람다 표현식이 메서드를 호출하는 간단한 경우에 해당합니다. 불필요한 람다 표현식을 줄이고, 코드의 의도를 더 명확하게 전달할 수 있음

2. **재사용성 증가:** 메서드 참조를 사용하면 기존의 메서드를 다른 컨텍스트에서 재사용할 수 있습니다. 코드를 작성할 때 특정 메서드를 가리키는 것으로, 해당 메서드의 로직을 변경하지 않고도 새로운 기능을 도입할 수 있습니다.
-> 이건, 람다 표현식도 그런거 아니야? 할 수 있다. 근데 메서드 참조는 람다를 가독성 있게 사용하려는거니까 넘어가주삼

3. **함수형 프로그래밍 지원:** 메서드 참조는 함수형 프로그래밍의 핵심 기능 중 하나입니다. 결국 람다의 좋은 점이 메서드 참조의 장점과 겹친다. ㅋ 메서드 참조는 이러한 함수형 스타일을 쉽게 표현할 수 있도록 도와준다.

4. **컴파일러 최적화:** 일부 경우(꼭 그런건 아님!!)에는 메서드 참조를 사용하면 컴파일러가 더 효율적인 코드를 생성할 수 있습니다. 이는 성능을 향상시키는 데 도움이 될 수 있다고 한다. 줄이 짧아져서 그렇다는 얘기도 있고...


무튼, 간결한 문법과 높은 가독성으로 인해 메서드 참조는 Java 8에서 도입된 함수형 프로그래밍 기능 중 하나로 많이 쓰인다고 한다.

중요한 것은 가독성이 좋아진다는거-!

### 1. 메서드 참조 만드는 방법

3가지 방법이 있음.

1. 정적 메서드 참조 (Static Method Reference)

클래스이름::정적메서드로 표현

예를 들어, Math 클래스의 정적 메서드인 abs를 참조하는 경우: Math::abs 같은~


2. 다양한 형식의 인스턴스 메서드 참조

참조변수::인스턴스메서드로 표현

예를 들어, 문자열의 length 메서드를 참조하는 경우: String::length

3. 기존 객체의 인스턴스 메서드 참조

객체참조::인스턴스메서드로 표현합니다.

예를 들어, 특정 객체의 메서드를 참조하는 경우: object::instanceMethod

3가지 방법으로 작성해보면 이렇게 쓸 수 있담.

```java
    class StringConcatenator {
        public void concatenate(String word) {
            System.out.print(word + " ");
        }
    }

	@Test
    @DisplayName("메서드 참조")
    void staticMethodTest() {
        // 1. 정적 메서드 참조
        List<String> singer = Arrays.asList("아이유", "레드벨벳", "QWER");
        singer.forEach(System.out::println);  // System.out이라는 클래스의 정적 메서드 println 참조

        // 2. 다양한 형식의 인스턴스 메서드 참조
        singer.sort(String::compareToIgnoreCase);  // String 클래스의 인스턴스 메서드 compareToIgnoreCase 참조

        // 3. 기존 객체의 인스턴스 메서드 참조
        StringConcatenator concatenator = new StringConcatenator();
        singer.forEach(concatenator::concatenate);  // StringConcatenator 객체의 인스턴스 메서드 concatenate 참조
    }
```

컴파일러는 람다 표현식 검사하던 방식과 비슷하게, 메서드 참조가 함수형 인터페이스와 호환되는지 확인한다.

### 2. 생성자 참조

내가 제일 신기하다고 생각한 부분..

위에서 봤던 함수형 인터페이스 기억나는감?

시그니처가 같은 함수형 인터페이스를 사용해서 생성자를 만들 수 있다.

```java
  @Test
    @DisplayName("생성자 참조")
    void constructorReference() {
        // 람다 표현식으로 Apple 객체 생성자 만들기
        Supplier<Apple> appleSupplier1 = () -> new Apple();

        // 생성자 참조로 이렇게 사용할 수 있다
        Supplier<Apple> appleSupplier2 = Apple::new;

        // Supplier의 get으로 객체 만들 수도 있다
        Apple apple = appleSupplier2.get();

        BiFunction<Color, Integer, Apple> biFunction = Apple::new;
        Apple apple1 = biFunction.apply(Color.RED, 20);
    }
```

인스턴스화하지 않고도 생성자로 객체를 만들 수 있다.

### 7. 람다, 메서드 참조 활용하기

1. sort 메서드에 정렬 전략 전달하기

sort 함수의 시그니처

```java
void sort(Comparator<? super E> c)
```

```java
    public class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight();
        }
    }

    @Test
    @DisplayName("sort 전략 전달하기")
    void sortTest() {
        List<Apple> appleList = new ArrayList<>();
        appleList.sort(new AppleComparator());
    }
```
sort 동작을 파라미터화한 예제이다. 이렇게 Comparator 객체로 전략을 전달하면 다양한 전략을 전달할 수 있다.

2. 익명 클래스 사용

만약 위 코드의 Comparator 클래스를 한 번만 사용한다면 클래스로 구현하는 것보다는 익명 클래스로 작성하면 간단하다.

```java
    @Test
    @DisplayName("익명 클래스로 구현하기")
    void anonymousClassTest() {
        List<Apple> appleList = new ArrayList<>();
        //appleList.sort(new AppleComparator());

        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight();
            }
        });
    }
```

3. 람다 표현식 사용하기

코드를 다이어트 시켜보자

Comparator 함수의 디스크립터는? `(T,T) -> int`

그러면 Apple Comparator 함수 디스크립터는? `(Apple, Apple) -> int`

```java
    @Test
    @DisplayName("람다로 구현하기")
    void lamdaTest() {
        List<Apple> appleList = new ArrayList<>();

        // 람다 표현식 사용하면 이렇게!
        appleList.sort((Apple o1, Apple o2) -> o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight());
        // 자바 컴파일러는 람다 표현식의 컨텍스트를 보고, 파라미터 형식을 추론할 수 있다.
        appleList.sort((o1, o2) -> o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight());
    }
```

더 가독성을 향상시키기 위해서 Comparator 객체의 comparing 메서드를 사용할 수 있다.

Comparator는 객체로 만드는 Function 함수를 인수로 받는 정적 메서드인 comparing을 포함하고 있다.


```java
static <T,U extends Comparable<? super U>>
Comparator<T>	comparing(Function<? super T,? extends U> keyExtractor)
```

타입 T에서 비교 가능한 정렬 키를 추출하는 함수를 받아, 해당 정렬 키로 객체 T를 비교하는 Comparator<T>를 반환해준다.

더 가독성을 향상시키기 위해서 Comparator 객체의 comparing 메서드를 사용할 수 있다.

Comparator는 객체로 만드는 Function 함수를 인수로 받는 정적 메서드인 comparing을 포함하고 있다.



static <T,U extends Comparable<? super U>>
Comparator<T> comparing(Function<? super T,? extends U> keyExtractor)

타입 T에서 비교 가능한 정렬 키를 추출하는 함수를 받아, 해당 정렬 키로 객체 T를 비교하는 Comparator<T>를 반환해준다.


```java
    @Test
    @DisplayName("람다로 구현하기")
    void lamda2Test() {
        List<Apple> appleList = new ArrayList<>();

        Comparator<Apple> comparator = Comparator.comparing((Apple apple)-> apple.getWeight());
        appleList.sort(Comparator.comparing(apple -> apple.getWeight()));
    }
```

4. 메서드 참조 사용하기

```java
    @Test
    @DisplayName("메서드 참조로 구현하기")
    void methodTest() {
        List<Apple> appleList = new ArrayList<>();
        appleList.sort(Comparator.comparing(Apple::getWeight));
    }
```

---

요약
- 람다 표현식은 코드를 간결하게 표현하기 위한 자바 8에 추가된 문법이다.
- 람다 표현식은 익명 함수의 한 종류이다. 이름은 없지만 파라미터 리스트/바디/반환 형식을 가진다.
- 함수형 인터페이스는 하나의 추상 메서드를 가지는 인터페이스다.
- 람다 표현식 전체가 함수형 인터페이스의 인스턴스가 된다.

