use jpact5st7
create table users (
	id int,
	username nvarchar(100),
	email nvarchar(100),
	password nvarchar(100),
	fullname nvarchar(100),
	images nvarchar(max),
	primary key (id)
);