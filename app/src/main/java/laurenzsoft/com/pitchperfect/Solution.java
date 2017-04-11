package laurenzsoft.com.pitchperfect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Solution extends AppCompatActivity {
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        Intent intent = getIntent();
        Button button = (Button) findViewById(R.id.solutionButton);
        button.setText(intent.getStringExtra("solution").split("_")[1]);
        state = intent.getIntExtra("state", 1);

    }
    public void onClick(View view) {
        Intent intent = new Intent(Solution.this, Node.class);
        intent.putExtra("state", state);
        startActivity(intent);
        finish();
    }
}
