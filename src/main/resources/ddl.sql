CREATE TABLE public.account (
	id text NOT NULL,
	name text NOT NULL,
	email text NOT NULL,
	"password" text NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (id)
);

CREATE TABLE public.team (
	id text NOT NULL,
	"name" text NOT NULL,
	category char(2) NOT NULL,
	CONSTRAINT team_pk PRIMARY KEY (id)
);

CREATE TABLE public.account_team (
	account_id text NOT NULL,
	team_id text NOT NULL,
	CONSTRAINT account_team_pk PRIMARY KEY (account_id,team_id),
	CONSTRAINT account_team_fk FOREIGN KEY (account_id) REFERENCES public.account(id),
	CONSTRAINT account_team_fk_1 FOREIGN KEY (team_id) REFERENCES public.team(id)
);

CREATE TABLE public."member" (
	id text NOT NULL,
	team_id text NOT NULL,
	"name" text NOT NULL,
	"no" int4 NULL,
	CONSTRAINT member_pk PRIMARY KEY (id),
	CONSTRAINT member_fk FOREIGN KEY (team_id) REFERENCES public.team(id)
);

CREATE TABLE public.activity (
	id text NOT NULL,
	team_id text NOT NULL,
	title text NOT NULL,
	"year" int4 NOT NULL,
	"month" int4 NOT NULL,
	"day" int4 NOT NULL,
	CONSTRAINT activity_pk PRIMARY KEY (id),
	CONSTRAINT activity_fk FOREIGN KEY (team_id) REFERENCES public.team(id)
);

CREATE TABLE public.score (
	id text NOT NULL,
	activity_id text NOT NULL,
	member_id text NOT NULL,
	goal int4 NOT NULL DEFAULT 0,
	assist int4 NOT NULL DEFAULT 0,
	win int4 NOT NULL DEFAULT 0,
	CONSTRAINT score_pk PRIMARY KEY (id),
	CONSTRAINT score_un UNIQUE (activity_id,member_id),
	CONSTRAINT score_fk FOREIGN KEY (activity_id) REFERENCES public.activity(id),
	CONSTRAINT score_fk_1 FOREIGN KEY (member_id) REFERENCES public."member"(id)
);
