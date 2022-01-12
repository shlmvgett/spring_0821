package com.ots.springbooks.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog
public class DatabaseChangelog {

  @ChangeSet(order = "001", id = "dropDb", author = "dev", runAlways = true)
  public void dropDb(MongoDatabase db) {
    db.drop();
  }
}
