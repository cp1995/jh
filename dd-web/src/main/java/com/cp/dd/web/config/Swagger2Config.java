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
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.member"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket actApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("活动相关接口", "活动相关接口"))
                .groupName("活动相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.act"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket sysApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("系统接口", "系统相关接口"))
                .groupName("系统相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.sys"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket upApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("文件上传接口", "文件上传接口"))
                .groupName("文件上传接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.util"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket topicApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("话题相关接口", "话题相关接口"))
                .groupName("话题相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.topic"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket videoicApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("视频相关接口", "视频相关接口"))
                .groupName("视频相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.video"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket venueApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("文化设施相关接口", "文化设施相关接口"))
                .groupName("文化设施相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.venue"))
                .paths(PathSelectors.any())
                .build();
    }
    
    @Bean
    public Docket clubApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("文化社团相关接口", "文化社团相关接口"))
                .groupName("文化社团相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.club"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket solrApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("搜索相关接口", "搜索相关接口"))
                .groupName("搜索相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.solr"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket tyomgApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("你点我送相关接口", "你点我送相关接口"))
                .groupName("你点我送相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.tyomg"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket cultureaskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("文化资讯相关接口", "文化资讯相关接口"))
                .groupName("文化资讯相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.tcultureask"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket texhApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("艺术展览相关接口", "艺术展览相关接口"))
                .groupName("艺术展览相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.texh"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket tourApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("文化旅游相关接口", "文化旅游相关接口"))
                .groupName("文化旅游相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.tour"))
                .paths(PathSelectors.any())
                .build();
    }
    
    @Bean
    public Docket recomendApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("内容优选相关接口", "内容优选相关接口"))
                .groupName("内容优选相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.recomend"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket evaluateApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo("评论接口", "评论接口"))
                .groupName("评论接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshore.zswh.web.controller.evaluate"))
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