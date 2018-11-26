package ml.exercise.mutants.service.mutant.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MutantInterceptorConfig {

    @Autowired
    private MutantInterceptor interceptor;

    @Bean
    public Advisor isMutantInterceptor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public boolean ml.exercise.mutants.service.mutant.MutantService.isMutant(char[][], int))");
        return new DefaultPointcutAdvisor(pointcut, interceptor);
    }

}
