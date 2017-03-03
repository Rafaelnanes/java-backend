
INSERT INTO `rbn-test`.`usr_user` (`USR_ENABLE`, `USR_LOGIN`, `USR_PASSWORD`) VALUES (1, 'adm', '$2a$10$/rMGxGoKcgLMN4cgJf/iQO2zpnxbNAkol0n33z7f0vMJ.8teoYSKS');
INSERT INTO `rbn-test`.`usl_user_level` (`USL_LEVEL`,`USR_ID`) VALUES ('ROLE_ADMIN', 1);
INSERT INTO `rbn-test`.`usl_user_level` (`USL_LEVEL`,`USR_ID`) VALUES ('ROLE_VISITOR', 1);
INSERT INTO `rbn-test`.`usl_user_level` (`USL_LEVEL`,`USR_ID`) VALUES ('ROLE_CUSTOMER', 1);

INSERT INTO `rbn-test`.`usr_user` (`USR_ENABLE`, `USR_LOGIN`, `USR_PASSWORD`) VALUES (1, 'visitor', '$2a$10$/rMGxGoKcgLMN4cgJf/iQO2zpnxbNAkol0n33z7f0vMJ.8teoYSKS');
INSERT INTO `rbn-test`.`usl_user_level` (`USL_LEVEL`,`USR_ID`) VALUES ('ROLE_VISITOR', 2);

INSERT INTO `rbn-test`.`usr_user` (`USR_ENABLE`, `USR_LOGIN`, `USR_PASSWORD`) VALUES (1, 'customer', '$2a$10$/rMGxGoKcgLMN4cgJf/iQO2zpnxbNAkol0n33z7f0vMJ.8teoYSKS');
INSERT INTO `rbn-test`.`usl_user_level` (`USL_LEVEL`,`USR_ID`) VALUES ('ROLE_CUSTOMER', 3);