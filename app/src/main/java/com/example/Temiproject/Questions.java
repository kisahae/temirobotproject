/*package com.example.temiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.Random;

public class Questions extends AppCompatActivity {
    int current;
    int index = 0;
    int select;
    int loc;
    int order = 0;
    int temi_num = 0;
    TextView questionText;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    TextView moves;
    TextView team;
    RadioGroup radioGroup;
    RadioButton select_temi1;
    RadioButton select_temi2;
    ProgressBar progressbar1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public static String quizList[][] = new String[100][8];
    InputStream fi;
    FileSplit word;
    Random random;
    int i;




    public class FileSplit {
        public FileSplit(String str) {
            String tmp[] = str.split("\n");
            String s;

            for (int i = 0; i < tmp.length; i++) {

                s = tmp[i];
                String tmp2[] = s.split(":");

                for(int j = 0; j < 8; j++){
                    tmp2[j]=tmp2[j].trim();
                    quizList[i][j]=tmp2[j];
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        fi = getResources().openRawResource(R.raw.quizlist);

        try {
            byte[] data = new byte[fi.available()];
            fi.read(data);
            fi.close();
            String s = new String(data, "UTF-8");
            word = new FileSplit(s);
        } catch (IOException e) {
        }



        for(int i = 0; i<9 ; i++) {
            myRef.child("Temi_loc").child(Integer.toString(i)).setValue(0);
        } // initializing
        questionText = findViewById(R.id.question1);
        answer1 = findViewById(R.id.option1);
        answer2 = findViewById(R.id.option2);
        answer3 = findViewById(R.id.option3);
        answer4 = findViewById(R.id.option4);
        moves = findViewById(R.id.moves);
        team = findViewById(R.id.team);
        select_temi1 =  findViewById(R.id.select_temi1);
        select_temi2 =  findViewById(R.id.select_temi2);
        radioGroup =  findViewById(R.id.radio2);
        select = 2;
        progressbar1 = (ProgressBar) findViewById(R.id.pb1);
        quizList[order][7] = "1";

        questionText.setText((order + 1) + ". " + quizList[order][1]);
        answer1.setText(quizList[order][2]);
        answer2.setText(quizList[order][3]);
        answer3.setText(quizList[order][4]);
        answer4.setText(quizList[order][5]);

        class Timer extends CountDownTimer{
            int turn;
            public Timer(long millisInFutre, long countdownInterval)
            {
                super(millisInFutre,countdownInterval);
            }
            @Override
            public void onTick(long l) {
                if (progressbar1.getProgress() > 0) {
                    current = progressbar1.getProgress() - 10;
                    progressbar1.setProgress(current);
                }
            }

            @Override
            public void onFinish() {
                progressbar1.setProgress(200);
                Dialog dialog = new Dialog(Questions.this);
                dialog.setContentView(R.layout.timesup);
                dialog.show();
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Timer timer = new Timer(20000, 1000);
                        turn = index % gamesetting.num_team + 1;
                        newQuestion();
                        dialog.hide();
                        timer.start();
                    }
                });
            }
        }
        Timer timer = new Timer(20000,1000);
        timer.start();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Questions.this);
                if(Integer.parseInt(quizList[order][6]) == 1) {
                    dialog.setContentView(R.layout.correct);
                    timer.cancel();
                }
                else
                {
                    dialog.setContentView(R.layout.incorrect);
                    timer.cancel();
                }
                dialog.show();
                dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select = 1;
                    }
                });
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timer.turn = index % gamesetting.num_team + 1;
                        team.setText("Team " + timer.turn);
                        temi_num = 4*(select-1) +timer.turn ;
                        move_temi(temi_num,QuestionAnswer.moves[index]);
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(100);
                        timer.cancel();
                        timer.start();
                    }
                });

            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Questions.this);
                if (Integer.parseInt(quizList[order][6]) == 2) {
                    dialog.setContentView(R.layout.correct);
                    timer.cancel();
                } else {
                    dialog.setContentView(R.layout.incorrect);
                    timer.cancel();
                }
                dialog.show();
                dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select = 1;
                    }
                });
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timer.turn = index % gamesetting.num_team + 1;
                        team.setText("Team " + timer.turn);
                        temi_num = 4*(select-1) +timer.turn ;
                        move_temi(temi_num,QuestionAnswer.moves[index]);
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(100);
                        timer.cancel();
                        timer.start();

                    }
                });

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Questions.this);
                if (Integer.parseInt(quizList[order][6]) == 3){
                    dialog.setContentView(R.layout.correct);
                    timer.cancel();
                } else {
                    dialog.setContentView(R.layout.incorrect);
                    timer.cancel();
                }
                dialog.show();
                dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select = 1;
                    }
                });
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timer.turn = index % gamesetting.num_team + 1;
                        team.setText("Team " +timer.turn);
                        temi_num = 4*(select-1) +timer.turn ;
                        move_temi(temi_num,QuestionAnswer.moves[index]);
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(100);
                        timer.cancel();
                        timer.start();
                    }
                });

            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Questions.this);
                if (Integer.parseInt(quizList[order][6]) == 4) {
                    dialog.setContentView(R.layout.correct);
                    timer.cancel();
                } else {
                    dialog.setContentView(R.layout.incorrect);
                    timer.cancel();
                }
                dialog.show();
                dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select = 1;
                    }
                });

                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timer.turn = index % gamesetting.num_team + 1;
                        team.setText("Team " + timer.turn);
                        temi_num = 4*(select-1) +timer.turn ;
                        move_temi(temi_num,QuestionAnswer.moves[index]);
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(100);
                        timer.cancel();
                        timer.start();
                    }
                });

            }
        });

    }



    void newQuestion(){
        random = new Random();
        order = random.nextInt(10);
        i = 0;
        while(quizList[order][7] == "1") {
            random = new Random();
            order = random.nextInt(10);
            i++;
            if(i>100) {
                Toast.makeText(getApplicationContext(),i + "No more question!!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        index = index + 1;
        String move_text = "For "+ QuestionAnswer.moves[index] + " Moves";
        quizList[order][7] = "1";
        questionText.setText((order + 1) + ". " + quizList[order][1]);
        answer1.setText(quizList[order][2]);
        answer2.setText(quizList[order][3]);
        answer3.setText(quizList[order][4]);
        answer4.setText(quizList[order][5]);
        moves.setText(move_text);
    }

    int getlocation(int temi_number) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int value = (int)snapshot.child("Temi_loc/"+temi_number).getValue(Integer.class);
                loc = value;

                Log.d("value",Long.toString(value));
                Log.d("temi_num",Integer.toString(temi_number));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return loc;
    }

    void move_temi(int temi_number, String moves)
    {
        myRef.child("Temi_loc").child(Long.toString(temi_number)).setValue(getlocation(temi_number)+Integer.parseInt(moves));
    }
}*/