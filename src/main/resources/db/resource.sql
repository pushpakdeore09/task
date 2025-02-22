

CREATE TABLE Main (
    Username VARCHAR(8) NOT NULL PRIMARY KEY,
    Password VARCHAR(70) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT IDX_Email UNIQUE (Email)
);


create table Resource (
	Username varchar(8),
	Resource nvarchar(255) primary key,
	SubResource nvarchar(max),
	Description nvarchar(max),
	foreign key (Username) references Main(Username)
);

INSERT INTO Main (Username, Password, Email)
VALUES ('Dirghayu', '$2a$10$9UZBhsy3K7XyCsa.fokQt.JZVvhXZXLOM7.jiLk4Sy/mgnzynu56O', 'dirghayurami52@gmail.com');

-- sp to store data in Resource table
CREATE PROCEDURE Add_Resource
    @Username VARCHAR(8),
    @Resource NVARCHAR(255),
    @SubResource NVARCHAR(MAX),
    @Description NVARCHAR(MAX),
    @StatusMessage NVARCHAR(255) OUTPUT
AS
BEGIN
    BEGIN TRANSACTION;

    BEGIN TRY
        IF EXISTS (SELECT 1 FROM Main WHERE Username = @Username)
        BEGIN
            INSERT INTO Resource (Username, Resource, SubResource, Description)
            VALUES (@Username, @Resource, @SubResource, @Description);
            SET @StatusMessage = 'Success';
            COMMIT TRANSACTION;
        END
        ELSE
        BEGIN
            SET @StatusMessage = 'Failed';
            ROLLBACK TRANSACTION;
        END
    END TRY
    BEGIN CATCH
        SET @StatusMessage = ERROR_MESSAGE();
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;

-- sp to get data from Resource table based on Resource attribute
create procedure GetResourceByResourcename
	@Resource nvarchar(255)
as
begin
	select Username, SubResource, Description
	from Resource
	where Resource = @Resource
end;

-- sp to get Resource by Username
create procedure GetResourceByUsername
	@Username varchar(8)
as
begin
	select Resource from Resource
	where Username = @Username
end;

