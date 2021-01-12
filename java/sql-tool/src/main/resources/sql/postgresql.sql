CREATE TABLE "public"."tab_zsql" (
                                     "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
                                     "project_name" varchar(255) COLLATE "pg_catalog"."default",
                                     "project_function" varchar(255) COLLATE "pg_catalog"."default",
                                     "name" varchar(255) COLLATE "pg_catalog"."default",
                                     "en_name" varchar(255) COLLATE "pg_catalog"."default",
                                     "type" varchar(255) COLLATE "pg_catalog"."default",
                                     "dependence" varchar(255) COLLATE "pg_catalog"."default",
                                     "parent_id" varchar(50) COLLATE "pg_catalog"."default",
                                     "descripe" varchar(255) COLLATE "pg_catalog"."default",
                                     "sql" text COLLATE "pg_catalog"."default",
                                     "remarks" varchar(255) COLLATE "pg_catalog"."default",
                                     "hierarchy" varchar(255) COLLATE "pg_catalog"."default",
                                     "uptime" timestamp(6),
                                     CONSTRAINT "tab_zsql_pkey" PRIMARY KEY ("id")
)
;