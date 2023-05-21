package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}

//FilterType 옵션
//FilterType은 5가지 옵션이 있다.
//ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
//ex) org.example.SomeAnnotation
//ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
//ex) org.example.SomeClass
//ASPECTJ: AspectJ 패턴 사용
//ex) org.example..*Service+
//REGEX: 정규 표현식
//ex) org\.example\.Default.*
//CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
//ex) org.example.MyTypeFilter
