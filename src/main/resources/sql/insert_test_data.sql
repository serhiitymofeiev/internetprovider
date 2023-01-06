INSERT INTO `provider`.`roles` (`name`) VALUES ('admim');
INSERT INTO `provider`.`roles` (`name`) VALUES ('client');

INSERT INTO `provider`.`services` (`name`, `description`) VALUES ('Інтернет', 'Безлімітні тарифні плани «Інтернет»');
INSERT INTO `provider`.`services` (`name`, `description`) VALUES ('Цифрове ТВ (IPTV)', 'Перегляд каналів в цифровом форматі IPTV');
INSERT INTO `provider`.`services` (`name`, `description`) VALUES ('Телефоний зв\'\язок', 'Телефоний зв\'\язок для офісів');

INSERT INTO `provider`.`tariffs` (`name`, `description`, `price`, `services_id`) VALUES ('«Комфорт 50»', '50 до, Мбіт/с Безлімитні тарифні плани «Інтернет»', '140', '1');
INSERT INTO `provider`.`tariffs` (`name`, `description`, `price`, `services_id`) VALUES ('Розширений', 'Цифрове ТВ (IPTV)', '20', '2');
INSERT INTO `provider`.`tariffs` (`name`, `description`, `price`, `services_id`) VALUES ('Телефоний зв\'\язок', 'Телефоний зв\'\язок для офісів', '1', '3');

INSERT INTO `provider`.`contact_details` (`id`, `city`, `street`, `home`, `apartment`, `email`, `phone`) VALUES (1,'Київ','Хрещатик','3','54','admin@gmail.com','+380992589898');
INSERT INTO `provider`.`accounts` (`id`, `number`, `balance`) VALUES (1,1,0);
INSERT INTO `provider`.`users` (`login`, `password`, `first_name`, `last_name`, `surname`, `blocked`, `roles_id`, `contact_details_id`, `accounts_id`) VALUES ('adminadmin','adminadmin',' Barak ','Obama','Barakovich',0,1,1,1);