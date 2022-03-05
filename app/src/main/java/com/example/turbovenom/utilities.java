package com.example.turbovenom;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class Utilities {

    private static Utilities instance;

    public Utilities() {
    }

    public static Utilities getInstance() {
        if (instance == null) {
            instance = new Utilities();
        }

        return instance;
    }
    public boolean validatemail(AppCompatActivity activity, String email)
    {
        // split string and check if we have formal email structure
        String[] splitString = email.split("@");

        if (splitString.length != 2) {
            Toast.makeText(activity, "Username or password are incorrect!", Toast.LENGTH_SHORT).show();
            return false;
        }

        String username = splitString[0];
        String domain = splitString[1];
        String[] splitusername = username.split(" ");
        if (splitusername.length != 1) {
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();

            return false;
        }


        char first = username.charAt(0);
        if (!(first >= 'a' & first <= 'z' || first == '_')) {
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (username.length() > 70) {
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (username.length() < 3) {
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < username.length(); i++) {


            char p = username.charAt(i);
            if (!(p >= 'a' & p <= 'z' || p >= 'A' & p <= 'Z' || p == '_' || p >= '0' & p <= '9')) {
                Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        return  true;

    }


    public boolean validatepassword(AppCompatActivity activity, String password)
    {
        if (password.trim().length()<8||password.trim().length()>30||password.trim().isEmpty())
            return false;
        int countCapital=0;
        int countSmall=0;
        int countWild=0;
        int countNum=0;
        char[] passwordArray=password.toCharArray();
        for(int i=0;i<password.trim().length();i++)
        {
            if (passwordArray[i]>='a'&&passwordArray[i]<='z')
                countSmall++;
            else if (passwordArray[i]>='A'&&passwordArray[i]<='Z')
                countCapital++;
            else if (passwordArray[i]>='0'&&passwordArray[i]>='9')
                countNum++;
            else countWild++;

        }
        if (countCapital==0||countSmall==0||countWild==0||countNum==0)return false;

        return true;

    }
}
