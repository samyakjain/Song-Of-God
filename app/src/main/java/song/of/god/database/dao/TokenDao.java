package song.of.god.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import song.of.god.database.entity.Token;

@Dao
public interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertToken(Token token);

    @Query("SELECT * FROM `token` order by id desc limit 1")
    LiveData<Token> getLatestTokenAsLiveData();

    @Query("SELECT * FROM `token` order by id desc limit 1")
    Token getLatestToken();
}
