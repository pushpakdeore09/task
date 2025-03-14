CREATE PROCEDURE Add_Post
    @Username NVARCHAR(8),
    @Post_ID NVARCHAR(2),
    @Title NVARCHAR(MAX),
    @Description NVARCHAR(MAX),
    @ImageUrl NVARCHAR(MAX),
    @Time VARCHAR,
    @StatusMessage NVARCHAR(10) OUTPUT
AS
BEGIN
    BEGIN TRY
        INSERT INTO User_Post(Username, Post_ID, Title, Description, ImageUrl, Time)
        VALUES (@Username, @Post_ID, @Title, @Description, @ImageUrl, @Time);

        SET @statusMessage = 'Success';
    END TRY
    BEGIN CATCH
        SET @statusMessage = 'Failed';
    END CATCH
END;


CREATE PROCEDURE UpdateLikes
    @Title NVARCHAR(MAX),
    @Action VARCHAR(10),
    @StatusMessage NVARCHAR(10) OUTPUT
AS
BEGIN
    BEGIN TRY
        IF @Action = 'like'
        BEGIN
            UPDATE User_Post
            SET Likes = Likes + 1
            WHERE Title = @Title
            SET @StatusMessage = 'Success';
        END
        ELSE IF @Action = 'dislike'
        BEGIN
            UPDATE User_Post
            SET Likes = CASE WHEN Likes > 0 THEN Likes - 1 ELSE 0 END
            WHERE Title = @Title
            SET @StatusMessage = 'Success';
        END
        ELSE
        BEGIN
            SET @StatusMessage = 'Failed';
        END
    END TRY
    BEGIN CATCH
        SET @StatusMessage = 'Failed: ' + ERROR_MESSAGE();
    END CATCH
END



select * from User_Post;