CREATE TABLE IF NOT EXISTS threed_user (
  "id_user"      BIGINT          NOT NULL,
  "nik"    CHARACTER VARYING(50) NOT NULL UNIQUE,
  "folowCount" INTEGER           NOT NULL,
  "modelsCount"       INTEGER    NOT NULL,
  "raiting" 	INTEGER          NOT NULL,
  "account"     BIGINT          NOT NULL,
  PRIMARY KEY ("id_user")
);
CREATE TABLE IF NOT EXISTS folowing (
  "id_user"      BIGINT NOT NULL,
  "id_folowing_user"      BIGINT NOT NULL,
  PRIMARY KEY ("id_user","id_folowing_user"),
  FOREIGN KEY ("id_folowing_user") REFERENCES "threed_user" ("id_user"),
  FOREIGN KEY ("id_user") REFERENCES "threed_user" ("id_user")
);
CREATE TABLE IF NOT EXISTS model_format (
  "id_format"      BIGINT NOT NULL,
  "formatName"      CHARACTER VARYING(50),
  PRIMARY KEY ("id_format")
);
CREATE TABLE IF NOT EXISTS renders (
  "id_render"      BIGINT NOT NULL,
  "size"      INTEGER NOT NULL,
  "po_render" CHARACTER VARYING(50),
  PRIMARY KEY ("id_render")
);
CREATE TABLE IF NOT EXISTS models (
  "id_author"   BIGINT NOT NULL,
  "id_model"    BIGINT NOT NULL,
  "modelName"   CHARACTER VARYING(50) NOT NULL,
  "polyCount"   INTEGER NOT NULL,
  "polyVertex"  INTEGER NOT NULL,
  "id_render"   BIGINT NOT NULL,
  "id_format"   BIGINT NOT NULL,
  FOREIGN KEY ("id_author") REFERENCES "threed_user" ("id_user"),
  FOREIGN KEY ("id_render") REFERENCES "renders" ("id_render"),
  FOREIGN KEY ("id_format") REFERENCES "model_format" ("id_format"),
  PRIMARY KEY ("id_author","id_model")
  
);
CREATE TABLE IF NOT EXISTS sail (
  "id_author"   BIGINT NOT NULL,
  "id_buyer"    BIGINT NOT NULL,
  "id_model"    BIGINT NOT NULL,
  "count"       INTEGER,
  PRIMARY KEY ("id_author","id_buyer","id_model"),
  FOREIGN KEY ("id_author","id_model") REFERENCES "models" ("id_author","id_model"),
  FOREIGN KEY ("id_buyer") REFERENCES "threed_user" ("id_user")
);
CREATE TABLE IF NOT EXISTS sail_story (
  "id_author"   BIGINT NOT NULL,
  "id_buyer"    BIGINT NOT NULL,
  "id_model"    BIGINT NOT NULL,
  "date"        DATE NOT NULL,
  PRIMARY KEY ("id_author","id_buyer","id_model"),
  FOREIGN KEY ("id_author","id_model","id_buyer") REFERENCES "sail" ("id_author","id_model","id_buyer")
);
