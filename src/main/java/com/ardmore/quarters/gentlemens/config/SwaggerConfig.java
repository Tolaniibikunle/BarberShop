package com.ardmore.quarters.gentlemens.config;

import java.util.Collections;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableConfigurationProperties(ApiProperties.class)
public class SwaggerConfig {

  private ApiProperties apiProperties;

  public SwaggerConfig(ApiProperties apiProperties) {
    this.apiProperties = apiProperties;
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .enable(apiProperties.isEnabled())
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Swaggerrize.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        apiProperties.getTitle(),
        apiProperties.getDescription(),
        apiProperties.getVersion(),
        apiProperties.getTermsOfServiceUrl(),
        null,
        apiProperties.getLicense(),
        apiProperties.getLicenseUrl(),
        Collections.emptyList());
  }
}
