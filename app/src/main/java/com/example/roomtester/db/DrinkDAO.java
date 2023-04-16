package com.example.roomtester.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DrinkDAO {
    @Query("SELECT * from Drinks")
    List<Drink> getAll();

    @Query("SELECT * from Drinks WHERE id = :id limit 1")
    Drink findById(long id);

    @Update
    void update(Drink drink);

    @Insert
    void insertAll(Drink...Drinks);

    @Delete
    void delete(Drink drink);

}
