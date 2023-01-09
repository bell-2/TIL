# Java 설계 (SOLID)

## 객체 지향 4대 특성

- 캡슐화
- 상속
- 추상화
- 다형성

<br>

## SOLID

자기 자신 클래스 안의 응집도는 내부적으로 높이기

타 클래스 간 결합도는 낮추기

- SRP (Single Responsibility Principle): 단일 책임 원칙
- OCP (Open Closed Principle) : 개방 폐쇄 원칙
- LSP( Liskov Substitution Principle): 리스코프 치환 원칙
- ISP (Interface Segregation Principle): 인터페이스 분리 원칙
- DIP(Dependency Inversion Principle): 의존 역전 원칙

### 1) SRP  (Single Responsibility Principle)

클래스는 하나의 역할만 갖게 하기

클래스에 많은 역할과 책임을 주지 마라

### 2) OCP (Open Closed Principle)

클래스 내부에서는 확장을 열어둬야하지만 외부에 의한 변화에는 닫혀있기

확장하고 싶으면 인터페이스나 상위클래스를 중간에 둬서 하기

### 3) LSP( Liskov Substitution Principle)

인터페이스와 클래스 관계, 상위/하위 클래스 관계를 얼마나 논리적으로 설계 했는가

상속은 확장

하위 상위라고 해서 계층적인 구조가 아니다

하위 클래스가 상위 클래스 역할을 대신할 때 논리적으로 맞아 떨어져야 한다

### 4) ISP (Interface Segregation Principle)

관련된 메소드만 제공해라

인터페이스 최소 주의

상위 클래스는 풍성할수록, 인터페이스는 작을 수록 좋다는 개념이 있다고 함

### 5) DIP(Dependency Inversion Principle)

자신보다 변하기 쉬운 것에 의존하지 않도록, 추상화된 인터페이스나 상위클래를 둬서 변화에 영향 받지 않게 의존 방향을 두기