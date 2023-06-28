/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : SQLite
 Source Server Version : 3035005
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3035005
 File Encoding         : 65001

 Date: 28/06/2023 17:15:08
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for n_category
-- ----------------------------
DROP TABLE IF EXISTS "n_category";
CREATE TABLE "n_category" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "title" VARCHAR(255) NOT NULL,
  "p_id" INTEGER NOT NULL,
  "description" VARCHAR(255)
);

-- ----------------------------
-- Table structure for n_sites
-- ----------------------------
DROP TABLE IF EXISTS "n_sites";
CREATE TABLE "n_sites" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "name" VARCHAR(255) NOT NULL,
  "src" VARCHAR(255) NOT NULL,
  "sort" INTEGER NOT NULL,
  "p_id" INTEGER NOT NULL,
  "icon" VARCHAR(100),
  "description" VARCHAR(255),
  "state" INTEGER DEFAULT 1
);

-- ----------------------------
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "sqlite_sequence";
CREATE TABLE "sqlite_sequence" (
  "name",
  "seq"
);

-- ----------------------------
-- Auto increment value for n_category
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 1 WHERE name = 'n_category';

-- ----------------------------
-- Auto increment value for n_sites
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 1 WHERE name = 'n_sites';

PRAGMA foreign_keys = true;
