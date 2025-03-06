create table Certificate (
	Username varchar(8) not null,
	Certificate_Id varchar(2) not null,
	ImageUrl nvarchar(max),
	IssuedBy nvarchar(max),
	Remark nvarchar(max),
	constraint PK_Certificate primary key (Username, Certificate_Id),
	FOREIGN KEY (Username) REFERENCES Main(Username)
);

-- sp to add certificate

CREATE PROCEDURE Add_Certificate
    @Username VARCHAR(8),
    @Certificate_ID VARCHAR(2),
    @ImageUrl NVARCHAR(MAX),
    @IssuedBy NVARCHAR(MAX),
    @Remark NVARCHAR(MAX),
    @StatusMessage NVARCHAR(255) OUTPUT
AS
BEGIN
    BEGIN TRANSACTION;

    BEGIN TRY
        IF EXISTS (SELECT 1 FROM Main WHERE Username = @Username)
        BEGIN
            INSERT INTO Certificate (Username, Certificate_ID, ImageUrl, IssuedBy, Remark)
            VALUES (@Username, @Certificate_ID, @ImageUrl, @IssuedBy, @Remark);
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

-- sp to delete certificate
CREATE PROCEDURE Delete_Certificate
    @Username VARCHAR(8),
    @Certificate_ID VARCHAR(2),
    @StatusMessage NVARCHAR(255) OUTPUT
AS
BEGIN
    BEGIN TRY
        IF EXISTS (SELECT 1 FROM Certificate WHERE Username = @Username AND Certificate_ID = @Certificate_ID)
        BEGIN
            DELETE FROM Certificate WHERE Username = @Username AND Certificate_ID = @Certificate_ID;
            SET @StatusMessage = 'Success';
        END
        ELSE
        BEGIN
            SET @StatusMessage = 'Failed';
        END
    END TRY
    BEGIN CATCH
        SET @StatusMessage = ERROR_MESSAGE();
    END CATCH
END;

-- sp tp update certificate
CREATE PROCEDURE Update_Certificate
    @Username VARCHAR(8),
    @Certificate_ID INT,
    @ImageUrl NVARCHAR(MAX),
    @IssuedBy NVARCHAR(MAX),
    @Remark NVARCHAR(MAX),
    @StatusMessage NVARCHAR(255) OUTPUT
AS
BEGIN
    BEGIN TRY
        IF EXISTS (SELECT 1 FROM Certificate WHERE Username = @Username AND Certificate_ID = @Certificate_ID)
        BEGIN
            UPDATE Certificate
            SET
                ImageUrl = @ImageUrl,
                IssuedBy = @IssuedBy,
                Remark = @Remark
            WHERE Username = @Username AND Certificate_ID = @Certificate_ID;
            SET @StatusMessage = 'Success';
        END
        ELSE
        BEGIN
            SET @StatusMessage = 'Failed';
        END
    END TRY
    BEGIN CATCH
        SET @StatusMessage = ERROR_MESSAGE();
    END CATCH
END;

select * from Certificate;