package com.example.emsd_app.BusinessLayer;

import android.annotation.SuppressLint;

import com.example.emsd_app.DataAccess.EMDSDB;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class Login {



    public String Username;
    public String Password;
    private String EncryptedPassword;



    public String GetUsername(String NewUsername){

        Username = NewUsername;


        return Username;
    }

    public String GetPassword(String NewPassword){

        Password = NewPassword;

        EncryptPassword();

        return Password;
    }

    private String EncryptPassword(){

        try {
            //This programming statement was adapted from GitHub:
            //Link: https://github.com/scottyab/AESCrypt-Android
            //Author: Scott Alexander-Bown
            //Author Profile Link: https://github.com/scottyab
            EncryptedPassword = AESCrypt.encrypt(Password,"safe!");
            Password = EncryptedPassword;

            Login();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


        return EncryptedPassword;
    }

    @SuppressLint("NotConstructor")
    public void Login(){

        EMDSDB DALayer = new EMDSDB();

        DALayer.LoginNewInstructor(Username,EncryptedPassword);


    }


}
