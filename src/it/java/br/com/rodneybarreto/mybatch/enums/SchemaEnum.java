package br.com.rodneybarreto.mybatch.enums;

import lombok.Getter;

@Getter
public enum SchemaEnum {

    CREATE_TABLE_CUSTOMER("""
        create table CUSTOMER (
            id bigint not null auto_increment,
            name varchar(255) not null,
            email varchar(255) not null,
            pix_key varchar(255),
            pix_key_encrypted varchar(255),
            pix_key_open varchar(255),
            primary key(id),
            unique(email)
        );
    """),
    DROP_TABLE_CUSTOMER("drop table CUSTOMER");

    private final String sql;

    SchemaEnum(String sql) {
        this.sql = sql;
    }

}
