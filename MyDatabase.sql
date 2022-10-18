use [master]
GO

create database [MVC2LamVNT]
GO

use [MVC2LamVNT]
GO

create table Registration (
	username varchar(20) primary key not null,
	password varchar(30) not null,
	lastname nvarchar(100) not null,
	isAdmin bit not null
)
GO

create table Product(
	SKU varchar(10) primary key not null,
	name varchar(20),
	description varchar(50),
	price float
)
GO

create table TblOrder(
	ordId int identity(1,1) primary key,
	date Date,
	total float
)
GO

create table OrderDetail(
	id int identity(1,1) primary key,
	ordId int foreign key references tblOrder(Ordid),
	SKU varchar(10) foreign key references Product(SKU),
	quantity int,
	price float,
	totalPrice float,	
)
GO
/*****************
	INSERT DATA
******************/
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'annt', N'123', N'Nguyễn Thành An', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'bdv', N'123', N'Đào Văn B', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'beb', N'123', N'Be Be', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'hoax', N'123', N'Xuân Hoa', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'lamvo', N'123', N'Võ Ngọc Trúc Lam', 1)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'manhx', N'123', N'Xuân Mạnh', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'not', N'123', N'Thị Nở', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'nva', N'456', N'Nguyễn Văn A', 0)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'pheoc123', N'123', N'Chí Phèo', 0)
GO

INSERT [dbo].[Product] ([SKU], [name], [description], [price]) VALUES (N'B001', N'Java', N'This is Java book', 10.5)
GO
INSERT [dbo].[Product] ([SKU], [name], [description], [price]) VALUES (N'B002', N'JDK', N'This is JDK book', 20)
GO
INSERT [dbo].[Product] ([SKU], [name], [description], [price]) VALUES (N'B003', N'Netbeans', N'This is Netbeans book', 15)
GO
INSERT [dbo].[Product] ([SKU], [name], [description], [price]) VALUES (N'B004', N'Tomcat', N'This is Catalina', 35)
GO
