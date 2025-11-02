-- liquibase formatted sql



-- =========================================================
-- changeset vinith:1-create-new-seq
-- =========================================================
CREATE SEQUENCE IF NOT EXISTS ID_SEQ
    START WITH 1000000
    INCREMENT BY 1
    CACHE 10000;
-- rollback DROP SEQUENCE IF EXISTS ID_SEQ;

-- =========================================================
-- changeset vinith:2-rename-column
-- =========================================================
ALTER TABLE tiny_url
    RENAME COLUMN short_url TO id;
-- rollback ALTER TABLE tiny_url RENAME COLUMN id TO short_url;

-- =========================================================
-- changeset vinith:3-add-new-short-url-column
-- =========================================================
ALTER TABLE tiny_url
    ADD COLUMN short_url VARCHAR(7);
-- rollback ALTER TABLE tiny_url DROP COLUMN short_url;

-- =========================================================
-- changeset vinith:4-update-default-sequence
-- =========================================================
ALTER TABLE tiny_url
    ALTER COLUMN id SET DEFAULT nextval('ID_SEQ');
-- rollback ALTER TABLE tiny_url ALTER COLUMN id SET DEFAULT nextval('SHORT_URL_SEQ');

-- changeset vinith:5-drop-old-seq
DROP SEQUENCE IF EXISTS short_url_seq;
-- rollback CREATE SEQUENCE short_url_seq START WITH 1000000;
