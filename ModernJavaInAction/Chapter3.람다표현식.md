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

람다 표현식 구성: 파라미터, 화살표, 바디로 이뤄짐.

```
	(o1, o2) -> o1.getWeight()> o2.getWeight() ?o1.getWeight():o2.getWeight();
```


- (o1, o2) : 람다 파라미터. 파라미터 리스트. Comparator의 compare 메서드 파라미터
- -> : 화살표는 왼쪽좌는 파라미터 리스트, 오른좌는 바디를 구분함. 구분좌임.
- 오른쪽: 람다 바디. 반환 값에 해당하는 표현식.


return 구문이 사라져있다. 그렇다. 람다 표현식에서는 return을 사용하지 않아도 된다.


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

