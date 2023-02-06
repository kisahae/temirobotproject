package com.example.Temiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class Quiz extends AppCompatActivity {
    public static int winner;
    public static Object timer;
    int current;
    int index = 1;
    int order = 0;
    int select;
    int turn = 1;
    int[] loc = {0,0,0,0,0,0,0,0,0};
    int temi_num = 0;
    boolean answer = false;
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
    public static String quizList[][] = new String[101][9];
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

                for(int j = 0; j < 9; j++){
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
        final MediaPlayer soundEffectCorrect = MediaPlayer.create(this,R.raw.correct);
        final MediaPlayer soundEffectIncorrect = MediaPlayer.create(this,R.raw.incorrect);
        fi = getResources().openRawResource(R.raw.quizlist);

        try {
            byte[] data = new byte[fi.available()];
            fi.read(data);
            fi.close();
            String s = new String(data, "UTF-8");
            word = new FileSplit(s);
        } catch (IOException e) {
        }


        for (int i = 0; i < 9; i++)
        {
            myRef.child("Temi_loc/temi" +i).setValue(0); // initializing
        }
        questionText = findViewById(R.id.question1);
        answer1 = findViewById(R.id.option1);
        answer2 = findViewById(R.id.option2);
        answer3 = findViewById(R.id.option3);
        answer4 = findViewById(R.id.option4);

        moves = findViewById(R.id.moves);
        team = findViewById(R.id.team);
        select_temi1 = findViewById(R.id.select_temi1);
        select_temi2 = findViewById(R.id.select_temi2);
        radioGroup = findViewById(R.id.radio2);
        select = 2;
        progressbar1 = (ProgressBar) findViewById(R.id.pb1);
        myRef.child("Temi_loc/temi1").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[1] = (int) snapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi2").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[2] = (int) snapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi3").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[3] = (int) snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi4").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[4] = (int) snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi5").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[5] = (int) snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi6").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[6] = (int) snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi7").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[7] = (int) snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRef.child("Temi_loc/temi8").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loc[8] = (int) snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        random = new Random();
        order = random.nextInt(80);
        quizList[order][7] = "1";
        questionText.setText(quizList[order][1]);
        answer1.setText(quizList[order][2]);
        answer2.setText(quizList[order][3]);
        answer3.setText(quizList[order][4]);
        answer4.setText(quizList[order][5]);



        class Timer extends CountDownTimer {
            public Timer(long millisInFuture, long countdownInterval) {
                super(millisInFuture, countdownInterval);
            }

            @Override
            public void onTick(long l) {
                if (progressbar1.getProgress() > 0) {
                    current = progressbar1.getProgress() - 10;
                    progressbar1.setProgress(current);
                }
                if(isfinished()>0)
                {
                    this.cancel();
                    Intent intent1 = new Intent(getApplicationContext(),finish.class);
                    startActivity(intent1);
                }
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(Quiz.this);
                dialog.setContentView(R.layout.timesup);
                soundEffectIncorrect.start();
                dialog.show();
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Timer timer = new Timer(20000, 1000);
                        turn = (index) % gamesetting.num_team + 1;
                        index = index + 1;
                        team.setText("team " + turn);
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(200);
                        timer.cancel();
                        timer.start();
                    }
                });
            }
        }
        Timer timer = new Timer(20000, 1000);
        timer.start();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                select = 2;
                Dialog dialog = new Dialog(Quiz.this);
                if(Integer.parseInt(quizList[order][6]) == 1)
                {
                    soundEffectCorrect.start();
                    answer = true;
                    dialog.setContentView(R.layout.correct);
                    dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            select = 1;
                        }
                    });
                }
                else
                {
                    soundEffectIncorrect.start();
                    dialog.setContentView(R.layout.incorrect);
                }
                dialog.show();
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        temi_num = 4 * (select - 1) + turn;
                        turn = (index) % gamesetting.num_team + 1;
                        index = index + 1;
                        team.setText("team " + turn);
                        if(answer)
                        {

                            if(checkfriendly(temi_num)) move_temi(4*(select%2) + turn%gamesetting.num_team+1,quizList[order][7]);
                            move_temi(temi_num, quizList[order][7]);
                            checkenemy(temi_num,quizList[order][7]);
                            answer = false;
                        }
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(200);
                        timer.start();
                    }
                });

            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                select = 2;
                Dialog dialog = new Dialog(Quiz.this);
                if(Integer.parseInt(quizList[order][6]) == 2){
                    soundEffectCorrect.start();
                    answer=true;
                    dialog.setContentView(R.layout.correct);
                    dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            select = 1;
                        }
                    });
                } else {
                    soundEffectIncorrect.start();
                    dialog.setContentView(R.layout.incorrect);

                }
                dialog.show();
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        temi_num = 4 * (select - 1) + turn;
                        turn = (index) % gamesetting.num_team + 1;
                        index = index + 1;
                        team.setText("team " + turn);
                        if(answer)
                        {
                            if(checkfriendly(temi_num)) move_temi(4*(select%2) + turn%gamesetting.num_team+1,quizList[order][7]);
                            move_temi(temi_num, quizList[order][7]);
                            checkenemy(temi_num,quizList[order][7]);
                            answer = false;
                        }
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(200);
                        timer.start();

                    }
                });

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                select = 2;
                Dialog dialog = new Dialog(Quiz.this);
                if(Integer.parseInt(quizList[order][6]) == 3){
                    soundEffectCorrect.start();
                    answer = true;
                    dialog.setContentView(R.layout.correct);
                    dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            select = 1;
                        }
                    });
                } else {
                    soundEffectIncorrect.start();
                    dialog.setContentView(R.layout.incorrect);
                }
                dialog.show();

                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        temi_num = 4 * (select - 1) + turn;
                        turn = (index) % gamesetting.num_team + 1;
                        index = index + 1;
                        team.setText("team " + turn);
                        if(answer)
                        {
                            if(checkfriendly(temi_num)) move_temi(4*(select%2) + turn%gamesetting.num_team+1,quizList[order][7]);
                            move_temi(temi_num, quizList[order][7]);
                            checkenemy(temi_num,quizList[order][7]);
                            answer = false;
                        }
                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(200);
                        timer.start();
                    }
                });

            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                select = 2;
                Dialog dialog = new Dialog(Quiz.this);
                if(Integer.parseInt(quizList[order][6]) == 4)
                {
                    soundEffectCorrect.start();
                    answer = true;
                    dialog.setContentView(R.layout.correct);
                    dialog.findViewById(R.id.select_temi1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            select = 1;
                        }
                    });
                }
                else
                {
                    soundEffectIncorrect.start();
                    dialog.setContentView(R.layout.incorrect);
                }
                dialog.show();
                dialog.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        temi_num = 4 * (select - 1) + turn;
                        turn = (index) % gamesetting.num_team + 1;
                        index = index + 1;
                        team.setText("team " + turn);
                        if(answer)
                        {
                            if(checkfriendly(temi_num)) move_temi(4*(select%2) + turn%gamesetting.num_team+1,quizList[order][7]);
                            move_temi(temi_num, quizList[order][7]);
                            checkenemy(temi_num,quizList[order][7]);
                            answer = false;
                        }

                        newQuestion();
                        dialog.hide();
                        progressbar1.setProgress(200);
                        timer.start();
                    }
                });

            }
        });

    }


    void newQuestion() {
        random = new Random();
        order = random.nextInt(80);
        i = 0;
        while(quizList[order][8] == "1") {
            random = new Random();
            order = random.nextInt(80);
            i++;
            if(i>100) {
                Toast.makeText(getApplicationContext(),i + "No more question!!", Toast.LENGTH_LONG).show();
                return;
            }
        }
//        String move_text = "for " + QuestionAnswer.moves[index-1] + " moves";
        quizList[order][8] = "1";
        questionText.setText(quizList[order][1]);
        answer1.setText(quizList[order][2]);
        answer2.setText(quizList[order][3]);
        answer3.setText(quizList[order][4]);
        answer4.setText(quizList[order][5]);
        moves.setText("for " + quizList[order][7]+" moves");
//        String move_text = "for " + QuestionAnswer.moves[index-1] + " moves";
//        questionText.setText(QuestionAnswer.question[index-1]);
//        answer1.setText(QuestionAnswer.choices[index-1][0]);
//        answer2.setText(QuestionAnswer.choices[index-1][1]);
//        answer3.setText(QuestionAnswer.choices[index-1][2]);
//        answer4.setText(QuestionAnswer.choices[index-1][3]);
//        moves.setText(move_text);
    }

    void move_temi(int temi_number, String moves) {
        myRef.child("Temi_loc/temi" + temi_number).setValue(loc[temi_number] + Integer.parseInt(moves));
    }

    int isfinished()
    {
        int win_team_number= 0;
        if(loc[1] > 16 && loc[5]>16) win_team_number = 1;
        else if (loc[2] > 16 && loc[6]>16) win_team_number = 2;
        else if(loc[3] > 16 && loc[7]>16) win_team_number = 3;
        else if(loc[4] > 16 && loc[8]>16) win_team_number = 4;
        winner = win_team_number;
        return winner;
    }

    boolean checkfriendly(int temi_number)
    {
        if(loc[temi_number] == 0)
            return false;
        else if(temi_number <5)
        {
            if(loc[temi_number] == loc[temi_number+4])
                return true;
        }
        else
        {
            if(loc[temi_number] == loc[temi_number-4])
                return true;
        }
        return false;
    }

    void checkenemy(int temi_number,String moves)
    {
        int move = Integer.parseInt(moves);
        for(int i = 1; i<9; i++)
        {
            if(loc[i] == loc[temi_number]+move)
            {
                if( i%4 == temi_number|| i == temi_number|| i == temi_number%4)
                    continue;
                myRef.child("Temi_loc/temi" + i).setValue(0);
            }
        }
    }
}