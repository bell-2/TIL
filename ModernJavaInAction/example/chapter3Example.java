import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static java.awt.Color.GREEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Lamda {

    public enum Color {RED, GREEN}

    @Test
    @DisplayName("Comparator 람다로 변환")
    void comparatorTest() {
        Comparator<Apple> appleComparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight();
            }
        };

        Comparator<Apple> lamdaComparator = (o1, o2) -> o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight();
    }

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
        process(() -> {
            System.out.println("Lamda.functionalInterface 직접 람다로 전달");
        });
    }

    public String processFile() throws Exception {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))) {
            return bufferedReader.readLine();
        }
    }

    public String processFile2(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    @FunctionalInterface
    public interface BufferReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException;
    }

    public static String processFile(BufferReaderProcessor processor) {
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            return processor.process(br);
        } catch (IOException e) {
            // 예외 처리 로직을 추가할 수 있음
            e.printStackTrace();
            return null; // 또는 예외에 대한 다른 처리 방식
        }
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

        String twoLineResult = processFile((BufferedReader bufferReader) -> bufferReader.readLine() + bufferReader.readLine());
    }

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
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

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public <T> void printMessage(T printMsg, Consumer<T> consumer) {
        consumer.accept(printMsg);
    }

    @Test
    @DisplayName("Consumer 예제")
    void consumerExample() {
        printMessage(12345, (Integer msg) -> System.out.println("Integer Lamda.consumerExample"));
        printMessage("소비해버릴테다", (String msg) -> System.out.println("String Lamda.consumerExample"));
    }

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

    @FunctionalInterface
    public interface IntPredicate {
        boolean test(int t);
    }

    @Test
    @DisplayName("IntPredicate로 오토박싱 안하기")
    void intPredicateExample() {
        IntPredicate intPredicate = (int i) -> i + 100 > 0;
        Predicate<Integer> integerPredicate = (Integer integer) -> integer + 100 > 0;

        assertEquals(true, intPredicate.test(100));
        assertEquals(true, integerPredicate.test(100));
    }

    @FunctionalInterface
    public interface bufferReader {
        String process(BufferedReader bufferedReader) throws IOException;
    }

    @Test
    @DisplayName("함수형 인터페이스 예외 처리 하기")
    void exceptionFunctionInterface() {
        Function<BufferedReader, String> function = (BufferedReader bufReader) -> {
            try {
                return bufReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    @DisplayName("람다 캡쳐링")
    void lamdaCapture() {
        int number = 12345;

        Runnable runnable = () -> System.out.println(number);
    }

    @Test
    @DisplayName("람다 지역변수는 final 처럼 취급 되어야 한다.")
    void localVarLamdaTest() {
        int a = 10;
        int b = 20;

        a = 30;

        //Runnable runnable = () -> System.out.println(a + b);

        System.out.println(a);
    }


    @Test
    @DisplayName("람다는 함수형 인터페이스를 정의하기 위한 방법")
    void methodReferenceTest1() {
        // 람다 표현식에서 매개변수 타입 생략, 반환 타입을 int로 변경
        IntFunction<Integer> absFunction = number -> Math.abs(number);

        // 람다 사용 예시
        int inputNumber = -5;
        Integer result = absFunction.apply(inputNumber);
    }

    @Test
    @DisplayName("메서드 참조로 람다처럼 전달하기")
    void methodReferenceTest2() {
        // 메서드 참조로 바꿈
        IntFunction<Integer> absFunction = Math::abs;

        // 사용 예시
        int inputNumber = -5;
        Integer result = absFunction.apply(inputNumber);
    }

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

    @Test
    @DisplayName("람다로 구현하기")
    void lamdaTest() {
        List<Apple> appleList = new ArrayList<>();

        // 람다 표현식 사용하면 이렇게!
        appleList.sort((Apple o1, Apple o2) -> o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight());
        // 자바 컴파일러는 람다 표현식의 컨텍스트를 보고, 파라미터 형식을 추론할 수 있다.
        appleList.sort((o1, o2) -> o1.getWeight() > o2.getWeight() ? o1.getWeight() : o2.getWeight());
    }

    @Test
    @DisplayName("람다로 구현하기")
    void lamda2Test() {
        List<Apple> appleList = new ArrayList<>();

        Comparator<Apple> comparator = Comparator.comparing((Apple apple)-> apple.getWeight());
        appleList.sort(Comparator.comparing(apple -> apple.getWeight()));
    }

    @Test
    @DisplayName("메서드 참조로 구현하기")
    void methodTest() {
        List<Apple> appleList = new ArrayList<>();
        appleList.sort(Comparator.comparing(Apple::getWeight));
    }



    public class Apple {
        private Color color;
        private int weight;

        Apple() {

        }

        Apple(Color color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public Color getColor() {
            return this.color;
        }

        public int getWeight() {
            return this.weight;
        }
    }
}

