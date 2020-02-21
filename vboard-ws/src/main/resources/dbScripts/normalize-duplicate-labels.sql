/*
cases treated :
'#licochat' and '##licochat'
'#LICOCHAT' and '#licochat'
'# licochat ' and '#licochat'
*/

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
/* LABELS with labels.label_name                                                     */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

UPDATE IGNORE labels SET label_name = CONCAT('$OLD$',label_name);
UPDATE IGNORE labels SET label_name = LOWER(label_name);
UPDATE IGNORE labels SET label_name = REPLACE(label_name, '#', '');
UPDATE IGNORE labels SET label_name = TRIM(label_name);
UPDATE IGNORE labels SET label_name = REPLACE(label_name, '$old$', '');

DELETE FROM labels WHERE label_name LIKE '$old$%';
DELETE FROM labels WHERE label_name LIKE '$OLD$%';
DELETE FROM labels WHERE label_name LIKE '#%';
DELETE FROM labels WHERE label_name LIKE ' %';

UPDATE IGNORE labels SET label_name = CONCAT('#',label_name);

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
/* PINS with pins.labels                                                             */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

UPDATE IGNORE pins SET labels = LOWER(labels);
UPDATE IGNORE pins SET labels = REPLACE(labels, '##', '#');
UPDATE IGNORE pins SET labels = REPLACE(labels, '# ', '#');
UPDATE IGNORE users SET favorite_labels = REPLACE(favorite_labels, ',  ', ',');

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
/* USERS with users.favorite_labels                                                  */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

UPDATE IGNORE users SET favorite_labels = LOWER(favorite_labels);
UPDATE IGNORE users SET favorite_labels = REPLACE(favorite_labels, '##', '#');
UPDATE IGNORE users SET favorite_labels = REPLACE(favorite_labels, '# ', '#');
UPDATE IGNORE users SET favorite_labels = REPLACE(favorite_labels, ',  ', ',');

    /*Case with double #mpd like  */
UPDATE IGNORE users SET favorite_labels = CONCAT('#$newmpd$,', favorite_labels) where favorite_labels like "%mpd%mpd%";
UPDATE IGNORE users SET favorite_labels = REPLACE(favorite_labels, '#mpd,','') where favorite_labels like "%mpd%mpd%";
UPDATE IGNORE users SET favorite_labels = REPLACE(favorite_labels, '$newmpd$,', 'mdp');
