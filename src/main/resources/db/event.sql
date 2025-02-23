

create table Event (
	Name nvarchar(255) primary key,
	Username varchar(8),
	Title nvarchar(max),
	Is_Paid_Free bit,
	Type nvarchar(10),
	Image nvarchar(max),
	Date nvarchar(10),
	foreign key (Username) references Main(Username)
);

-- sp to store data in Event table
CREATE PROCEDURE Add_Event
    @Name NVARCHAR(255),
    @Username VARCHAR(8),
    @Title NVARCHAR(MAX),
    @Is_Paid_Free BIT,
    @Type NVARCHAR(10),
    @Image NVARCHAR(MAX),
    @Date NVARCHAR(10),
    @StatusMessage NVARCHAR(255) OUTPUT
AS
BEGIN
    BEGIN TRANSACTION;

    BEGIN TRY
        IF EXISTS (SELECT 1 FROM Main WHERE Username = @Username)
        BEGIN
            INSERT INTO Event (Name, Username, Title, Is_Paid_Free, Type, Image, Date)
            VALUES (@Name, @Username, @Title, @Is_Paid_Free, @Type, @Image, @Date);

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

-- sp to fetch all events
create procedure GetAllEvents
as
begin
	select * from Event
end;
