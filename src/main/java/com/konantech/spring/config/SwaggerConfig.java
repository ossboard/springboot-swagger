package com.konantech.spring.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.google.common.collect.Lists.newArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host("nps.konantech.com")
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.konantech.spring.controller.rest"))
                .paths(Predicates.not(PathSelectors.regex("/docs")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 Message!")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("403 Forbidden!")
                                        .build())
                );
    }

    private ApiInfo apiInfo() {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("/MANIFEST-INFO.MF");
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (Exception ignore) {}
        String scm = String.format("Build-Time : %s", prop.getProperty("Build-Time"));
        List<VendorExtension> vendorExtensions = new ArrayList<>();
        vendorExtensions.add(new StringVendorExtension("SCM", scm));
        return new ApiInfo(
                "REST API",
                "API 정보",
                "1.1",
                "#",
                new Contact(scm, "http://www.konantech.com", ""),
                "license : 코난테크놀로지", "http://www.konantech.com", vendorExtensions);

    }

}
