package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect//aop일때 써야됨         ,SpringConfig 에 bean어노테이션을 등록해도 되고 component scan 해도됨
@Component
public class TimeTraceAop {


    @Around("execution(* hello.hellospring.service..*(..))")   //공통 관심 사항을 어디에 적용할지 타겟팅
    public Object execut(ProceedingJoinPoint joinPoint)throws Throwable{

        long start = System.currentTimeMillis();
        System.out.println("START:"+joinPoint.toString());
        try{
            return joinPoint.proceed();     //joinPoint.proceed = 다음 메소드로 진행
        }finally {
            long finish = System.currentTimeMillis();
            long time = finish-start;
            System.out.println("END:"+joinPoint.toString()+" "+ time + "ms");
        }

    }





}
