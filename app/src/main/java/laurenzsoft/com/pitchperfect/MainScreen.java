package laurenzsoft.com.pitchperfect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {
    public final static int NODE_STATE = 1;
    public final static int CHORD_STATE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
    public void nodeClick(View view) {
        Intent intent = new Intent(MainScreen.this, Node.class);
        intent.putExtra("state", NODE_STATE);
        startActivity(intent);
    }
    public void chordClick(View view) {
        Intent intent = new Intent(MainScreen.this, Node.class);
        intent.putExtra("state", CHORD_STATE);
        startActivity(intent);
    }
}
