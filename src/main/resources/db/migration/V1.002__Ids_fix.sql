DROP TABLE followers;
DROP TABLE likes;
DROP TABLE messages;
DROP TABLE users;

CREATE TABLE users (
  id         UUID NOT NULL PRIMARY KEY,
  first_name TEXT NOT NULL,
  last_name  TEXT NOT NULL,
  login      TEXT NOT NULL,
  password   TEXT NOT NULL
);

CREATE TABLE followers (
  user_id    UUID NOT NULL REFERENCES users (id),
  folower_id UUID NOT NULL REFERENCES users (id),
  CHECK (user_id <> folower_id),
  PRIMARY KEY (user_id, folower_id)
);

CREATE TABLE messages (
  id       UUID      NOT NULL PRIMARY KEY,
  text     TEXT      NOT NULL,
  time_key TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id  UUID      NOT NULL REFERENCES users (id)
);

CREATE TABLE likes (
  message_id UUID NOT NULL REFERENCES messages (id),
  user_id    UUID NOT NULL REFERENCES users (id),
  PRIMARY KEY (message_id, user_id)
)