BEGIN TRANSACTION;
DROP TABLE IF EXISTS `Utilisateur_ListeMenus`;
CREATE TABLE IF NOT EXISTS `Utilisateur_ListeMenus` (
	`Utilisateur`	INTEGER,
	`ListeMenus`	INTEGER,
	FOREIGN KEY(`Utilisateur`) REFERENCES `Utilisateur`,
	PRIMARY KEY(`Utilisateur`,`ListeMenus`),
	FOREIGN KEY(`ListeMenus`) REFERENCES `ListeMenus`
);
DROP TABLE IF EXISTS `Utilisateur`;
CREATE TABLE IF NOT EXISTS `Utilisateur` (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Nom`	TEXT
);
DROP TABLE IF EXISTS `Tag`;
CREATE TABLE IF NOT EXISTS `Tag` (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`Label`	TEXT NOT NULL
);
DROP TABLE IF EXISTS `Repas_Plat`;
CREATE TABLE IF NOT EXISTS `Repas_Plat` (
	`repas`	INTEGER,
	`plat`	INTEGER,
	FOREIGN KEY(`plat`) REFERENCES `Plat`,
	PRIMARY KEY(`plat`,`repas`),
	FOREIGN KEY(`repas`) REFERENCES `Repas`
);
DROP TABLE IF EXISTS `Repas`;
CREATE TABLE IF NOT EXISTS `Repas` (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nom`	TEXT,
	`personnes`	INTEGER NOT NULL,
	`numero`	INTEGER NOT NULL
);
DROP TABLE IF EXISTS `Plat_Tag`;
CREATE TABLE IF NOT EXISTS `Plat_Tag` (
	`plat`	INTEGER,
	`tag`	INTEGER,
	FOREIGN KEY(`tag`) REFERENCES `Tag`,
	FOREIGN KEY(`plat`) REFERENCES `Plat`,
	PRIMARY KEY(`tag`,`plat`)
);
DROP TABLE IF EXISTS `Plat_Ingredient`;
CREATE TABLE IF NOT EXISTS `Plat_Ingredient` (
	`plat`	INTEGER,
	`ingredient`	INTEGER,
	`grammes`	INTEGER,
	PRIMARY KEY(`plat`,`ingredient`),
	FOREIGN KEY(`ingredient`) REFERENCES `Ingredient`,
	FOREIGN KEY(`plat`) REFERENCES `Plat`
);
DROP TABLE IF EXISTS `Plat`;
CREATE TABLE IF NOT EXISTS `Plat` (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`intitule`	TEXT NOT NULL
);
DROP TABLE IF EXISTS `Menu_Repas`;
CREATE TABLE IF NOT EXISTS `Menu_Repas` (
	`menu`	INTEGER,
	`repas`	INTEGER,
	FOREIGN KEY(`repas`) REFERENCES `Repas`,
	PRIMARY KEY(`repas`,`menu`),
	FOREIGN KEY(`menu`) REFERENCES `Menu`
);
DROP TABLE IF EXISTS `Menu`;
CREATE TABLE IF NOT EXISTS `Menu` (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nom`	TEXT NOT NULL,
	`date`	INTEGER NOT NULL
);
DROP TABLE IF EXISTS `ListeMenus_Menu`;
CREATE TABLE IF NOT EXISTS `ListeMenus_Menu` (
	`listemenus`	INTEGER,
	`menu`	INTEGER,
	PRIMARY KEY(`listemenus`,`menu`),
	FOREIGN KEY(`listemenus`) REFERENCES `ListeMenus`,
	FOREIGN KEY(`menu`) REFERENCES `Menu`
);
DROP TABLE IF EXISTS `ListeMenus`;
CREATE TABLE IF NOT EXISTS `ListeMenus` (
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`date_debut`	INTEGER NOT NULL,
	`date_fin`	INTEGER NOT NULL
);
DROP TABLE IF EXISTS `Ingredient`;
CREATE TABLE IF NOT EXISTS `Ingredient` (
	`Nom`	TEXT NOT NULL,
	`ID`	INTEGER PRIMARY KEY AUTOINCREMENT
);
COMMIT;
