--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: name_game; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.name_game (
    id integer NOT NULL,
    name text NOT NULL,
    player_id integer NOT NULL
);


ALTER TABLE public.name_game OWNER TO postgres;

--
-- Name: gameNames_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."gameNames_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."gameNames_id_seq" OWNER TO postgres;

--
-- Name: gameNames_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."gameNames_id_seq" OWNED BY public.name_game.id;


--
-- Name: game_state; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.game_state (
    id integer NOT NULL,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL,
    name character varying,
    actual_map integer
);


ALTER TABLE public.game_state OWNER TO postgres;

--
-- Name: game_state_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.game_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.game_state_id_seq OWNER TO postgres;

--
-- Name: game_state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.game_state_id_seq OWNED BY public.game_state.id;


--
-- Name: player; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player (
    id integer NOT NULL,
    player_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    strength integer,
    key boolean DEFAULT false
);


ALTER TABLE public.player OWNER TO postgres;

--
-- Name: player_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_id_seq OWNER TO postgres;

--
-- Name: player_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_id_seq OWNED BY public.player.id;


--
-- Name: game_state id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.game_state ALTER COLUMN id SET DEFAULT nextval('public.game_state_id_seq'::regclass);


--
-- Name: name_game id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.name_game ALTER COLUMN id SET DEFAULT nextval('public."gameNames_id_seq"'::regclass);


--
-- Name: player id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player ALTER COLUMN id SET DEFAULT nextval('public.player_id_seq'::regclass);


--
-- Data for Name: game_state; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.game_state (id, current_map, saved_at, player_id, name, actual_map) FROM stdin;
54	com.codecool.dungeoncrawl.logic.GameMap@1865b041	2022-10-02 00:00:00	124	02.10.2022	1
44	com.codecool.dungeoncrawl.logic.GameMap@3d373009	2022-10-01 00:00:00	114	01.10.22	1
51	com.codecool.dungeoncrawl.logic.GameMap@669b1f96	2022-10-01 00:00:00	120	17.58	1
52	com.codecool.dungeoncrawl.logic.GameMap@2dd77404	2022-10-01 00:00:00	122	18:00	1
53	com.codecool.dungeoncrawl.logic.GameMap@634942a	2022-10-01 00:00:00	123	save a	1
\.


--
-- Data for Name: name_game; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.name_game (id, name, player_id) FROM stdin;
\.


--
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player (id, player_name, hp, x, y, strength, key) FROM stdin;
125		20	5	13	20	f
113		20	5	13	5	f
114		25	2	7	24	f
115		20	5	13	20	f
116		15	12	3	20	f
117		20	6	13	20	f
118		20	6	5	20	f
119		15	23	7	20	f
120		20	5	13	20	f
122		20	5	13	20	f
123		20	6	5	20	f
124		15	6	4	20	f
\.


--
-- Name: gameNames_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."gameNames_id_seq"', 1, false);


--
-- Name: game_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.game_state_id_seq', 54, true);


--
-- Name: player_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_id_seq', 125, true);


--
-- Name: game_state game_state_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT game_state_pkey PRIMARY KEY (id);


--
-- Name: name_game gamenames_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.name_game
    ADD CONSTRAINT gamenames_pk PRIMARY KEY (id);


--
-- Name: player player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- Name: gamenames_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX gamenames_id_uindex ON public.name_game USING btree (id);


--
-- Name: gamenames_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX gamenames_name_uindex ON public.name_game USING btree (name);


--
-- Name: game_state fk_player_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);


--
-- PostgreSQL database dump complete
--

