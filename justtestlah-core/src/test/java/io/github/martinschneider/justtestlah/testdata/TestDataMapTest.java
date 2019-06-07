package io.github.martinschneider.justtestlah.testdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

public class TestDataMapTest {
  private static TestDataMap target = new TestDataMap();

  @BeforeClass
  public static void setUp() {
    TestDataObjectRegistry registry = new TestDataObjectRegistry();
    target.setRegistry(registry);
    TestDataParser parser = new TestDataParser();
    parser.setYamlParser(new Yaml());
    parser.setTestDataObjectRegistry(registry);
    target.setParser(parser);
    target.setFilter("io/github/martinschneider/justtestlah/testdata/valid");
  }

  @Test
  public void testWithModelPackage() throws IOException {
    target.setModelPackage(TestEntity1.class.getPackageName());
    target.initializeTestDataMap();
    Object result = target.get(TestEntity1.class, "test-123");
    assertThat(result.getClass()).isEqualTo(TestEntity1.class);
    assertThat(((TestEntity1) result).getValue()).isEqualTo("test");
  }

  @Test
  public void testWithoutModelPackage() throws IOException {
    target.setModelPackage(null);
    target.initializeTestDataMap();
    Object result = target.get(TestEntity1.class, "test-123");
    assertThat(result.getClass()).isEqualTo(TestEntity1.class);
    assertThat(((TestEntity1) result).getValue()).isEqualTo("test");
  }

  @Test
  public void testWithEmptyModelPackage() throws IOException {
    target.setModelPackage("");
    target.initializeTestDataMap();
    Object result = target.get(TestEntity1.class, "test-123");
    assertThat(result.getClass()).isEqualTo(TestEntity1.class);
    assertThat(((TestEntity1) result).getValue()).isEqualTo("test");
  }

  @Test
  public void testWithInvalidModelPackage() throws IOException {
    target.setModelPackage("${model.package}");
    target.initializeTestDataMap();
    Object result = target.get(TestEntity1.class, "test-123");
    assertThat(result.getClass()).isEqualTo(TestEntity1.class);
    assertThat(((TestEntity1) result).getValue()).isEqualTo("test");
  }
}