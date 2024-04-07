# 새로운 날짜와 시간 API

자바 API는 유용한 컴포넌트를 제공하는데, 자바 8이전에는 개발자가 만족하는 날짜와 시간 관련 기능이 없었다.

자바 8에서는 날짜와 시간 문제를 개선하는 새로운 API를 제공한다.

## 자바 8 이전의 `Date` 및 `Calendar` 클래스의 문제점

1. **가변(Mutable) 객체**: 
   - `java.util.Date` 클래스는 가변(mutable)하며, 변경할 수 있음
   - 이는 여러 스레드에서 동시에 접근할 때 문제를 일으킬 수 있음

2. **불변(Immutable) 객체의 부재**: 
   - `Date` 클래스는 불변(immutable) 객체가 아님
   - 불변 객체는 상태 변경이 불가능하므로 스레드 안전성을 보장하고 코드를 예측 가능하게 만들 수 있음

3. **날짜와 시간 처리의 복잡성**: 
   - `Date` 클래스는 밀리초 단위의 Unix 타임스탬프를 기반으로 해서 직관적이지 못함
   - `Calendar` 클래스는 날짜 및 시간 처리를 위해 많은 복잡한 메서드를 제공함

4. **월 인덱스의 문제**: 
   - `Calendar` 클래스에서 월을 나타내는 상수는 0부터 시작함 (0은 1월을 의미)
   - 이는 실수를 유발할 수 있고 코드의 가독성을 떨어트림

5. **API의 일관성 부족**: 
   - `Date`와 `Calendar` 클래스는 API가 일관성이 없음
   - ex) `Date`는 날짜와 시간을 동시에 표현하지만, `Calendar`는 이를 분리하여 처리함

6. **Java.util.Date의 한계**: 
   - `java.util.Date` 클래스는 정확성, 유지보수성, 기능성 측면에서 한계가 있음
   - ex) `Date` 클래스는 초 단위의 정밀도만 제공하므로, 밀리초 미만의 정밀도가 필요한 애플리케이션에는 적합하지 않음

Java 8에서는 이러한 문제점을 해결하기 위해 `java.time` 패키지가 도입되었다.

`java.time` 패키지는 새로운 날짜와 시간 API를 제공하여 이전의 문제점들을 극복하고 더 쉽고 안전하게 날짜와 시간을 다룰 수 있게 해준다.

`LocalDate`, `LocalTime`, `LocalDateTime` 등의 클래스가 추가되었고, 이러한 클래스들은 불변 객체이며 스레드 안전성을 보장한다.

따라서 자바 8 이상에서는 `java.time` 패키지를 사용하여 날짜와 시간을 처리하는 것이 권장된다.

## LocalDate와 LocalTime 사용

LocalDate 인스턴스는 시간을 제외한 날짜를 표현하는 불변 객체이다. 어떤 시간대 정보도 포함하지 않는다.

연도, 달, 요일 등을 반환하는 메서드를 제공한다.

```java
LocalDate date = LocalDate.of(2024, 04, 07);
int year = date.getYear();
Month month = date.getMonth();
int day = date.getDayOfMonth();
DayOfWeek dow = date.getDayOfWeek();
int len = date.lengthOfMonth();
boolean leap = date.isLeapYear();
```

팩토리 메서드 `now`는 시스템 시계의 정보를 이용해서 현재 날짜 정보를 얻는다.

```
LocalDate today = LocalDate.now();
```

get 메서드에 TemporalField를 전달해서 LocalDate 값을 읽을 수도 있다.

```
int year = date.get(ChronoField.YEAR);
int month = date.get(ChronoField.MONTH_OF_YEAR);
int day = date.get(ChronoField.DAY_OF_MONTH);
```

내장 메서드를 이용해서 가독성을 높일 수도 있다.

```
int year = date.getYear();
int month = date.getMonthValue();
int day = date.getDayOfMonth();
```

시간 정보는 LocalTime 클래스로 표현할 수 있다.

```
LocalTime time = LocalTime.of(13, 45, 20);
int hour = time.getHour(); // 13
int minute = time.getMinute(); // 45
int second = time.getSecond(); // 20
```

날짜와 시간 문자열로 LocalDate, LocalTime 인스턴스를 만들 수도 있다.

데이터베이스에 저장된 시간 정보를 읽거나, 클라이언트에게 받은 시간 정보의 유효성을 판단할 때 많이  사용하고 있다.

```
LocalDate date = LocalDate.parse("2024-04-07");
LocalTime time = LocalTime.parse("11:28:13");
```

parse 메서드에 DateTimeFormatter를 전달할 수도 있다.

## LocalDateTime

LocalDateTime은 LocalDate와 LocalTime을 갖는 짬뽕 클래스다. 

단어 그대로 날짜와 시간을 모두 표현할 수 있다.

```
LocalDateTime dt1 = LocalDateTime.of(2024, Month.APRIL, 11, 13, 45, 20);
LocalDateTime dt2 = LocalDateTime.of(date, time);
LocalDate date1 = dt1.toLocalDate();
LocalTime time1 = dt1.toLocalTime();
```

## Instant 클래스: 기계의 날짜와 시간

사람은 몇월, 며칠, 시간, 분으로 날짜를 계산하지만, 기계는 연속된 시간에서 특정 지점을 하나의 큰 수로 표현하는 것이 자연스럽다.

새로운 `java.time.Instant` 클래스에서는 이와 같은 기계적인 관점에서 시간을 표현한다.

팩토리 메서드 `ofEpochSecond`에 초를 넘겨줘서 Instant 클래스 인스턴스를 만들 수 있다.

나노초 (10억분의 1초)의 정밀도를 제공한다.

```
Instant.ofEpochSecond(3);
Instant.ofEpochSecond(3, 0); // 두번째 인수에서 값을 지정할 수 있다
Instant.ofEpochSecond(2, 1_000_000_000); // 2초 이후의 1억 나노초 (1초)
```
