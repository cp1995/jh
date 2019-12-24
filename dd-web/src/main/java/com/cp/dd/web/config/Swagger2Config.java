package com.cp.dd.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 *
 * @author chengp
 */
@EnableSwagger2
@Configuration
@EnableWebMvc
@ComponentScan("com.eshore.zswh.web.controller")
public class Swagger2Config {

    @Value("${swagger.enable}")
    private Boolean swaggerEnable;

    /**
     * 创建API应用
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return Docket
     */
    @Bean
    public Docket memberApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("会员相关接口", "会员相关接口"))
                .groupName("会员相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cp.dd.web.controller.member"))
                .paths(PathSelectors.any())
                .build();
    }



    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo(String title, String desc) {
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                .termsOfServiceUrl("http://localhost:8081/")
                .contact(new Contact("zswh", "", "developer@mail.com"))
                .version("1.0")
                .build();
    }
}