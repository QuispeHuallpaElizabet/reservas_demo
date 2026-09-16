create table usuario (
    id_usuario bigserial primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    telefono varchar(20),
    correo varchar(150) unique not null,
    contraseña varchar(255) not null
    
);

create table rol (
    id_rol bigserial primary key,
    nombre varchar(50) unique not null
);

create table usuario_rol (
    id_usuario bigint not null,
    id_rol bigint not null,

    primary key (id_usuario, id_rol),

    foreign key (id_usuario)
        references usuario(id_usuario),

    foreign key (id_rol)
        references rol(id_rol)
);

insert into rol (nombre)
values
    ('CLIENTE'),
    ('ADMIN_COMPLEJO'),
    ('ADMIN_PLATAFORMA');

insert into usuario (nombres, apellidos, telefono, correo, contraseña)
values
    ('Ana María', 'Dias Torres', '987654321', 'ana.dias@gmail.com', '224466'),
    ('Carlos Alberto', 'Mendoza Rojas', '912345678', 'carlos.mendoza@gmail.com', '123456');

insert into usuario_rol (id_usuario, id_rol)
values
    (1, 1),
    (2, 1);

