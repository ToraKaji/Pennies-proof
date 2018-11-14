package deepdive.cnm.edu.penniesv10.stetho;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class PenniesApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(getApplicationContext());
  }
}
