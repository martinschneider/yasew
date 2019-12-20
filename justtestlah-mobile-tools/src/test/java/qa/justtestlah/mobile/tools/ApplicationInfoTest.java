package qa.justtestlah.mobile.tools;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationInfoTest {

  @Test
  public void testToString() {
    ApplicationInfo target = new ApplicationInfo(null, null, null);
    target.setApplicationName("appName");
    target.setVersionCode("versionCode");
    target.setVersionName("versionName");
    assertThat(target.toString()).isEqualTo("appName versionName_versionCode");
  }
}