package com.example.app20;

public class ListTwo {
    private String Users_Key;
    private String Zametki_Text;

    public ListTwo(String users_Key, String zametki_Text) {
        Users_Key = users_Key;
        Zametki_Text = zametki_Text;
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
