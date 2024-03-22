# Optional Class

Optional은 선택형 값을 캡슐화하는 클래스다.

값이 존재할 수도 있고 존재하지 않을 수도 있는 상황에서, 값이 null이 될 수 있는 경우에 null 체크를 명시적으로 처리하는 데 도움을 준다.

값이 있으면 Optional 클래스는 값을 감싼다.

값이 없으면 Optional.empty 메서드로 Optional를 반환한다. 

### Optional.empty

Optional의 특별한 싱글턴 인스턴스를 반환하는 정적 팩토리 메서드.

## null 참조와 Optional.empty()의 차이

null 참조와 `Optional.empty()`의 차이는 다음과 같습니다:

1. **값의 존재 여부**:
	- null 참조: null은 값이 존재하지 않음을 나타냄. null을 사용하면 값이 없음을 나타내지만, 이에 대한 추가적인 정보나 처리 방법을 제공하지 않음
   - `Optional.empty()`: `Optional.empty()`는 값이 비어 있음을 명시적으로 나타내는 데 사용됨

2. **Null Pointer Exception 발생 여부**:
   - null 참조: null을 사용할 경우 NullPointerException이 발생할 수 있음. null을 사용하는 경우에는 이를 다루는 추가적인 null 체크가 필요함
   - `Optional.empty()`: `Optional.empty()`는 명시적으로 값이 비어 있음을 나타내기 때문에 NullPointerException이 발생할 가능성이 없음. 이를 사용할 때는 값이 존재하는지 여부를 확인하는 메서드인 `isPresent()`를 사용해야 함 (ㅜㅜ)

3. **API 설계 측면**:
   - null 참조: 메서드의 반환 값이 null일 수 있는 경우, 개발자는 이를 API 문서에서 명확히 설명해야 합니다. 또한 null 처리에 대한 책임은 개발자에게 있음
   - `Optional.empty()`: `Optional`을 사용하면 메서드의 반환 값이 `Optional`로 명시되므로 API 사용자는 값의 존재 여부를 명확히 인식할 수있음. 또한 `Optional`은 값을 다루는 다양한 메서드를 제공하여 null 처리를 더 간편하게 할 수 있습니다.

요약하자면, null 참조와 `Optional.empty()`는 값이 없음을 나타내는 데 사용되지만, `Optional.empty()`는 명시적으로 값이 비어 있음을 표현하고 다루기 쉽도록 하는 데에 더 나은 방법을 제공합니다


예시를 봐보자

```
public class Person {
    public Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

```

Car는 null일 수가 있다고 가정해보자. 이를 Optional로 감싸면 이렇게 할 수 있다

```
import java.util.Optional;

public class Person {
    public Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}

```

이를 사용하게 되면, Person에게는 Car가 없을 수도 있구나를 알 수 있다.


```
        Optional<Car> car = person.getCar();
        if (car.isPresent()) {
            return "Unknown";
        }

```

이렇게 확인 할 수 있다.

Optional을 사용하면 더 명확해지므로, 값이 없는 상황이 데이터에 문제가 있는 것인지, 아니면 버그인지 구분이 가능하다

그치만..! 모든 null 참조를 대치하라는 것은 아니다
