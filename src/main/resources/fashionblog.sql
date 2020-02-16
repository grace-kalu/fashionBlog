
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;

CREATE TABLE posts (
    id serial PRIMARY KEY NOT NULL ,
    title VARCHAR (255) UNIQUE NOT NULL,
    content TEXT NOT NULL,
    likes integer NOT NULL,
    unlikes integer NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE comments (
    id serial PRIMARY KEY NOT NULL,
    content text NOT NULL,
    post_id integer NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id, post_id),
    CONSTRAINT comment_post_fkey FOREIGN KEY (post_id) REFERENCES posts (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
    )