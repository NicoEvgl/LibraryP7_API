INSERT INTO public.copy(loan_start_date,loan_end_date,extend,available,user_id,book_id,library_id)
VALUES ('26/10/2020','23/11/2020','false','false',1,4,4)

INSERT INTO public.copy(extend,available,user_id,book_id,library_id)
VALUES ('false','false',1,6,4)

INSERT INTO public.copy(loan_start_date,loan_end_date,extend,available,user_id,book_id,library_id)
VALUES ('25/11/2020','23/12/2020','false','false',1,2,4)

INSERT INTO public.copy(loan_start_date,loan_end_date,extend,available,user_id,book_id,library_id)
VALUES ('25/11/2020','23/12/2020','false','true',1,3,4)

INSERT INTO public.copy(loan_start_date,loan_end_date,extend,available,user_id,book_id,library_id)
VALUES ('11/11/2020','09/12/2020','false','false',1,5,4)

INSERT INTO public.library(location)
VALUES ('Bibliothèque Mériadeck - 85 Cours Maréchal Juin')

INSERT INTO public.book(title,author)
VALUES ('La Condition humaine','André Malraux')

INSERT INTO public.book(title,author)
VALUES ('Vingt mille lieues sous les mers','Jules Verne')

INSERT INTO public.book(title,author)
VALUES ('Le château de ma mère','Marcel Pagnol')

INSERT INTO public.book(title,author)
VALUES ('La gloire de mon père','Marcel Pagnol')

INSERT INTO public.book(title,author)
VALUES ('Germinal','Emile Zola')

INSERT INTO public.book(title,author)
VALUES ('Nana','Emile Zola')

INSERT INTO public.book(title,author)
VALUES ('Eugénie Grandet','Honoré de Balzac')

INSERT INTO public.book(title,author)
VALUES ('L''Étranger ','Albert Camus')

INSERT INTO public.book(title,author)
VALUES ('Quatrevingt-treize','Victor Hugo')

INSERT INTO public.book(title,author)
VALUES ('Notre-Dame de Paris','Victor Hugo')

INSERT INTO public.book(title,author)
VALUES ('Les Misérables','Victor Hugo')

INSERT INTO public.user(first_name,last_name,pseudo,email,password)
VALUES ('MON PRENOM','MON NOM','Nicoe','monemail@gmail.com','Pass123')