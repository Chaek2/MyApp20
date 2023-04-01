package com.example.app20;

import javax.annotation.Nullable;

public class Zametki {
    private @Nullable String ID;
    private String Users_Key;
    private String Zametki_Text;

    public Zametki(@Nullable String ID, String users_Key, String zametki_Text) {
        this.ID = ID;
        Users_Key = users_Key;
        Zametki_Text = zametki_Text;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsers_Key() {
        return Users_Key;
    }

    public void setUsers_Key(String users_Key) {
        Users_Key = users_Key;
    }

    public String getZametki_Text() {
        return Zametki_Text;
    }

    public void setZametki_Text(String zametki_Text) {
        Zametki_Text = zametki_Text;
    }
}
