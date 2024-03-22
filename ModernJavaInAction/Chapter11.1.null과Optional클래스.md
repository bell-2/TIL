# null 대신 Optional 클래스

## NullPointerException

NullPointerException(널 포인터 예외)은 프로그래밍 언어에서 매우 흔한 오류 유형 중 하나입니다. 

주로 자바와 같은 객체 지향 프로그래밍 언어에서 발생하며, 코드에서 null 값을 가진 객체에 대해 메서드나 속성을 호출하려고 할 때 발생합니다. 

이러한 상황에서는 해당 객체가 실제로 메모리에 존재하지 않기 때문에 오류가 발생합니다.

아래의 코드에서 `object` 변수가 null로 초기화되어 있습니다.

```java
String object = null;
```

그런 다음 다음과 같은 메서드 호출이 있습니다.

```java
int length = object.length();
```

이 경우 `NullPointerException`이 발생합니다. 왜냐하면 `object`가 null이기 때문에 `length()` 메서드를 호출할 수 없기 때문입니다.

이 문제를 해결하기 위해서는 호출하기 전에 해당 객체가 null이 아닌지 확인해야 합니다.

```java
if (object != null) {
    int length = object.length();
}
```

이렇게 하면 null 상태에서 객체의 메서드를 호출하는 오류를 방지할 수 있습니다. 

NullPointerException은 디버깅하기 어렵고 예기치 못한 시간에 발생할 수 있으므로, 코드를 작성할 때 null 상태를 주의 깊게 처리하는 것이 중요합니다.

## Null 등장

1965년 영국 컴퓨터과학자 토니호어씨는 처음 null 참조를 등장시켰습니다

그 당시에는 null 참조 및 예외로 값이 없는 상황을 가장 단순하게 구현할 수 있다고 생각했고 그래서 null이랑 예외가 탄생했다는...

여러 해가 지나고 null과 예외를 만든게 십억 달러짜리 실수라고 했다고 합니다. 그러게요 ㅠㅠ 왜 그러신거죠.

c, c++로 개발할 때 메모리를 잘못 참조하고 무수한 core 파일을 만드는 것은 일상이었어서 

사실 null 참조를 나는 편하게 쓰는 편이다.

물론 클린코드나 여러 의견에서는 null을 반환하는 것이 좋지 않다고 말을 하지만.. 

```
if( a == null )
```

오히려 더 직관적이지 않나.. 생각이 들기도 한다.

## NullPointerException 줄이기

책에 아래와 같은 예제가 있다

```
public String getCarInsuranceName(Person person) {
        if(person != null) {
            Car car = person.getCar();
            if( car != null) {
                Insurance insurance = car.getInsurance();;
                if( insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }
```

null 확인 코드 때문에 나머지 호출 체인의 들여쓰기가 레벨이 증가한걸 설명한 예제이다.

이를 가독성을 높이고, 구조를 변경하면 아래와 같이 쓸 수 있다.

```
    public String getCarInsuranceName(Person person) {
        if (person == null) {
            return "Unknown";
        }

        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }
}
```

이렇게 쓰면 사실 뭐가 문제인지 아직 와닿지는 않는다.

문제는 null 확인 코드마다 return을 하기 때문에, 출구가 많아진다는 것이다.

그러면 유지보수를 하거나 예외 상황이 더 늘어난다.

## null 때문에 발생하는 문제

1. 에러의 근원이다
2. null 확인 코드로 인해 코드 가독성이 떨어진다
3. null은 아무 의미도 표현하지 않는다.
4. 자바는 개발자로부터 포인터를 모두 숨겼다. 하지만 null 포인터는 자바 철학에 위배된다.
5. 모든 객체가 null 이라면 형식 시스템에 구멍을 만들 수 있다. 

## 다른 언어의 null 사용 방법

Groovy 언어는 자바 플랫폼을 기본으로 하는 스크립팅, 웹 애플리케이션 개발, 자동화 및 테스트 작성 등 다양한 용도로 사용되는 언어다.

```
	def carInsuranceName = person?.car?.insurance?.name
```

이 언어는 안전 내비케이션 연산자 `?.`를 사용해서 null 문제를 해결했다

그럼 자바 8은?

java.util.Optional<T>를 라는 클래스를 사용해서 처리할 수 있다.