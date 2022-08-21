INSERT INTO POSTS (id, title, description, photo, post_date_and_time)
VALUES ('15a95af7-8b83-4a08-8001-0f865db8ea27', '????', '????????????????????????? ??????????????????????????????',
        'https://llandscapes-10674.kxcdn.com/wp-content/uploads/2018/01/landscape-1799006_1280-e1515723740943.jpg',
        '2022-08-11 20:38:35'),
       ('d06cb831-9427-41ee-adcc-271f7b02d626', '????', '????????????????????????? ??????????????????????????????',
        'https://thumbs.dreamstime.com/b/rainbow-love-heart-background-red-wood-60045149.jpg',
        '2022-08-11 20:38:35'),
       ('ef90aee5-5337-4ebf-899f-e2823271f8c5', 'Fifth', 'Fifth Fifth Fifth Fifth',
        'https://thumbs.dreamstime.com/b/alone-sunset-man-near-cherry-tree-87689017.jpg',
        '2022-08-05 20:38:35'),
       ('bb2db642-6681-4e96-bff9-d226d6384efb', 'Seventh', 'Seventh Seventh Seventh Seventh Seventh Seventh Seventh',
        'https://thumbs.dreamstime.com/b/dawn-go-cong-sea-everyday-sunrise-will-be-different-life-same-lonely-new-days-have-many-better-103782551.jpg',
        '2022-08-07 20:38:35'),
       ('d96e1892-3019-4304-bd5a-1ef750be3aca', 'Eighth', 'Eighth Eighth Eighth Eighth Eighth Eighth',
        'https://thumbs.dreamstime.com/b/beautiful-rain-forest-ang-ka-nature-trail-doi-inthanon-national-park-thailand-36703721.jpg',
        '2022-08-08 20:38:35'),
       ('abdee4f9-5763-4afc-85ed-98b2fdefb366', 'Tenth', 'Tenth Tenth Tenth Tenth',
        'https://thumbs.dreamstime.com/b/sunset-merauke-i-love-watching-to-share-beautiful-view-all-you-172966959.jpg',
        '2022-08-10 20:38:35');

/*INSERT INTO COMMENTS (id, comment, comment_date_and_time, post_id, user_id)
VALUES ('48a95af7-8b83-4a08-8001-0f865db8ea46', '?????????????', '2022-08-05 20:38:35',
        'ef90aee5-5337-4ebf-899f-e2823271f8c5', '97591abe-5108-4bc2-afaa-6bc6a339619c'),
       ('15a95af7-8b83-4a08-8001-0f865db8e351', '!!!!!!!!!', '2022-08-11 20:38:35',
        'ef90aee5-5337-4ebf-899f-e2823271f8c5', '1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b'),
       ('ebdee4f9-5763-4afc-85ed-98b2fdefb05f', '+++++++++++++++', '2022-08-03 20:38:35',
        'ef90aee5-5337-4ebf-899f-e2823271f8c5', '97591abe-5108-4bc2-afaa-6bc6a339619c');*/

INSERT INTO USERS(id, username, password)
VALUES ('97591abe-5108-4bc2-afaa-6bc6a339619c', 'user',
        '{bcrypt}$2a$10$AsRCsrfh4423vjPr0xKpZeNpYixVcNtDpiGdM5xcIejUXOttH2jcu'),
       ('1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b', 'admin',
        '{bcrypt}$2a$10$9Ox9WgR8X5SD04lLSdCwJ.AITH/cAZmcZ9tMkqJUFYSc0krItXT9W');

INSERT INTO ROLES(id, name)
VALUES ('7f74bb02-9f14-43ce-8b28-8c0c889d1558', 'USER'),
       ('25dde1c9-f740-46a7-a598-d62f37126950', 'ADMIN');

INSERT INTO USERS_ROLES(user_id, role_id)
VALUES ('97591abe-5108-4bc2-afaa-6bc6a339619c', '7f74bb02-9f14-43ce-8b28-8c0c889d1558'),
       ('1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b', '7f74bb02-9f14-43ce-8b28-8c0c889d1558'),
       ('1c6eb4cd-b644-4932-8d88-ec97b3ba0b7b', '25dde1c9-f740-46a7-a598-d62f37126950');