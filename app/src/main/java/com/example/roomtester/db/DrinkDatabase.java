package com.example.roomtester.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.io.Serializable;

@Database(entities = {Drink.class}, version = 1)
public abstract class DrinkDatabase extends RoomDatabase implements Serializable {
    public abstract DrinkDAO drinkDAO();

}