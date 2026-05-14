package com.example.practicabd.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2 : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `Phone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`number` TEXT NOT NULL, " +
                    "`personId` INTEGER NOT NULL, " +
                    "`type` INTEGER NOT NULL, " +
                    "FOREIGN KEY(`personId`) REFERENCES `Person`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )"
        )
    }

}