# Optional 적용 패턴

## Optional 객체 만들기

1. 빈 Optional
```
Optional<Car> optCar = Optional.empty();
```

2. null 아닌 값으로 Optional 만들기
```
Optional<Car> optCar = Optional.of(car);
```

`Optional.of(car)`를 사용하여 `Optional` 객체를 생성할 때 `car` 변수가 null이면 NullPointerException이 발생할 수 있음

이는 `Optional.of()` 메서드는 null을 허용하지 않으며, null을 전달하면 예외가 발생하는 것임

대신에 `Optional.ofNullable(car)` 메서드를 사용하여 `car` 변수가 null인 경우에도 `Optional` 객체를 생성할 수 있습니다. 이렇게 하면 null일 때는 비어있는 `Optional` 객체가 반환됨

따라서 안전한 코드를 작성하기 위해서는 `Optional.of()` 대신 `Optional.ofNullable()`을 사용하여 `Optional` 객체를 생성하는 것이 좋습니다. 이렇게 하면 null일 경우 예외가 발생하지 않고 안전하게 처리할 수 있습니다. 

예시:

```java
Optional<Car> optCar = Optional.ofNullable(car);
```

여기서 `car`가 null이면 `optCar`는 비어있는 `Optional` 객체가 됩니다.

## 맵으로 Optional 값 추출하고 변환하기

객체 정보를 추출할 때는 Optional을 사용할 때가 많다

```
if( insurance != null ) {
	return insurance.getname();
}
```

이렇게 사용할 수 있도록, Optional은 map 메서드를 지원한다

```
Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
Optional<String> name = optInsurance.map(Insurance::getName);
```

Optional 객체를 최대 요소 개수가 한 개 이하인 데이터 컬렉션으로 생각할 수 있다

스트림의 map은 스트림의 각 요소에 제공된 함수를 적용하는 연산인데, 이와 비슷하다

Optional이 값을 갖고 있다면 map의 인수로 제공된 함수가 값을 바꿔준다

비어있다면 아무 일도 일어나지 않는다

## flatMap으로 Optional 객체 연결

```
Optional<Person> optPerson = Optional.of(person);
Optional<String> name = 
	optPerson.map(Person::getCar)
	.map(Car::getInsurance)
	.map(Insurance::getName);
```

위 코드는 컴파일 되지 않는다.

왜냐? `map()` 메서드를 연속적으로 호출하여 체인으로 연결한 후, 마지막 `map()` 메서드에서 반환된 값이 `Optional`일 수 있기 때문입니다. 


코드에서는 `Person` 객체에서부터 `Car` 객체를 얻어내고, 다시 `Car` 객체에서 `Insurance` 객체를 얻어내고, 마지막으로 `Insurance` 객체에서 이름을 얻어내려고 합니다. 

그러나 `map()` 메서드는 반환된 값이 `Optional`인 경우에만 동작합니다. 

즉, 중간에 어느 하나라도 값이 null이면 그 다음 `map()` 호출에서는 동작하지 않고 그대로 null을 반환합니다.

따라서 이 코드를 컴파일하려면, `Person` 객체, `Car` 객체, `Insurance` 객체, 그리고 `getName()` 메서드가 모두 null이 아닌 경우에만 이름을 얻어와야 합니다. 

만약 이 중 하나라도 null이라면 `map()` 메서드는 동작하지 않고 비어있는 `Optional`을 반환하게 되어 컴파일 에러가 발생합니다.

```
Optional<Person> optPerson = Optional.of(person);
```

optPerson의 형식은 Optional<Person> 이기 때문에, map 메서드를 호출할 수 있다.

하지만 `getCar`는 Optional<Car>를 또 반환한다.

즉 전체 map 연산의 결과는 Optional<Optional<Car>> 형식이 되는 것이다;;; 중첩이다.


따라서 이를 수정하려면 `map()` 메서드를 연속해서 호출하는 대신 `flatMap()` 메서드를 사용하여야 합니다. 

`flatMap()` 메서드는 `map()`과 비슷하지만, 중첩된 `Optional`을 풀어서 하나의 `Optional`로 만들어줍니다.

수정된 코드:

```java
Optional<String> name = optPerson.flatMap(Person::getCar)
                                .flatMap(Car::getInsurance)
                                .map(Insurance::getName)
								 .orElse("Unknown");
```

이렇게 수정하면 컴파일 에러가 발생하지 않고, `Person` 객체, `Car` 객체, `Insurance` 객체가 모두 null이 아닌 경우에만 이름을 얻어올 수 있습니다.

이차원 Optional을 일차원의 Optional로 평준화해준 것이다.