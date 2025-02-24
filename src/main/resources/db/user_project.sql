use test_db;

CREATE TABLE User_Projects (
    Username VARCHAR(8) NOT NULL,             -- Username of the user which added project
    Project_ID VARCHAR(2) NOT NULL,           -- Project identifier
    Title NVARCHAR(MAX) NOT NULL,             -- Title of the project
    Description NVARCHAR(MAX) NOT NULL,       -- Project description
    ImageUrl NVARCHAR(MAX) NOT NULL,          -- URL for the project image
    
    CONSTRAINT PK_User_Projects PRIMARY KEY (Username, Project_ID),  -- Composite Primary Key
    CONSTRAINT FK_User_Projects_Username FOREIGN KEY (Username) REFERENCES Main(Username) ON DELETE CASCADE -- Foreign Key
);

INSERT INTO User_Projects (
    Username, 
    Project_ID, 
    Title, 
    Description, 
    ImageUrl
)
VALUES (
    -- Basic project details
    'Dirghayu', 
    'P1', 
    'Asteroid Detection', 
    'A project to identify asteroids using advanced technologies.', 
    'https://example.com/asteroid.jpg'
);

ALTER TABLE User_Projects 
ADD Contributors NVARCHAR(MAX); 

-- sp to update Contributors of project

CREATE PROCEDURE UpdateContributors
    @Username NVARCHAR(8),        
    @Project_ID NVARCHAR(2),      
    @Contributors NVARCHAR(MAX),  
    @StatusMessage NVARCHAR(10) OUTPUT  
AS
BEGIN
    BEGIN TRY
        
        UPDATE User_Projects
        SET Contributors = @Contributors
        WHERE Username = @Username
        AND Project_ID = @Project_ID;

      
        IF @@ROWCOUNT > 0
        BEGIN
            SET @StatusMessage = 'Success'; 
        END
        ELSE
        BEGIN
            SET @StatusMessage = 'Fail';  
        END
    END TRY
    BEGIN CATCH
       
        SET @StatusMessage = 'Fail';  
    END CATCH
END



select * from User_Projects;
