package com.ardmore.quarters.gentlemens.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import com.ardmore.quarters.gentlemens.config.swagger.ApiProperties;
import org.junit.Before;
import org.junit.Test;

public class ApiPropertiesTest {

  private ApiProperties apiProperties;

  @Before
  public void init() {
    apiProperties = new ApiProperties();
    initObject();
  }

  @Test
  public void testSetAndGet() {
    assertThat(apiProperties.getTitle()).isEqualTo("test");
    assertThat(apiProperties.getDescription()).isEqualTo("test");
    assertThat(apiProperties.getVersion()).isEqualTo("test");
    assertThat(apiProperties.getTermsOfServiceUrl()).isEqualTo("test");
    assertThat(apiProperties.getLicense()).isEqualTo("test");
    assertThat(apiProperties.getLicenseUrl()).isEqualTo("test");
    assertTrue(apiProperties.isEnabled());
  }

  private void initObject() {
    apiProperties.setTitle("test");
    apiProperties.setDescription("test");
    apiProperties.setVersion("test");
    apiProperties.setTermsOfServiceUrl("test");
    apiProperties.setLicense("test");
    apiProperties.setLicenseUrl("test");
    apiProperties.setEnabled(true);
  }

}
