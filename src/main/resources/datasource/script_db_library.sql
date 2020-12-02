
CREATE SEQUENCE public.library_library_id_seq;

CREATE TABLE public.library (
                library_id INTEGER NOT NULL DEFAULT nextval('public.library_library_id_seq'),
                location VARCHAR NOT NULL,
                CONSTRAINT library_pk PRIMARY KEY (library_id)
);


ALTER SEQUENCE public.library_library_id_seq OWNED BY public.library.library_id;

CREATE SEQUENCE public.book_book_id_seq;

CREATE TABLE public.book (
                book_id INTEGER NOT NULL DEFAULT nextval('public.book_book_id_seq'),
                title VARCHAR NOT NULL,
                author VARCHAR NOT NULL,
                CONSTRAINT book_pk PRIMARY KEY (book_id)
);


ALTER SEQUENCE public.book_book_id_seq OWNED BY public.book.book_id;

CREATE SEQUENCE public.user_user_id_seq;

CREATE TABLE public.user (
                user_id INTEGER NOT NULL DEFAULT nextval('public.user_user_id_seq'),
                first_name VARCHAR(100) NOT NULL,
                last_name VARCHAR(100) NOT NULL,
                pseudo VARCHAR(50) NOT NULL,
                email VARCHAR(150) NOT NULL,
                password VARCHAR NOT NULL,
                CONSTRAINT user_pk PRIMARY KEY (user_id)
);


ALTER SEQUENCE public.user_user_id_seq OWNED BY public.user.user_id;

CREATE SEQUENCE public.copy_copy_id_seq;

CREATE TABLE public.copy (
                copy_id INTEGER NOT NULL DEFAULT nextval('public.copy_copy_id_seq'),
                loan_start_date DATE,
                loan_end_date DATE,
                extend BOOLEAN NOT NULL,
                available BOOLEAN NOT NULL,
                user_id INTEGER NOT NULL,
                book_id INTEGER NOT NULL,
                library_id INTEGER NOT NULL,
                CONSTRAINT copy_pk PRIMARY KEY (copy_id)
);


ALTER SEQUENCE public.copy_copy_id_seq OWNED BY public.copy.copy_id;

ALTER TABLE public.copy ADD CONSTRAINT library_copy_fk
FOREIGN KEY (library_id)
REFERENCES public.library (library_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.copy ADD CONSTRAINT book_copy_fk
FOREIGN KEY (book_id)
REFERENCES public.book (book_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.copy ADD CONSTRAINT user_copy_fk
FOREIGN KEY (user_id)
REFERENCES public.user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
