package fun.xiaorang.microservice.common.web.validation;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/20 22:17
 */
@Configuration
@AutoConfigureBefore(value = {ValidationAutoConfiguration.class})
public class ValidatorConfig {
    @Bean
    public Validator validator(AutowireCapableBeanFactory beanFactory) {
        try (ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(beanFactory))
                .buildValidatorFactory()) {
            return validatorFactory.getValidator();
        }
    }
}
