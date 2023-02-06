package com.example.Temiproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static String question [] ={
            "Who is the richest person in the world?",
            "Which one is not the programming language?",
            "Which one is not the city in United States?",
            "What is the result of 25X25?",
            "Which one is not the university in South Korea?",
            "Which one is the South Korean Footballer?",
            "What is the capital city of Germany?",
            "When is the National Liberation Day of Korea?",
            "Who is the inventor of the telephone?",
            "What is the name of robot that is used in ICT class?",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""

    };

    public static String choices[][]={
            {"A. Jack Ma","B. Bill Gates","C. Elon Musk","D. Jeff Bezos"},
            {"A. Stinger","B. Python","C. Java","D. C++"},
            {"A. Las Vegas","B. Ansan","C. San Fransisco","D. New York"},
            {"A. 640","B. 700","C. 625","D. 900"},
            {"A. Harvard University","B. Hanyang University","C. Korea University","D. Yonsei University"},
            {"A. Messi","B. Shinji Kagawa","C. Ronaldo","D. Son Heung Min"},
            {"A. Berlin","B. Munchen","C. Hamburg","D. Dortmund"},
            {"A. 14 August","B. 15 August","C. 16 August","D. 17 August"},
            {"A. Tesla","B. Graham Bell","C. Einstein ","D. Niels Bohr"},
            {"A. Silvia","B. Terminator","C. Robocop","D. Temi Robot"},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "},
            {"A. ", "B. ","C. ","D. "}
    };
    public static String moves[] ={  // we have to decide the number of moves for each quiz precisely
            "10","10","10","10","10","10","10","5","5","5","5","5","5","5","5","5","5","5","5","5","5","5"
    };

    public static String correctAnswer[]={
            "Jeff Bezos",
            "Stinger",
            "Ansan",
            "625",
            "Harvard University",
            "Son Heung Min",
            "Berlin",
            "15 August",
            "Graham Bell",
            "Temi Robot",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""



    };
}