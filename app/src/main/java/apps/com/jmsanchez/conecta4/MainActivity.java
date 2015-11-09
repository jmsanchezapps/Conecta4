package apps.com.jmsanchez.conecta4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int ids[][] = {
            {R.id.item11, R.id.item12, R.id.item13, R.id.item14, R.id.item15, R.id.item16, R.id.item17},
            {R.id.item21, R.id.item22, R.id.item23, R.id.item24, R.id.item25, R.id.item26, R.id.item27},
            {R.id.item31, R.id.item32, R.id.item33, R.id.item34, R.id.item35, R.id.item36, R.id.item37},
            {R.id.item41, R.id.item42, R.id.item43, R.id.item44, R.id.item45, R.id.item46, R.id.item47},
            {R.id.item51, R.id.item52, R.id.item53, R.id.item54, R.id.item55, R.id.item56, R.id.item57},
            {R.id.item61, R.id.item62, R.id.item63, R.id.item64, R.id.item65, R.id.item66, R.id.item67}
        };

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
    }

    private int getRowById(int id){
        for(int i = 0; i < this.ids.length; i++){
            for(int j = 0; j < this.ids[i].length; j++){
                if(id == this.ids[i][j])
                    return i;
            }
        }

        return -1;
    }

    private int getColumnById(int id){
        for(int i = 0; i < this.ids.length; i++){
            for(int j = 0; j < this.ids[i].length; j++){
                if(id == this.ids[i][j])
                    return j;
            }
        }

        return -1;
    }

    public void pressed(View v)
    {
        int id = v.getId();
        int row = this.getRowById(id);
        int column = this.getColumnById(id);

        if(row == -1 || column == -1)
            return;

        boolean validPosition = game.isPosiblePutPieceIn(row, column);

        if(validPosition){
            game.playPlayer2(row, column);
            game.playPlayer1();
            this.updateBoard();
        }
        else{
            Toast.makeText(this, R.string.advicePositionNotAvailable, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBoard(){
        for(int i = 0; i < this.ids.length; i++){
            for(int j = 0; j < this.ids[i].length; j++){
                ImageButton button = (ImageButton)findViewById(this.ids[i][j]);
                if(game.isFree(i,j))
                    button.setImageResource(R.drawable.c4_button);
                else if(game.isPlayer1(i,j))
                    button.setImageResource(R.drawable.c4_button_player1);
                else if(game.isPlayer2(i,j))
                    button.setImageResource(R.drawable.c4_button_player2);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
