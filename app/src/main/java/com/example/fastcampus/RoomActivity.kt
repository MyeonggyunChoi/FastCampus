package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        // 기본적으로 DB 작업은 메인쓰레드에서 동작시키지 않음
        // 이유는, DB 작업을 하는 동안 사용자가 기다려야 하기 때문
        // 해결잭은, Thread를 이용하거나 async를 이용하면 된다.
        // .allowMainThreadQueries()를 쓰면 메인 쓰레드에서 동작 가능
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-database"
        ).allowMainThreadQueries().build()

        findViewById<TextView>(R.id.save).setOnClickListener {
            val userProfile = UserProfile("홍", "길동")
            database.userProfileDao().insert(userProfile)
        }

        findViewById<TextView>(R.id.load).setOnClickListener {
            val userProfiles = database.userProfileDao().getAll()
            userProfiles.forEach {
                Log.d("testt", ""+ it.id + it.firstName)
            }
        }

        findViewById<TextView>(R.id.delete).setOnClickListener{
            database.userProfileDao().delete(1)
        }
    }
}

@Entity
class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "first_name")
    val firstName: String
) {
    constructor(lastName: String, firstName: String) : this(0, lastName, firstName)
}

@Dao // Data Accescc Object
interface UserProfileDao {
    // CRUD
    // Query
    @Insert(onConflict = REPLACE)
    fun insert(userProfile: UserProfile)

    @Query("DELETE FROM userprofile WHERE id = :userId")
    fun delete(userId: Int)

    @Query("SELECT * FROM userprofile")
    fun getAll(): List<UserProfile>
}

@Database(entities = [UserProfile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}