package com.bresia.textmachina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private TextView userOutput;
    private Button buttonUseless;
    private Button buttonReverse;
    private Button buttonPig;
    private Button buttonVowels;
    private Button buttonPalin;
    private Button buttonWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = (EditText)findViewById(R.id.userInput);
        userOutput = (TextView)findViewById(R.id.userOutput);

        //useless button just repeats everything
        buttonUseless = (Button)findViewById(R.id.buttonUseless);
        buttonUseless.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String input = userInput.getText().toString();
                userOutput.setText(input);
            }
        });
        //reverses the string
        buttonReverse = (Button)findViewById(R.id.buttonReverse);
        buttonReverse.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String input = userInput.getText().toString();
                StringBuilder output = new StringBuilder(input).reverse();
                userOutput.setText(output.toString());  //need to convert b/c of compatibility ~_~
            }
        });
        //translates into pig latin
        buttonPig = (Button)findViewById(R.id.buttonPig);
        buttonPig.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String input = userInput.getText().toString();
                //add words to an array
                String[] tokens = input.split("\\W+");
                String result = "";
                String vowels = "aeiou";

                for(String str : tokens){
                    //check if first letter is a vowel or consonant
                    int vowelIndex = vowels.indexOf(str.charAt(0));
                    boolean isVowel = (vowelIndex >= 0);
                    if(isVowel) result += str + "-yay ";
                    //first letter is a consonant
                    //we check if there are consonant clusters
                    else {
                        if(vowels.indexOf(str.charAt(1)) < 0){
                            String word = str.substring(2);
                            result += (word+"-"+str.substring(0, 2)+"ay ");
                        }
                        else {
                            String word = str.substring(1); //includes all but first letter
                            result += (word+"-"+str.charAt(0)+"ay ");
                        }
                    }
                }
                userOutput.setText(result);
            }//end onClick
        });
        //count the total number of vowels
        buttonVowels = (Button)findViewById(R.id.buttonVowels);
        buttonVowels.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int count = 0;
                String input = userInput.getText().toString();
                for(int i = 0; i < input.length(); i++){
                    switch(input.charAt(i)){
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u':
                            count++;
                            break;
                        default:
                            //do nothing
                    }
                }
                userOutput.setText("There are "+count+" vowels total");
            }
        });
        //checks if input is a palindrome
        buttonPalin = (Button)findViewById(R.id.buttonPalin);
        buttonPalin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String input = userInput.getText().toString();
                StringBuilder reverseInput = new StringBuilder(input).reverse();
                if(input.equalsIgnoreCase(reverseInput.toString())) userOutput.setText("It is a palindrome");
                else userOutput.setText("It is not a palindrome");
            }
        });
        //determine number of words using a space delimiter
        buttonWords = (Button)findViewById(R.id.buttonWords);
        buttonWords.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String input = userInput.getText().toString();
                //split is the regex, trim makes sure there's no beginning or ending spaces
                int count = input.trim().split("\\W+").length;
                userOutput.setText("There are "+count+" words");
            }
        });
    }
}
