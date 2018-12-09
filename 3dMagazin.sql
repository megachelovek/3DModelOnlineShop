CREATE TABLE IF NOT EXISTS threed_user (
  "user_id"      BIGINT          NOT NULL,
  "user_name"    CHARACTER VARYING(50) NOT NULL UNIQUE,
  "followers_count" INTEGER           NOT NULL,
  "models_count"       INTEGER    NOT NULL,
  "rating" 	INTEGER          NOT NULL,
  "account"     BIGINT          NOT NULL,
  PRIMARY KEY ("user_id")
);
CREATE TABLE IF NOT EXISTS following (
  "user_id"      BIGINT NOT NULL,
  "following_user_id"      BIGINT NOT NULL,
  PRIMARY KEY ("user_id","following_user_id"),
  FOREIGN KEY ("following_user_id") REFERENCES "threed_user" ("user_id"),
  FOREIGN KEY ("user_id") REFERENCES "threed_user" ("user_id")
);
CREATE TABLE IF NOT EXISTS model_format (
  "format_id"      BIGINT NOT NULL,
  "format_name"      CHARACTER VARYING(50),
  PRIMARY KEY ("format_id")
);
CREATE TABLE IF NOT EXISTS render (
  "render_id"      BIGINT NOT NULL,
  "size"      INTEGER NOT NULL,
  "render_software" CHARACTER VARYING(50),
  PRIMARY KEY ("render_id")
);
CREATE TABLE IF NOT EXISTS model (
  "author_id"   BIGINT NOT NULL,
  "model_id"    BIGINT NOT NULL,
  "model_name"   CHARACTER VARYING(50) NOT NULL,
  "poly_count"   INTEGER NOT NULL,
  "poly_vertex"  INTEGER NOT NULL,
  "render_id"   BIGINT NOT NULL,
  "format_id"   BIGINT NOT NULL,
  FOREIGN KEY ("author_id") REFERENCES "threed_user" ("user_id"),
  FOREIGN KEY ("render_id") REFERENCES "render" ("render_id"),
  FOREIGN KEY ("format_id") REFERENCES "model_format" ("format_id"),
  PRIMARY KEY ("author_id","model_id")
  
);
CREATE TABLE IF NOT EXISTS purchase (
  "author_id"   BIGINT NOT NULL,
  "buyer_id"    BIGINT NOT NULL,
  "model_id"    BIGINT NOT NULL,
  "amount"       INTEGER,
  "date"        DATE NOT NULL,
  PRIMARY KEY ("author_id","buyer_id","model_id"),
  FOREIGN KEY ("author_id","model_id") REFERENCES "model" ("author_id","model_id"),
  FOREIGN KEY ("buyer_id") REFERENCES "threed_user" ("user_id")
);/*
CREATE TABLE IF NOT EXISTS purchases_story (
  "author_id"   BIGINT NOT NULL,
  "buyer_id"    BIGINT NOT NULL,
  "model_id"    BIGINT NOT NULL,
  "date"        DATE NOT NULL,
  PRIMARY KEY ("author_id","buyer_id","model_id", "date"),
  FOREIGN KEY ("author_id","model_id","buyer_id") REFERENCES "purchase" ("author_id","model_id","buyer_id")
);*/
