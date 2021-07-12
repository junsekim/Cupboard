# Cupboard
Spring Framework 의 DI 와 유사한 기능을 제공하는 것을 목표로 하고 있습니다.  

# Simple Usage

- 빈 소스 생성
```java
ClassScanner scanner = new AnnotatedClassScanner(Arrays.asList("integeration.components"), Component.class);
BeanReader reader = new ComponentScanner(scanner, new SimpleBeanFactory());
BeanSource source = new SimpleBeanSource(reader);
```

AnnotatedClassScanner - 패키지 경로 리스트와 어노테이션 클래스 타입을 받아 주어진 패키지 내에 주어진 어노테이션이 달린 클래스들을  
                        긁어옵니다.     
ComponentScanner - 클래스 스캐너가 가져온 클래스들을 이용해 빈들을 생성한 후 목록을 반환합니다.     
SimpleBeanFactory - AnnotatedBeanFactory 의 구현 클래스입니다. 클래스, 메서드에 달린 어노테이션 목록을 이용해 적절한 빈 객체를 생성합니다.    
SimpleBeanSource - BeanReader 을 이용해 읽어온 빈들을 관리하고, 빈 요청에 적절히 객체를 반환하는 역할을 수행합니다.
</br></br>
- 빈 요청

```java
TestComponent bean = source.requestBean(TestComponent.class);
TestComponent namedBean = source.requestBean(TestComponent.class, "SomeBeanName");
```
객체 타입과 빈 생성시에 제공하는 이름을 통해 객체를 요청할 수 있습니다.  
적절한 객체를 찾지 못했을 경우 BeanNotFoundException 이 던져집니다.

# Warning

현재 ComponentScanner 를 통해 등록되는 모든 빈은 SingletonPolicy 를 사용합니다.  
즉, 빈 객체가 처음 요청될 때 한번 생성되고 요청시마다 모두 같은 객체를 반환합니다.
