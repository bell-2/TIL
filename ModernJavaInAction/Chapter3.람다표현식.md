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



