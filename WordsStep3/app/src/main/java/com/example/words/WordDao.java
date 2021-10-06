package com.example.words;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    public void insertWords(Word... words);

    @Update
    public int updateWords(Word ... words);

    @Delete
    public void deleteWords(Word ... words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    public List<Word> getAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    public LiveData<List<Word>> getAllWordsLive();

    @Query("SELECT * FROM WORD WHERE english_word LIKE :patten ORDER BY ID DESC")
    public LiveData<List<Word>> findWordsWithPatten(String patten);


}
