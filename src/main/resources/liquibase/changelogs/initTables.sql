DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
  id SERIAL NOT NULL ,
  address character varying(255) NOT NULL,
  birthday date NOT NULL,
  email character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  phonenumber character varying(255) NOT NULL,
  subscription boolean,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
  CONSTRAINT uk_kwds03ohobcd8p6eowkw0f5bm UNIQUE (phonenumber)
);

ALTER TABLE users
  OWNER TO postgres;

DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles
(
  id SERIAL NOT NULL ,
  authority character varying(255) NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (id),
  CONSTRAINT uk_r261muslviw4d89p3xlvagqof UNIQUE (authority)
)
WITH (
OIDS=FALSE
);
ALTER TABLE roles
  OWNER TO postgres;

DROP TABLE IF EXISTS zones CASCADE;

CREATE TABLE zones
(
  id SERIAL NOT NULL,
  description character varying(255) NOT NULL,
  image character varying(255) NOT NULL,
  title character varying(255) NOT NULL,
  CONSTRAINT zones_pkey PRIMARY KEY (id),
  CONSTRAINT uk_dk0pfmiojpoopoavxvu5tmd5s UNIQUE (title)
)
WITH (
OIDS=FALSE
);
ALTER TABLE zones
  OWNER TO postgres;

DROP TABLE IF EXISTS user_role CASCADE;

CREATE TABLE user_role
(
  user_id SERIAL NOT NULL,
  role_id SERIAL NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id)
  REFERENCES users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkt7e7djp752sqn6w22i6ocqy6q FOREIGN KEY (role_id)
  REFERENCES roles (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE user_role
  OWNER TO postgres;

DROP TABLE IF EXISTS category CASCADE;

CREATE TABLE category
(
  id SERIAL NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE category
  OWNER TO postgres;

DROP TABLE IF EXISTS dish CASCADE;

CREATE TABLE dish
(
  id SERIAL NOT NULL,
  description character varying(255) NOT NULL,
  image text NOT NULL,
  mass double precision NOT NULL,
  name character varying(255) NOT NULL,
  price double precision NOT NULL,
  category_id integer,
  CONSTRAINT dish_pkey PRIMARY KEY (id),
  CONSTRAINT fkdof5kpuiwjx3qnct4xdotkbon FOREIGN KEY (category_id)
  REFERENCES public.category (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE dish
  OWNER TO postgres;

DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE orders
(
  id SERIAL NOT NULL,
  date_time timestamp without time zone NOT NULL,
  user_id bigint,
  CONSTRAINT orders_pkey PRIMARY KEY (id),
  CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE orders
  OWNER TO postgres;

DROP TABLE IF EXISTS order_dish CASCADE;

CREATE TABLE order_dish
(
  order_id SERIAL NOT NULL,
  dish_id SERIAL NOT NULL,
  CONSTRAINT order_dish_pkey PRIMARY KEY (order_id, dish_id),
  CONSTRAINT fk1fevhe8ke4l3uebaotqn5ae77 FOREIGN KEY (order_id)
  REFERENCES public.orders (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk5sqmqw2c9b6njaix3pswcupk5 FOREIGN KEY (dish_id)
  REFERENCES public.dish (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE order_dish
  OWNER TO postgres;