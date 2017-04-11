package laurenzsoft.com.pitchperfect;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Node extends AppCompatActivity {
    private static List<MediaPlayer> player = new LinkedList<>();
    private int state = -1;
    private List<Integer> nodesID, chordID;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);
        nodesID = new LinkedList<>();
        chordID = new LinkedList<>();
        Intent input = getIntent();
        state = input.getIntExtra("state", -1);
        listRaw();
        Random r = new Random();
        switch (state) {
            case MainScreen.NODE_STATE:
               id = nodesID.get(r.nextInt(nodesID.size()));
                break;
            case MainScreen.CHORD_STATE:
                id = chordID.get(r.nextInt(chordID.size()));
                break;
            default:
                Log.e("Node.java", "Wrong state int: " + state);
        }
        playNode(id);

    }
    public void checkClick(View view) {
        Intent intent = new Intent(Node.this, Solution.class);
        intent.putExtra("solution", getName(id));
        intent.putExtra("state", state);
        startActivity(intent);
        finish();
    }
    public void rehearClick(View view) {
        playNode(id);
    }
    private void listRaw(){
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            //Log.d("Raw Asset: ", fields[count].getName());
            if (fields[count].getName().contains("node")) {
                nodesID.add(count);
            } else if (fields[count].getName().contains("chord")) {
                chordID.add(count);
            }
        }
    }
    private String getName(int id) {
        Field[] fields=R.raw.class.getFields();
        return fields[id].getName();
    }
    private int getRawId(int id) {
        Field[] fields=R.raw.class.getFields();
        try {
            return fields[id].getInt(fields[id]);
        } catch (IllegalAccessException e) {
            Log.e("Node.java", "Illegal Acess Exception! \nCouldnt get the right Raw id from the field id");
            return 1;
        }
    }
    public void playNode(int id) {
        Log.d("Node.java", "Play Node with id" + id);
        try {
            MediaPlayer mPlayer = MediaPlayer.create(Node.this, getRawId(id));
            mPlayer.start();
            player.add(mPlayer);
            for (int i = 0; i < player.size(); i++) {
                if (!player.get(i).isPlaying()) {
                    player.get(i).release();
                    player.remove(i);
                    break;
                }
            }
        } catch (RuntimeException exc) {
            Log.e("Node.java", "RuntimeException!\nCould not play the file with the id " + id + " and the name " + getName(id));
        }

    }
}
