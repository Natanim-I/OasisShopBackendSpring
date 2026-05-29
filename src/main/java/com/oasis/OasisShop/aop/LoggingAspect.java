package com.oasis.OasisShop.aop;

import com.oasis.OasisShop.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger("APP_LOGGER");
    private static final Logger ERROR_LOGGER = LoggerFactory.getLogger("ERROR_LOGGER");

    @AfterReturning(value = "execution(* com.oasis.OasisShop.service.ProductService.addProduct(..))", returning = "result")
    public void lodProductAdd(JoinPoint jp, Object result){
        Product product = (Product) result;
        LOGGER.info("Product added: {Product Id: " + product.getId() + ", Product Name: " + product.getName() + "}");
    }

    @AfterReturning(value = "execution(* com.oasis.OasisShop.service.ProductService.updateProduct(..))", returning = "result")
    public void logProductUpdate(JoinPoint jp, Object result){
        Product product = (Product) result;
        LOGGER.info("Product updated: {Product Id: " + product.getId() + ", Product Name: " + product.getName() + "}");
    }

    @AfterReturning(value = "execution(* com.oasis.OasisShop.service.ProductService.deleteProduct(..))")
    public void logDeleteProduct(JoinPoint jp){
        Product product = (Product) jp.getArgs()[0];
        LOGGER.info("Product deleted: {Product Id: " + product.getId() + "}");
    }

    @AfterThrowing(value = "execution(* com.oasis.OasisShop.service.ProductService.*(..))", throwing = "ex")
    public void logError(JoinPoint jp, Object ex){
        ERROR_LOGGER.error("Error in method: " + jp.getSignature().getName() + " - " + ex.toString());
    }

}
