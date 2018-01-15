CREATE SCHEMA `movie_rating` ;
CREATE TABLE IF NOT EXISTS `movie_rating`.`users` (
  `id_users` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `login` VARCHAR(45) NOT NULL COMMENT 'login',
  `email` VARCHAR(45) NOT NULL COMMENT 'e-mail',
  `password` VARCHAR(45) NOT NULL COMMENT 'Пароль\n',
  `admin_flag` TINYINT(4) NULL DEFAULT NULL COMMENT 'Администратор или обычный пользаватель\n',
  `status` INT(11) NULL DEFAULT NULL COMMENT 'Статус пользавателя\nНачальный статус - 0\nЕсли оценка близка к средней +1\nЕсли далека  -1\nМожет быть отрицательной',
  `access` TINYINT(4) NULL DEFAULT NULL COMMENT 'Забанен пользаватель или нет',
  PRIMARY KEY (`id_users`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `e-mail_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Пользователи (обычные и админестраторы)\nНет соответствующей таблицы с локализацией, т.к. нет полей, нуждающихся в ней';

CREATE TABLE IF NOT EXISTS `movie_rating`.`films` (
  `id_film` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `title` VARCHAR(200) NOT NULL COMMENT 'Название фильма',
  `type` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Тип фильма(Может быть многосерийный, полнометражный, краткометражный и т.д.)',
  `realese_year` YEAR NULL DEFAULT NULL COMMENT 'Год выход на экран',
  `films_rating` DOUBLE NULL DEFAULT NULL COMMENT 'Рейтинг фильма (средний из поставленных пользавателями) \nНе создан индекс к рейтингу ( хотя по нему часто ищут) т.к. рейтинг часто меняют',
  `director` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Режиссер',
  `description` TEXT NULL DEFAULT NULL COMMENT 'Краткое описание ',
  PRIMARY KEY (`id_film`),
  INDEX `title` (`title` ASC)  COMMENT 'Фильмы часто ищут по названиям ',
  INDEX `year` (`realese_year` ASC)  COMMENT 'Фильмы могут искать по году\nгод не меняется',
  INDEX `director` (`director` ASC)  COMMENT 'Фильмы могут искать по режиссеру\nрежиссер не меняется',
  INDEX `type` (`type` ASC)  COMMENT 'Фильмы могут искать по типу\nтип не меняется')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Фильмы';

CREATE TABLE IF NOT EXISTS `movie_rating`.`lang` (
  `id_lang` CHAR(2) NOT NULL COMMENT 'Краткое наисенование языка(en или ru)\n',
  `langname` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Название языка\n',
  PRIMARY KEY (`id_lang`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Таблца для локализации';

CREATE TABLE IF NOT EXISTS `movie_rating`.`rating` (
  `id_rating` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `film_id_film` INT(11) NOT NULL COMMENT 'Ключ фильма, которому ставится рейтинг и/или отзыв ',
  `users_id_user` INT(11) NOT NULL COMMENT 'Ключ фильма, которому ставится рейтинг и/или отзыв ',
  `rating` INT(11) NULL DEFAULT NULL COMMENT 'Рейтинг ',
  `review` TEXT NULL DEFAULT NULL COMMENT 'Отзыв пользователя',
  PRIMARY KEY (`id_rating`, `film_id_film`, `users_id_user`),
  INDEX `fk_user_rates_film` (`users_id_user` ASC),
  INDEX `fk_film_has_rating` (`film_id_film` ASC),
  CONSTRAINT `fk_films_has_rating`
    FOREIGN KEY (`film_id_film`)
    REFERENCES `movie_rating`.`films` (`id_film`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_rates_film`
    FOREIGN KEY (`users_id_user`)
    REFERENCES `movie_rating`.`users` (`id_users`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Рейтинги и отзывы пользавателей\nЛюбой пользователь может поставить любому фильму рейтинг и/или отзыв \n\n';

CREATE TABLE IF NOT EXISTS `movie_rating`.`genre` (
  `id_genre` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный кюч',
  `genre` VARCHAR(45) NOT NULL COMMENT 'Жанр\n',
  PRIMARY KEY (`id_genre`),
  UNIQUE INDEX `genre_UNIQUE` (`genre` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Жанры фильмов\nВынесенена как отдельная таблица т.к. 1 фильм может иметь несколько жанров.';

CREATE TABLE IF NOT EXISTS `movie_rating`.`films_has_genre` (
  `films_id_films` INT(11) NOT NULL COMMENT 'Ключ фильма',
  `genre_id_genre` INT(11) NOT NULL COMMENT 'Ключ жанра',
  PRIMARY KEY (`films_id_films`, `genre_id_genre`),
  INDEX `fk_films_has_genre_genre_idx` (`genre_id_genre` ASC),
  INDEX `fk_films_has_genre_films_idx` (`films_id_films` ASC),
  CONSTRAINT `fk_films_has_genre_films`
    FOREIGN KEY (`films_id_films`)
    REFERENCES `movie_rating`.`films` (`id_film`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_films_has_genre_genre`
    FOREIGN KEY (`genre_id_genre`)
    REFERENCES `movie_rating`.`genre` (`id_genre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Реализация связи многие ко многим';

CREATE TABLE IF NOT EXISTS `movie_rating`.`tgenre` (
  `id_tgenre` INT(11) NOT NULL AUTO_INCREMENT,
  `lang_id_lang` CHAR(2) NOT NULL COMMENT 'Язык, на котрый переведен жанр',
  `genre_id_genre` INT(11) NOT NULL COMMENT 'Ключ жанра',
  `tgerne` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Переведенный жанр',
  PRIMARY KEY (`id_tgenre`, `lang_id_lang`, `genre_id_genre`),
  INDEX `fk_tgenre_lang1_idx` (`lang_id_lang` ASC),
  INDEX `fk_tgenre_genre1_idx` (`genre_id_genre` ASC),
  CONSTRAINT `fk_tgenre_lang1`
    FOREIGN KEY (`lang_id_lang`)
    REFERENCES `movie_rating`.`lang` (`id_lang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tgenre_genre1`
    FOREIGN KEY (`genre_id_genre`)
    REFERENCES `movie_rating`.`genre` (`id_genre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Локализация жанров';

CREATE TABLE IF NOT EXISTS `movie_rating`.`tfilms` (
  `id_tfilm` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Ключ переводимого фильма',
  `lang_id_lang` CHAR(2) NOT NULL,
  `films_id_film` INT(11) NOT NULL COMMENT 'Язык, на который переводим',
  `ttitle` VARCHAR(200) NULL DEFAULT NULL COMMENT 'Переведенное название фильма\n',
  `ttype` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Переведенный тип фильма (полнометражный, односерийный и т.д.)\n',
  `tdirector` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Переведенное имя режиссера',
  `tdescription` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Описние фильма на языке локали',
  PRIMARY KEY (`id_tfilm`, `lang_id_lang`, `films_id_film`),
  INDEX `fk_tfilms_lang1_idx` (`lang_id_lang` ASC),
  INDEX `fk_tfilms_films1_idx` (`films_id_film` ASC),
  INDEX `titlt` (`ttitle` ASC)  COMMENT 'Фильмы могут искать по названию\nназвание не меняется',
  INDEX `type` (`ttype` ASC)  COMMENT 'Фильмы могут искать по типу\nрежиссер не тип',
  INDEX `director` (`tdirector` ASC)  COMMENT 'Фильмы могут искать по режиссеру\nрежиссер не меняется',
  CONSTRAINT `fk_tfilms_lang1`
    FOREIGN KEY (`lang_id_lang`)
    REFERENCES `movie_rating`.`lang` (`id_lang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tfilms_films1`
    FOREIGN KEY (`films_id_film`)
    REFERENCES `movie_rating`.`films` (`id_film`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Локализация таблицы films';

CREATE TABLE IF NOT EXISTS `movie_rating`.`country` (
  `id_country` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `name_country` VARCHAR(45) NOT NULL COMMENT 'Название страны',
  PRIMARY KEY (`id_country`),
  UNIQUE INDEX `name_country_UNIQUE` (`name_country` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Страны которые снимали фильмы\nВынесенена как отдельная таблица т.к. 1  фильм могут снимать несколько стран.';

CREATE TABLE IF NOT EXISTS `movie_rating`.`tcountry` (
  `id_tcountry` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `country_id_country` INT(11) NOT NULL COMMENT 'Ключ страны',
  `lang_id_lang` CHAR(2) NOT NULL COMMENT 'язык',
  `tname_country` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Переведенное название страны\n',
  PRIMARY KEY (`id_tcountry`, `country_id_country`, `lang_id_lang`),
  INDEX `fk_tcountry_country1_idx` (`country_id_country` ASC),
  INDEX `fk_tcountry_lang1_idx` (`lang_id_lang` ASC),
  CONSTRAINT `fk_tcountry_country1`
    FOREIGN KEY (`country_id_country`)
    REFERENCES `movie_rating`.`country` (`id_country`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tcountry_lang1`
    FOREIGN KEY (`lang_id_lang`)
    REFERENCES `movie_rating`.`lang` (`id_lang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Локализация для стран';

CREATE TABLE IF NOT EXISTS `movie_rating`.`country_has_films` (
  `country_id_country` INT(11) NOT NULL COMMENT 'Ключ страны',
  `film_id_films` INT(11) NOT NULL COMMENT 'Ключ фильма',
  PRIMARY KEY (`country_id_country`, `film_id_films`),
  INDEX `fk_country_has_films_films1_idx` (`film_id_films` ASC),
  INDEX `fk_country_has_films_country1_idx` (`country_id_country` ASC),
  CONSTRAINT `fk_country_has_films_country1`
    FOREIGN KEY (`country_id_country`)
    REFERENCES `movie_rating`.`country` (`id_country`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_country_has_films_films1`
    FOREIGN KEY (`film_id_films`)
    REFERENCES `movie_rating`.`films` (`id_film`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Реализация связи многие ко многим';

CREATE TRIGGER update_rating_after_insert AFTER INSERT ON movie_rating.rating
FOR EACH ROW
		UPDATE movie_rating.films
        SET films.films_rating = 
		(SELECT AVG(rating) FROM movie_rating.rating  
		WHERE movie_rating.rating .film_id_film= films.id_film);
	
CREATE TRIGGER update_rating_after_update AFTER UPDATE ON movie_rating.rating 
FOR EACH ROW
		UPDATE movie_rating.films 
        SET films.films_rating = 
		(SELECT AVG(rating) FROM movie_rating.rating  
		WHERE movie_rating.rating .film_id_film= films.id_film) ;
CREATE TRIGGER update_rating_after_delet AFTER DELETE ON movie_rating.rating 
FOR EACH ROW
		UPDATE movie_rating.films 
        SET films.films_rating = 
		(SELECT AVG(rating) FROM movie_rating.rating  
		WHERE movie_rating.rating .film_id_film= films.id_film) ;
